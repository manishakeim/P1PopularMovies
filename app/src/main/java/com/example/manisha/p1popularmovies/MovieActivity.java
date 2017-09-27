package com.example.manisha.p1popularmovies;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linearlistview.LinearListView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manisha on 9/20/2017.
 */

public class MovieActivity extends AppCompatActivity {

    public final String apiKey = BuildConfig.POPULAR_MOVIES_API_KEY;
    private final String LOG_TAG = MovieActivity.class.getSimpleName();

    final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    private Toast mToast;
    private String movieID;
    private String movieName;
    private String movieDesc;
    private String movieRate;
    private String movieDate;
    private String movieImage;

    @BindView(R.id.movie_image) ImageView movieImageView;
    @BindView(R.id.movie_date) TextView movieDateView;
    @BindView(R.id.movie_rating) TextView movieRatingView;
    @BindView(R.id.movie_desc) TextView movieDescView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);


        String movie = getIntent().getStringExtra("movie");
        try {
            JSONObject obj = new JSONObject(movie);

            movieID = "" + obj.optString("id");
            movieName = "" + obj.optString("name");
            if (obj.optString("desc") != null) {
                movieDesc = "" + obj.optString("desc");
            }
            if (obj.optString("rating") != null) {
                movieRate = "" + obj.optString("rating") + "/10";
            }
            if (obj.optString("date") != null) {
                movieDate = "" + obj.optString("date");
            }
            movieImage = "" + obj.optString("image");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fetchAndShowMovies();

        setTitle(movieName);
        movieDescView.setText(movieDesc);
        movieRatingView.setText(movieRate);
        movieDateView.setText(movieDate);
        String finalImageURL = IMAGE_BASE_URL + movieImage;
        Picasso.with(getApplicationContext())
                .load(finalImageURL)
                .error(R.drawable.no_image_available)
                .placeholder(R.drawable.loading)
                .into(movieImageView);
    }

    public void fetchAndShowMovies() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}