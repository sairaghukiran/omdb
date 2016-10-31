package com.example.srk.openmoviedatabase.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.srk.openmoviedatabase.R;
import com.example.srk.openmoviedatabase.adapter.MoviesAdapter;
import com.example.srk.openmoviedatabase.model.MoviesList;
import com.example.srk.openmoviedatabase.retrofitapi.OnlineMoviewDBAPI;
import com.example.srk.openmoviedatabase.utils.ListUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayMoviesActivity extends AppCompatActivity implements Callback<MoviesList>{

    private EditText searchBox;
    private RecyclerView recyclerView;

    private static final String OMDB_BASE_URL = "http://www.omdbapi.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movies);

        View searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(getSearchButtonClickListener());
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchBox = (EditText) findViewById(R.id.search_box);
    }

    private View.OnClickListener getSearchButtonClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchTerm = searchBox.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(OMDB_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OnlineMoviewDBAPI omdbApi = retrofit.create(OnlineMoviewDBAPI.class);

                Call<MoviesList> retrofitCall = omdbApi.getMovies(searchTerm);
                retrofitCall.enqueue(DisplayMoviesActivity.this);
            }
        };
    }

    @Override
    public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
        if(response.isSuccessful()) Log.i("Response: ", response.isSuccessful()?"Successful":"Not successful");
        MoviesList moviesList = response.body();
        if(moviesList == null || ListUtils.isEmpty(moviesList.getMovie())) {
            // show empty view
            return;
        }
        MoviesAdapter moviesAdapter = new MoviesAdapter(moviesList.getMovie());
        recyclerView.setAdapter(moviesAdapter);
    }

    @Override
    public void onFailure(Call<MoviesList> call, Throwable t) {

    }
}
