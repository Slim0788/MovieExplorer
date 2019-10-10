package com.android.academy.academy_minsk_movie;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

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

}
