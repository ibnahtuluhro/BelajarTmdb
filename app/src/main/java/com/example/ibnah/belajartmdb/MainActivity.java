package com.example.ibnah.belajartmdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.ibnah.belajartmdb.adapter.MovieAdapter;
import com.example.ibnah.belajartmdb.model.Movie;
import com.example.ibnah.belajartmdb.model.MovieResponse;
import com.example.ibnah.belajartmdb.network.ApiService;
import com.example.ibnah.belajartmdb.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private List<Movie>movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        initView();

        getNowPlaying();
    }

    private void initView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void getNowPlaying() {
        ApiService service = RetrofitClient.retrofit().create(ApiService.class);
        Call<MovieResponse> call = service.getPopular();
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                Log.e("Data", "onresponse");
                movieList = movieResponse.getResults();

                for (int i = 0; i < movieList.size(); i++) {
                    Movie movie = movieList.get(i);

                    Log.i("Data", "result : " + movie.getTitle());
                }
                movieAdapter = new MovieAdapter(getApplicationContext(),movieList);
                recyclerView.setAdapter(movieAdapter);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

}
