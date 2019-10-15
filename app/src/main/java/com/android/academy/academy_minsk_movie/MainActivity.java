package com.android.academy.academy_minsk_movie;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private BottomAppBar bottomAppBar;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupBottomAppBar();

        fab.setOnClickListener(v -> toggleFAB());

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        return true;

    }


    @Override
    public void onBackPressed() {
        // Пользователь нажал на кнопку "назад"

        // Проверяем, есть ли еще фрагменты в back stack.
        // Если back stack пуст, то выводим диалог.
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle(getString(R.string.exit_alert_title))
                    .setMessage(getString(R.string.exit_alert_message))
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, (arg0, arg1) ->
                            MainActivity.super.onBackPressed()).create().show();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            setupBottomAppBar();
            super.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

    private void init() {
        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab = findViewById(R.id.floatingActionButton);
    }

    private void setupBottomAppBar() {

        setSupportActionBar(bottomAppBar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


//        bottomAppBar.replaceMenu(R.menu.app_bar_menu);
        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.app_bar_search:
                    showSnackBar(getString(R.string.menu_item_search));
                    return true;
                case R.id.app_bar_delete:
                    showSnackBar(getString(R.string.menu_item_delete));
                    return true;
//                case R.id.app_bar_turn_left:
//                    showToast(getString(R.string.menu_item_turn_left));
//                    return true;
//                case R.id.app_bar_turn_right:
//                    showToast(getString(R.string.menu_item_turn_right));
//                    return true;
            }
            return false;
        });

//        bottomAppBar.setNavigationOnClickListener(v -> {
//            BottomSheetDialogFragment fragment = ThreadsBottomSheetDialog.newInstance();
//            fragment.show(getSupportFragmentManager(), ThreadsBottomSheetDialog.TAG);
//        });
    }

    public void toggleFAB() {
        if (bottomAppBar.getFabAlignmentMode() == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            showSnackBar("FAB_ALIGNMENT_MODE_END");
        } else {
            bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            showSnackBar("FAB_ALIGNMENT_MODE_CENTER");
        }
    }

    private void showSnackBar(String message) {
        Snackbar.make(findViewById(R.id.coordinator_main_activity), message, Snackbar.LENGTH_SHORT)
                .setAnchorView(fab)
                .show();
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
