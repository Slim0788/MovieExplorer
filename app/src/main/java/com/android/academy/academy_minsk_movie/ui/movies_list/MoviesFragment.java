package com.android.academy.academy_minsk_movie.ui.movies_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.academy.academy_minsk_movie.R;
import com.android.academy.academy_minsk_movie.data.DataStorage;
import com.android.academy.academy_minsk_movie.data.model.Movie;
import com.android.academy.academy_minsk_movie.ui.gallery_movies_details.GalleryDetailsFragment;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment implements
        MoviesAdapter.OnItemClickListener,
        DataStorage.OnResponseListener {

    private OnFragmentInteractionListener fragmentInteractionListener;

    private ProgressBar progressBar;
    private List<Movie> movies;
    private RecyclerView recyclerView;

    public interface OnFragmentInteractionListener {
        // Слушатель для переключения Navigation icon на AppBar
        void onFragmentInteraction();
    }

    public static Fragment newInstance() {
        // Возвращаем экземпляр фрагмента MoviesFragment

        return new MoviesFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            fragmentInteractionListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " interface OnFragmentInteractionListener must be implemented");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = view.findViewById(R.id.movies_progressBar);

        movies = new ArrayList<>();

        // Находим recyclerView на макете фрагмента
        recyclerView = view.findViewById(R.id.movies_recycler_view);

        // Для конечного числа элементов в списке ставим значение true для увеличения
        // производительности. Не подходит для бесконечных подгружаемых списков.
        recyclerView.setHasFixedSize(true);

        // Создаём адаптер для recyclerView и передаём ему в конструктор слушатель кликов
        MoviesAdapter adapter = new MoviesAdapter(this, movies);
        recyclerView.setAdapter(adapter);

        DataStorage.getInstance().getMoviesList(this);

    }

    @Override
    public void onItemClick(int position) {
        fragmentInteractionListener.onFragmentInteraction();
        // Пользователь выбрал фильм из фрагмента MoviesFragment.

        // Создаём фрагмент и даём ему аргументы (номер позиции) для выбранного фильма
        Fragment galleryDetailsFragment = GalleryDetailsFragment.newInstance(position);

        // Замените все, что находится в представлении 'container' FrameLayout на этот фрагмент,
        // и добавьте транзакцию в back stack, чтобы пользователь мог вернуться назад
        FragmentManager manager = getFragmentManager();
        if (manager != null) {
            manager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container, galleryDetailsFragment)
                    .commit();
        }

    }

    @Override
    public void updateMovieList(List<Movie> responseMovieList) {
        movies.addAll(responseMovieList);
        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
        progressBar.setVisibility(View.GONE);
    }

}
