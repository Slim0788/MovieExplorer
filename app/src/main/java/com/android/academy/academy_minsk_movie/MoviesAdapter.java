package com.android.academy.academy_minsk_movie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.academy.academy_minsk_movie.data.DataStorage;
import com.android.academy.academy_minsk_movie.data.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final OnItemClickListener itemClickListener;

    public static final int ADVERTISING_POSITION = 3;
    private static final int ITEM_VIEW_TYPE_MOVIES = 0;
    static final int ITEM_VIEW_TYPE_ADVERTISING = 1;

    public interface OnItemClickListener {
        /**
         * MoviesFragment должен реализовывать этот интерфейс, чтобы адаптер мог
         * сообщать фрагменту о кликах
         */
        void onItemClick(int position);

        void onAdvertisingClick();
    }

    private List<Movie> listOfItems;

    MoviesAdapter(OnItemClickListener itemClickListener) {

        // В конструктор адаптера передаём интерфейс для прослушки кликов
        this.itemClickListener = itemClickListener;

        // Инициализируем лист с фильмами
        listOfItems = DataStorage.getInstance().getMovieList();
    }

    @Override
    public int getItemViewType(int position) {

        // Проверяем была ли выбрана реклама из списка фильмов
        if (position != ADVERTISING_POSITION) {
            return ITEM_VIEW_TYPE_MOVIES;
        }
        return ITEM_VIEW_TYPE_ADVERTISING;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Проверяем для какого типа view нужно создать viewHolder
        if (viewType == ITEM_VIEW_TYPE_ADVERTISING) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movies_recycler_view_advertising, parent, false), viewType);
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_movies_recycler_view_item, parent, false), viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Если тип view не является рекламой, то биндим туда значения из viewHolder
        if (holder.getItemViewType() != ITEM_VIEW_TYPE_ADVERTISING) {
            Movie movie = listOfItems.get(position);

            holder.title.setText(movie.getTitle());
            holder.overview.setText(movie.getOverview());
            Glide.with(holder.poster)
                    .load(movie.getPosterRes())
                    .into(holder.poster);
        }
    }

    @Override
    public int getItemCount() {

        // Проверяем размер нашего списка фильмов
        return listOfItems.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView poster;
        private TextView title;
        private TextView overview;

        private ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);

            // Проверяем, если тип view не является рекламой, то инициализируем вьюхи
            if (viewType != ITEM_VIEW_TYPE_ADVERTISING) {
                poster = itemView.findViewById(R.id.movies_item_iv_poster);
                title = itemView.findViewById(R.id.movies_item_tv_title);
                overview = itemView.findViewById(R.id.movies_item_tv_overview_text);
            }

            // Ставим слушатель на элемент из списка recyclerView
            setListeners(itemView, viewType);
        }

        private void setListeners(View itemView, int viewType) {
            itemView.setOnClickListener(view -> {

                // Получаем значение позиции выбранного элемента
                int position = getAdapterPosition();

                // Проверяем слушатель и позицию элемента на валидность
                if (itemClickListener != null && position != RecyclerView.NO_POSITION) {

                    // Проверяем тип view...
                    if (viewType != ITEM_VIEW_TYPE_ADVERTISING) {

                        // Если это филь, то переходим на просмотр деталей фильма...
                        itemClickListener.onItemClick(getAdapterPosition());
                    } else {

                        // Если это реклама, то открываем YouTube
                        itemClickListener.onAdvertisingClick();
                    }
                }
            });
        }
    }
}

