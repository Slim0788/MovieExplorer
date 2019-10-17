package com.android.academy.academy_minsk_movie;

import android.animation.ValueAnimator;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.OnFragmentInteractionListener {

    /**
     * Holds the current image position to be shared between the grid and the pager fragments. This
     * position updated when a grid item is clicked, or when paging the pager.
     */
    public static int currentPosition;
    private static final String KEY_CURRENT_POSITION = "imageCurrentPosition";

    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;
    private DrawerArrowDrawable homeDrawable;
    private AnimatedVectorDrawable tickToCross, crossToTick;

    // define a variable to track hamburger-arrow state
    private boolean isHomeAsUp = false;
    // define a variable to track tick-cross state
    private boolean isTick = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        tickToCross = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_tick2cross);
        crossToTick = (AnimatedVectorDrawable) getDrawable(R.drawable.avd_cross2tick);

        homeDrawable = new DrawerArrowDrawable(bottomAppBar.getContext());
        bottomAppBar.setNavigationIcon(homeDrawable);

        // Реализовываем BottomAppBar через setSupportActionBar для настройки меню
        // через onCreateOptionsMenu. Если устанавливать меню через replaceMenu, то
        // при смене меню и передвижении FAB меню мигает несколько раз.
        setSupportActionBar(bottomAppBar);

        setupBottomAppBarPrimary();

        // Проверяем, использует ли MainActivity версию макета
        // для планшета. Эта версия содержит <fragment.../> с id movies_fragment.
        // Если это так, то фрагмент MoviesFragment откроется самостоятельно, если нет,
        // то используется макет с FrameLayout, который используется как контейнер для
        // фрагметов и мы должны сами добавить первый фрагмент MoviesFragment.
        if (findViewById(R.id.movies_fragment) == null) {

            // Тем не менее, если мы восстанавливаемся из предыдущего состояния,
            // тогда нам не нужно ничего делать, и мы должны вернуться, иначе
            // мы могли бы получить перекрывающиеся фрагменты (например при повороте экрана).
            if (savedInstanceState != null) {

                currentPosition = savedInstanceState.getInt(KEY_CURRENT_POSITION, 0);
                // Return here to prevent adding additional MovieFragments when changing orientation.

                return;
            }

            // Создаём экземпляр MoviesFragment
            Fragment fragment = MoviesFragment.newInstance();

            // В случае, если эта деятельность была начата со специальными инструкциями из Intent,
            // передать дополнения Intent к фрагменту в качестве аргументов.
            // В данном случае эта строка приведена для примера.
            fragment.setArguments(getIntent().getExtras());

            // Добавляем фрагмент в 'container' FrameLayout
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }

    }

    @Override
    protected void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_POSITION, currentPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu_primary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                showSnackBar(getString(R.string.menu_item_search));
                return true;
            case R.id.app_bar_sync:
                showSnackBar(getString(R.string.menu_item_sync));
                Animatable animatable = (Animatable) item.getIcon();
                if (animatable.isRunning()) {
                    animatable.stop();
                } else {
                    animatable.start();
                }
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        // Пользователь нажал на кнопку "назад"

        // Проверяем, есть ли еще фрагменты в back stack.
        // Если back stack пуст, то выводим диалог.
        switch (getSupportFragmentManager().getBackStackEntryCount()) {
            case 1:
                setupBottomAppBarPrimary();
                setHomeAsUp(false);
                super.onBackPressed();
                break;
            case 0:
                showExitDialog();
                break;
            default:
                super.onBackPressed();
        }
    }

    private void init() {
        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab = findViewById(R.id.floatingActionButton);
    }

    private void setupBottomAppBarPrimary() {

        fab.setImageDrawable(getResources().getDrawable(isTick ?
                R.drawable.avd_tick2cross :
                R.drawable.avd_cross2tick));
        fab.setOnClickListener(v -> {
            showSnackBar("I am a FAB");
            animatedFAB();
        });

        if (bottomAppBar.getFabAlignmentMode() != BottomAppBar.FAB_ALIGNMENT_MODE_END) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        }

        bottomAppBar.setContentDescription(getString(R.string.content_description_appbar_navigation));

        bottomAppBar.setNavigationOnClickListener(v -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                BottomSheetDialogFragment fragment = ThreadsBottomSheetDialog.newInstance();
                fragment.show(getSupportFragmentManager(), ThreadsBottomSheetDialog.TAG);
            } else {
                onBackPressed();
            }

        });
    }

    private void setupBottomAppBarSecondary() {

        setHomeAsUp(true);

        bottomAppBar.setNavigationContentDescription(getString(R.string.content_description_appbar_come_back));

        if (bottomAppBar.getFabAlignmentMode() != BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        }

        fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_youtube_24dp));
    }

    protected void setHomeAsUp(boolean isHomeAsUp) {
        if (this.isHomeAsUp != isHomeAsUp) {
            this.isHomeAsUp = isHomeAsUp;
            ValueAnimator anim = isHomeAsUp ? ValueAnimator.ofFloat(0, 1) : ValueAnimator.ofFloat(1, 0);
            anim.addUpdateListener(valueAnimator -> {
                float slideOffset = (Float) valueAnimator.getAnimatedValue();
                homeDrawable.setProgress(slideOffset);
            });
            anim.setInterpolator(new DecelerateInterpolator());
            anim.setDuration(1000);
            anim.start();
        }
    }

    private void showExitDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.exit_alert_title))
                .setMessage(getString(R.string.exit_alert_message))
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, (arg0, arg1) ->
                        MainActivity.super.onBackPressed())
                .create()
                .show();
    }

    private void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.coordinator_main_activity), message, Snackbar.LENGTH_SHORT)
                .setAnchorView(fab)
                .show();
    }

    private void animatedFAB() {
        AnimatedVectorDrawable drawable = isTick ? tickToCross : crossToTick;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawable.registerAnimationCallback(new Animatable2.AnimationCallback() {
                @Override
                public void onAnimationStart(Drawable drawable) {
                    super.onAnimationStart(drawable);
                }

                @Override
                public void onAnimationEnd(Drawable drawable) {
                    super.onAnimationEnd(drawable);
                }
            });
        }
        fab.setImageDrawable(drawable);
        drawable.start();
        isTick = !isTick;
    }

    @Override
    public void onFragmentInteraction() {
        setupBottomAppBarSecondary();
    }

}
