package com.android.academy.academy_minsk_movie.ui.movies_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.academy.academy_minsk_movie.R;
import com.android.academy.academy_minsk_movie.data.Movie;
import com.bumptech.glide.Glide;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private final OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        /**
         * MoviesFragment должен реализовывать этот интерфейс, чтобы адаптер мог
         * сообщать фрагменту о кликах
         */
        void onItemClick(int position);
    }

    private List<Movie> listOfItems;

    MoviesAdapter(OnItemClickListener itemClickListener, List<Movie> listOfItems) {

        // В конструктор адаптера передаём интерфейс для прослушки кликов
        this.itemClickListener = itemClickListener;

        // Инициализируем лист с фильмами
        this.listOfItems = listOfItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movies_recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = listOfItems.get(position);
        holder.onBind(movie);
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

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.movies_item_iv_poster);
            title = itemView.findViewById(R.id.movies_item_tv_title);
            overview = itemView.findViewById(R.id.movies_item_tv_overview_text);
            setListeners(itemView);
        }

        private void onBind(Movie movie) {
            Glide.with(poster)
                    .load(movie.getPosterRes())
                    .into(poster);
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
        }

        private void setListeners(View itemView) {
            itemView.setOnClickListener(view -> {

                // Получаем значение позиции выбранного элемента
                int position = getAdapterPosition();

                // Проверяем слушатель и позицию элемента на валидность
                if (itemClickListener != null && position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(position);
                }
            });
        }

    }
}

