package com.example.srk.openmoviedatabase.retrofitapi;

import com.example.srk.openmoviedatabase.model.MoviesList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Raghu on 10/30/2016.
 */

public interface OnlineMoviewDBAPI {
    @GET("?y=&plot=short&r=json")
    Call<MoviesList> getMovies(@Query("s") String searchTerm);
}
