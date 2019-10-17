package com.android.academy.academy_minsk_movie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class MoviesFragment extends Fragment /*implements MoviesAdapter.OnItemClickListener*/ {

    private static final String ADVERTISING_URL = "https://www.youtube.com/playlist?list=PLH434_oX84wNp7NYYdFV5JqJozpWXIjlA";

    private RecyclerView recyclerView;

//    private OnFragmentInteractionListener fragmentInteractionListener;
//
//    interface OnFragmentInteractionListener {
//        // Слушатель для переключения Navigation icon на AppBar
//        void onFragmentInteraction();
//    }

    static Fragment newInstance() {
        // Возвращаем экземпляр фрагмента MoviesFragment

        return new MoviesFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        try {
//            fragmentInteractionListener = (OnFragmentInteractionListener) context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString()
//                    + " interface OnFragmentInteractionListener must be implemented");
//        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        // Находим recyclerView на макете фрагмента
        recyclerView = view.findViewById(R.id.movies_recycler_view);

        // Для конечного числа элементов в списке ставим значение true для увеличения
        // производительности. Не подходит для бесконечных подгружаемых списков.
        recyclerView.setHasFixedSize(true);

        // Создаём адаптер для recyclerView и передаём ему в конструктор слушатель кликов
        MoviesAdapter adapter = new MoviesAdapter(this);
        recyclerView.setAdapter(adapter);

        // Ограничиваем pool для view типа реклама, т.к. такого типа view у нас в единственном
        // экземпляре и нам незачем хранить её в памяти. По-умолчанию в пуле хранится 5 view.
        recyclerView.getRecycledViewPool().setMaxRecycledViews(MoviesAdapter.ITEM_VIEW_TYPE_ADVERTISING, 1);

        prepareTransitions();
        postponeEnterTransition();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        scrollToPosition();
    }

//    @Override
//    public void onItemClick(int position) {
//        fragmentInteractionListener.onFragmentInteraction();
//        // Пользователь выбрал фильм из фрагмента MoviesFragment.
//
//        // Создаём фрагмент и даём ему аргументы (номер позиции) для выбранного фильма
//        Fragment galleryDetailsFragment = GalleryDetailsFragment.newInstance(position);
//
//        // Замените все, что находится в представлении 'container' FrameLayout на этот фрагмент,
//        // и добавьте транзакцию в back stack, чтобы пользователь мог вернуться назад
//        FragmentManager manager = getFragmentManager();
//        if (manager != null) {
//            manager.beginTransaction()
//                    .addToBackStack(null)
//                    .replace(R.id.container, galleryDetailsFragment)
//                    .commit();
//        }
//
//    }
//
//    @Override
//    public void onAdvertisingClick() {
//        // Пользователь выбрал рекламу из фрагмента MoviesFragment.
//
//        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(ADVERTISING_URL)));
//    }

    /**
     * Scrolls the recycler view to show the last viewed item in the grid. This is important when
     * navigating back from the grid.
     */
    private void scrollToPosition() {
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left,
                                       int top,
                                       int right,
                                       int bottom,
                                       int oldLeft,
                                       int oldTop,
                                       int oldRight,
                                       int oldBottom) {
                recyclerView.removeOnLayoutChangeListener(this);
                final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                View viewAtPosition = layoutManager.findViewByPosition(MainActivity.currentPosition);
                // Scroll to position if the view for the current position is null (not currently part of
                // layout manager children), or it's not completely visible.
                if (viewAtPosition == null || layoutManager
                        .isViewPartiallyVisible(viewAtPosition, false, true)) {
                    recyclerView.post(() -> layoutManager.scrollToPosition(MainActivity.currentPosition));
                }
            }
        });
    }

    /**
     * Prepares the shared element transition to the pager fragment, as well as the other transitions
     * that affect the flow.
     */
    private void prepareTransitions() {
        setExitTransition(TransitionInflater.from(getContext())
                .inflateTransition(R.transition.grid_exit_transition));

        // A similar mapping is set at the ImagePagerFragment with a setEnterSharedElementCallback.
        setExitSharedElementCallback(
                new SharedElementCallback() {
                    @Override
                    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
                        // Locate the ViewHolder for the clicked position.
                        RecyclerView.ViewHolder selectedViewHolder = recyclerView
                                .findViewHolderForAdapterPosition(MainActivity.currentPosition);
                        if (selectedViewHolder == null || selectedViewHolder.itemView == null) {
                            return;
                        }

                        // Map the first shared element name to the child ImageView.
                        sharedElements
                                .put(names.get(0), selectedViewHolder.itemView.findViewById(R.id.movies_item_iv_poster));
                    }
                });
    }

}
