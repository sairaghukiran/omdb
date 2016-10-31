package com.example.srk.openmoviedatabase.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.srk.openmoviedatabase.R;
import com.example.srk.openmoviedatabase.model.Movie;

import java.util.List;

/**
 * Created by Raghu on 10/30/2016.
 */

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> moviesList;

    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View moviesView = layoutInflater.inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(moviesView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Movie movieItem = moviesList.get(position);

        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;
        int itemNumber = position + 1;
        movieViewHolder.getItemPosition().setText(String.valueOf(itemNumber));
        movieViewHolder.getMovieTitle().setText(movieItem.getTitle());
        movieViewHolder.getYearReleased().setText(movieItem.getYear());
    }

    @Override
    public int getItemCount() {
        if(moviesList == null) return 0;
        return moviesList.size();
    }

    public void setMovies(List<Movie> moviesList) {
        int previousItemCount = getItemCount();
        this.moviesList = moviesList;
        notifyItemRangeInserted(previousItemCount, 10);
    }

    private class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView itemPosition;
        private TextView movieTitle;
        private TextView yearReleased;

        public MovieViewHolder(View itemView) {
            super(itemView);

            itemPosition = (TextView) itemView.findViewById(R.id.position);
            movieTitle = (TextView) itemView.findViewById(R.id.movie_title);
            yearReleased = (TextView) itemView.findViewById(R.id.year_released);
        }

        public TextView getItemPosition() {
            return itemPosition;
        }

        public TextView getMovieTitle() {
            return movieTitle;
        }

        public TextView getYearReleased() {
            return yearReleased;
        }
    }
}
