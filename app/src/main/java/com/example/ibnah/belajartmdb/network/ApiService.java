package com.example.ibnah.belajartmdb.network;

import android.graphics.Movie;

import com.example.ibnah.belajartmdb.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    String API_KEY = "6d90617eed798d4678c51320e816bae1";

    @GET("movie/popular?api_key=" + API_KEY)
    Call<MovieResponse> getPopular();

}
