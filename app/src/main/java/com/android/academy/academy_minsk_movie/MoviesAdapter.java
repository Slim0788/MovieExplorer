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

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private List<Movie> listOfItems;

    MoviesAdapter(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        listOfItems = DataStorage.getInstance().getMovieList();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ADVERTISING_POSITION:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movies_recycler_view_advertising, parent, false));
            default:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movies_recycler_view_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case ADVERTISING_POSITION:

                break;
            default:
                Movie movie = listOfItems.get(position);

                holder.title.setText(movie.getTitle());
                holder.overview.setText(movie.getOverview());
                Glide.with(holder.poster)
                        .load(movie.getPosterRes())
                        .into(holder.poster);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listOfItems.size();
    }

    final class ViewHolder extends RecyclerView.ViewHolder {

        final ImageView poster;
        final TextView title;
        final TextView overview;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.movies_item_iv_poster);
            title = itemView.findViewById(R.id.movies_item_tv_title);
            overview = itemView.findViewById(R.id.movies_item_tv_overview_text);

            setListeners();
        }

        private void setListeners() {
            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (itemClickListener != null && position != RecyclerView.NO_POSITION) {
                    itemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

