package com.android.academy.academy_minsk_movie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class MoviesFragment extends Fragment implements MoviesAdapter.OnItemClickListener {

    private static final String ADVERTISING_URL = "https://www.youtube.com/playlist?list=PLH434_oX84wNp7NYYdFV5JqJozpWXIjlA";

    static Fragment newInstance() {
        // Возвращаем экземпляр фрагмента MoviesFragment

        return new MoviesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Находим recyclerView на макете фрагмента
        RecyclerView recyclerView = view.findViewById(R.id.movies_recycler_view);

        // Для конечного числа элементов в списке ставим значение true для увеличения
        // производительности. Не подходит для бесконечных подгружаемых списков.
        recyclerView.setHasFixedSize(true);

        // Создаём адаптер для recyclerView и передаём ему в конструктор слушатель кликов
        MoviesAdapter adapter = new MoviesAdapter(this);
        recyclerView.setAdapter(adapter);

        // Ограничиваем pool для view типа реклама, т.к. такого типа view у нас в единственном
        // экземпляре и нам незачем хранить её в памяти. По-умолчанию в пуле хранится 5 view.
        recyclerView.getRecycledViewPool().setMaxRecycledViews(MoviesAdapter.ITEM_VIEW_TYPE_ADVERTISING, 1);

    }

    @Override
    public void onItemClick(int position) {
        // Пользователь выбрал фильм из фрагмента MoviesFragment.

        // Создаём фрагмент и даём ему аргументы (номер позиции) для выбранного фильма
        Fragment detailsFragment = DetailsFragment.newInstance(position);

        // Замените все, что находится в представлении 'container' FrameLayout на этот фрагмент,
        // и добавьте транзакцию в back stack, чтобы пользователь мог вернуться назад
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, detailsFragment)
                .commit();
    }

    @Override
    public void onAdvertisingClick() {
        // Пользователь выбрал рекламу из фрагмента MoviesFragment.

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ADVERTISING_URL)));
    }

}
