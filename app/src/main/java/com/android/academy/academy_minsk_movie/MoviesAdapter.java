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

    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private List<Movie> listOfItems;

    MoviesAdapter(OnItemClickListener listener) {
        this.listener = listener;
        listOfItems = DataStorage.getInstance().getMovieList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.activity_movies_recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = listOfItems.get(position);

        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        Glide.with(holder.poster)
                .load(movie.getPosterRes())
                .into(holder.poster);
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

            itemView.setOnClickListener(view -> listener.onItemClick(getAdapterPosition()));
//            setListeners();
        }

        private void setListeners() {
            if (listener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    itemView.setOnClickListener(view -> listener.onItemClick(position));
                }
            }
        }
    }
}
