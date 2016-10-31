package com.example.srk.openmoviedatabase.activity;

import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.srk.openmoviedatabase.R;
import com.example.srk.openmoviedatabase.adapter.EndlessScrollListener;
import com.example.srk.openmoviedatabase.adapter.MoviesAdapter;
import com.example.srk.openmoviedatabase.model.CompleteMoviesList;
import com.example.srk.openmoviedatabase.model.Movie;
import com.example.srk.openmoviedatabase.model.MoviesList;
import com.example.srk.openmoviedatabase.retrofitapi.OnlineMoviewDBAPI;
import com.example.srk.openmoviedatabase.utils.ListUtils;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayMoviesActivity extends AppCompatActivity implements Callback<MoviesList>{

    private EditText searchBox;
    private RecyclerView recyclerView;
    private CompleteMoviesList completeMoviesList;
    private MoviesAdapter moviesAdapter;
    private EndlessScrollListener scrollListener;

    private static final String OMDB_BASE_URL = "http://www.omdbapi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        View searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(getSearchButtonClickListener());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        scrollListener = new RecyclerEndlessScrollListener(layoutManager);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(scrollListener);
        searchBox = (EditText) findViewById(R.id.search_box);
        completeMoviesList = new CompleteMoviesList();
    }

    class RecyclerEndlessScrollListener extends EndlessScrollListener {

        RecyclerEndlessScrollListener(RecyclerView.LayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public void onLoadMore(int page, int totalItemCount, RecyclerView recyclerView) {
            performSearch(page, false);
        }
    }

    private View.OnClickListener getSearchButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollListener.resetState();
                performSearch(1, true);
            }
        };
    }

    private void performSearch(int page, boolean newSearch) {

        if(newSearch) {
            resetAdapter();
        }

        String searchTerm = searchBox.getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OnlineMoviewDBAPI omdbApi = retrofit.create(OnlineMoviewDBAPI.class);

        Call<MoviesList> retrofitCall = omdbApi.getMovies(searchTerm, page);
        retrofitCall.enqueue(DisplayMoviesActivity.this);
    }

    private void resetAdapter() {
        recyclerView.setAdapter(null);
        completeMoviesList.setCompleteMoviesList(null);
    }

    @Override
    public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
        if(response.isSuccessful()) Log.i("Response: ", response.isSuccessful()?"Successful":"Not successful");
        MoviesList moviesList = response.body();
        if(moviesList != null && moviesList.getMovies()!= null) {
            List<Movie> moviesInCurrentPage = moviesList.getMovies();
            if(ListUtils.isEmpty(completeMoviesList.getCompleteMoviesList())) {
                completeMoviesList.setCompleteMoviesList(moviesInCurrentPage);
            } else {
                completeMoviesList.getCompleteMoviesList().addAll(moviesInCurrentPage);
            }
        }
        if(ListUtils.isEmpty(completeMoviesList.getCompleteMoviesList())) {
            View rootView = findViewById(R.id.activity_display_movies);
            Snackbar.make(rootView, R.string.error_msg, Snackbar.LENGTH_LONG).show();
            return;
        }
        if(recyclerView.getAdapter() == null) {
           moviesAdapter = new MoviesAdapter(completeMoviesList.getCompleteMoviesList());
            recyclerView.setAdapter(moviesAdapter);
        } else {
            moviesAdapter.setMovies(completeMoviesList.getCompleteMoviesList());
        }
    }

    @Override
    public void onFailure(Call<MoviesList> call, Throwable t) {
        View rootView = findViewById(R.id.activity_display_movies);
        Snackbar.make(rootView, R.string.error_msg, Snackbar.LENGTH_LONG).show();
    }
}
