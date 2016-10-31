package com.example.srk.openmoviedatabase.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Raghu on 10/30/2016.
 */

public class CompleteMoviesList implements Serializable {

    private static final long serialVersionUID = 7526476285954676147L;

    private List<Movie> completeMoviesList;

    public List<Movie> getCompleteMoviesList() {
        return completeMoviesList;
    }

    public void setCompleteMoviesList(List<Movie> completeMoviesList) {
        this.completeMoviesList = completeMoviesList;
    }
}
