package com.akshatjain.codepath.flicks.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Collection of Movie
 * Created by akshatjain on 7/18/16.
 */

public class Movies {

    @SerializedName("results")
    List<Movie> movies;

    public Movies() {
        this.movies = new ArrayList<>();
    }

    public static Movies parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        Movies playingMovie = gson.fromJson(response, Movies.class);
        return playingMovie;
    }

    public List<Movie> getMovies() {
        return movies;
    }

}
