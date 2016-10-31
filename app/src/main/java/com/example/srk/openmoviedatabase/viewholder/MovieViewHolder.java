package com.example.srk.openmoviedatabase.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.srk.openmoviedatabase.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
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