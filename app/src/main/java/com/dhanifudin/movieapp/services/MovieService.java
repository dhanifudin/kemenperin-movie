package com.dhanifudin.movieapp.services;

import com.dhanifudin.movieapp.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
	@GET("/3/movie/now_playing")
	Call<Movie> getNowPlaying(@Query("api_key") String apiKey);
}
