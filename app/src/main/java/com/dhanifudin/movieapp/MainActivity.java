package com.dhanifudin.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dhanifudin.movieapp.adapter.ResultAdapter;
import com.dhanifudin.movieapp.generator.ServiceGenerator;
import com.dhanifudin.movieapp.model.Movie;
import com.dhanifudin.movieapp.model.Result;
import com.dhanifudin.movieapp.services.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
	implements ResultAdapter.OnAdapterClickListener {

	public static final String API_KEY = "92a6872b881a784c517a23a53cecee24";
	private static final String TAG = MainActivity.class.getName();

	RecyclerView recyclerMovies;
	ResultAdapter resultAdapter;
	MovieService service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerMovies = findViewById(R.id.recycler_movies);
		resultAdapter = new ResultAdapter();
		resultAdapter.setListener(this);

		RecyclerView.LayoutManager layoutManager =
			new GridLayoutManager(this, 3);
		recyclerMovies.setLayoutManager(layoutManager);
		recyclerMovies.setAdapter(resultAdapter);

		service = ServiceGenerator.createService(MovieService.class);

		Call<Movie> movieResponse = service.getNowPlaying(API_KEY);
		movieResponse.enqueue(new Callback<Movie>() {
			@Override
			public void onResponse(Call<Movie> call, Response<Movie> response) {
				Movie movie = response.body();
				if (movie != null) {
					resultAdapter.setResults(movie.getResults());
					Log.i(TAG, movie.toString());
				}
			}

			@Override
			public void onFailure(Call<Movie> call, Throwable t) {
				Log.e(TAG, t.toString());
			}
		});
	}

	@Override
	public void onClick(Result result) {
		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra("MOVIE", result);
		startActivity(intent);
	}
}
