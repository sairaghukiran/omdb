package com.example.srk.openmoviedatabase.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Raghu on 10/30/2016.
 */

public class MoviesList {

    @SerializedName("Search")
    @Expose
    List<Movie> movie;

    public List<Movie> getMovie() {
        return movie;
    }

    public void setMovie(List<Movie> movie) {
        this.movie = movie;
    }
}
