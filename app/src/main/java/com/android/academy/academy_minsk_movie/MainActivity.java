package com.android.academy.academy_minsk_movie;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    BottomAppBar bottomAppBar;
    FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setupBottomAppBar();

        fab.setOnClickListener(v -> toggleBottomAppBar());

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
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);

    }

    private void init() {
        bottomAppBar = findViewById(R.id.bottomAppBar);
        fab = findViewById(R.id.floatingActionButton);
    }

    private void setupBottomAppBar() {
        bottomAppBar.replaceMenu(R.menu.app_bar_menu);
        bottomAppBar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.app_bar_search:
                    showSnackBar(getString(R.string.menu_item_search));
                    return true;
                case R.id.app_bar_mail:
                    showSnackBar(getString(R.string.menu_item_mail));
                    return true;
                case R.id.app_bar_delete:
                    showSnackBar(getString(R.string.menu_item_delete));
                    return true;
                case R.id.app_bar_archive:
                    showSnackBar(getString(R.string.menu_item_archive));
                    return true;
            }
            return false;
        });

        bottomAppBar.setNavigationOnClickListener(v -> showSnackBar(getString(R.string.content_description_navigation_drawer)));
    }

    private void toggleBottomAppBar() {
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

}
