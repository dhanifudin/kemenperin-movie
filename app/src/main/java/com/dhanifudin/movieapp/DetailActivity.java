package com.dhanifudin.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dhanifudin.movieapp.model.Result;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getCanonicalName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        if (intent != null) {
            Result result = intent.getParcelableExtra("MOVIE");
            Log.i(TAG, result.toString());
        }
    }
}
