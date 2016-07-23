package com.akshatjain.codepath.flicks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.akshatjain.codepath.flicks.Constants;
import com.akshatjain.codepath.flicks.R;
import com.akshatjain.codepath.flicks.data.Movie;
import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetails extends AppCompatActivity {

    @BindView(R.id.txtMovieDesc)
    TextView movieDesc;

    @BindView(R.id.txtTitleDetail)
    TextView title;

    @BindView(R.id.ratingBar)
    RatingBar votes;

    @BindView(R.id.imgPosterDetails)
    ImageView poster;

    @BindView(R.id.txtReleaseDate)
    TextView releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        Movie movie = (Movie) getIntent().getSerializableExtra("MovieDetails");

        movieDesc.setText(movie.getDescription());
        title.setText(movie.getName());
        Date rDate = movie.getReleaseDate();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        releaseDate.setText(sdf.format(rDate));

        votes.setMax(10);
        votes.setStepSize(0.1f);
        Log.d(Constants.TAG,"Rating ==" + movie.getVoteAverage());
        votes.setRating(movie.getVoteAverage());
        votes.setEnabled(false);
        Glide.with(this).load(Constants.IMAGE_URL + movie.getBackdropURL())
                .placeholder(R.mipmap.popcorn)
                .crossFade().into(poster);
    }
}
