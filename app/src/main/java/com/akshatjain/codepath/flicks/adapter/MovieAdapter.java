package com.akshatjain.codepath.flicks.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.akshatjain.codepath.flicks.Constants;
import com.akshatjain.codepath.flicks.R;
import com.akshatjain.codepath.flicks.data.Movie;
import com.akshatjain.codepath.flicks.data.Movies;
import com.bumptech.glide.Glide;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akshatjain on 7/16/16.
 */
public class MovieAdapter extends BaseAdapter{

    private Movies movies;
    private Context mContext;
    public MovieAdapter(Context context,Movies movies){
        this.mContext = context;
        this.movies = movies;
    }

    public MovieAdapter(Context context) {
        mContext = context;
        movies = new Movies();
    }

    public void setMovies(Movies movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.getMovies().size();
    }

    @Override
    public Object getItem(int i) {
        return movies.getMovies().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        MovieHolder holder;
        if (view != null) {
            holder = (MovieHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.movie_item, viewGroup, false);
            holder = new MovieHolder(view);
            view.setTag(holder);
        }
        Movie movie = movies.getMovies().get(i);
        holder.title.setText(movie.getName());
        holder.desc.setText(movie.getDescription());
        Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int displayRotation = display.getRotation();
        Log.e(Constants.TAG,"++++++ Orientation ==" + displayRotation);
        String imageUrl = "";
        switch(displayRotation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                imageUrl = Constants.IMAGE_URL + movie.getPosterURL();
                break;
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                imageUrl = Constants.IMAGE_URL + movie.getBackdropURL();
                break;
        }
        Glide.with(mContext).load(imageUrl).into(holder.poster);
        return view;
    }

    static class MovieHolder{
        @BindView(R.id.txtTitle)
        TextView title;
        @BindView(R.id.txtDesc) TextView desc;
        @BindView(R.id.imgPoster)
        ImageView poster;

        public MovieHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
