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
    private List<Movie> movies;

    @SerializedName("totalResults")
    private String totalResults;

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }
}
