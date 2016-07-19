package com.akshatjain.codepath.flicks.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.akshatjain.codepath.flicks.Constants;
import com.akshatjain.codepath.flicks.R;
import com.akshatjain.codepath.flicks.data.Movie;
import com.akshatjain.codepath.flicks.data.Movies;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MovieLists extends AppCompatActivity {

    @BindView(R.id.listView) ListView movieList;
    @BindView(R.id.swipeContainer)
    SwipeRefreshLayout swipeRefreshLayout;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_lists);
        ButterKnife.bind(this);

//        RestClient client = RestApplication.getRestClient();
        client = new AsyncHttpClient();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(true);
            }
        });

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        fetchMovies(false);
    }

    private void fetchMovies(final boolean isRefresh) {
        RequestParams params = new RequestParams();
        String url = Constants.MOVIE_HOST+Constants.NOW_PLAYING_ENDPOINT + "/?api_key=" + Constants.API_KEY;
        Log.d(Constants.TAG,"URL == " + url);
        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(Constants.TAG,"Failed Response == " + responseString);
                if(isRefresh){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new GsonBuilder().create();
                // Define Response class to correspond to the JSON response returned
                Movies movies = Movies.parseJSON(responseString); //gson.fromJson(responseString, Movies.class);

                for(Movie movie : movies.getMovies())
                    Log.d(Constants.TAG,"movie == " + movie.toString());
                if(isRefresh){
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });
    }

    public void fetchTimelineAsync(final boolean isRefresh) {
        RequestParams params = new RequestParams();
        String url = Constants.MOVIE_HOST+Constants.NOW_PLAYING_ENDPOINT + "/?api_key=" + Constants.API_KEY;
        Log.d(Constants.TAG,"URL == " + url);
        client.setEnableRedirects(false);
        client.get(url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e(Constants.TAG,"Failed Response == " + responseString);
                if(isRefresh){
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new GsonBuilder().create();
                // Define Response class to correspond to the JSON response returned
                Movies movies = Movies.parseJSON(responseString); //gson.fromJson(responseString, Movies.class);

                for(Movie movie : movies.getMovies())
                    Log.d(Constants.TAG,"movie == " + movie.toString());
                if(isRefresh){
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });
    }

}
