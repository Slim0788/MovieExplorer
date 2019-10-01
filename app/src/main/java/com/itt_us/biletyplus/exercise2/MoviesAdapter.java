package com.itt_us.biletyplus.exercise2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itt_us.biletyplus.exercise2.data.DataStorage;
import com.itt_us.biletyplus.exercise2.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> listOfItems;

    MoviesAdapter() {
        listOfItems = DataStorage.getInstance().getMovieList();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.activity_movies_recycler_view_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = listOfItems.get(position);

        holder.overview.setText(movie.getTitle());
        holder.title.setText(movie.getOverview());

//        Glide.with(holder.poster)
//                .load("main_image")
//                .into(holder.poster);
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
        }
    }
}
