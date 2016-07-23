package com.akshatjain.codepath.flicks.adapter;

import android.content.Context;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = movies.getMovies().get(position);
        if (movie.getVoteAverage() < 5f) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Movie movie = movies.getMovies().get(i);
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        if(movie.getVoteAverage() < 5f) {
            MovieHolder holder;
            if (view != null) {
                holder = (MovieHolder) view.getTag();
            } else {
                view = inflater.inflate(R.layout.movie_item, viewGroup, false);
                holder = new MovieHolder(view);
                view.setTag(holder);
            }
            holder.title.setText(movie.getName());
            holder.desc.setText(movie.getDescription());
            Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            int displayRotation = display.getRotation();
            String imageUrl = "";
            switch (displayRotation) {
                case Surface.ROTATION_0:
                case Surface.ROTATION_180:
                    imageUrl = Constants.IMAGE_URL + movie.getPosterURL();
                    break;
                case Surface.ROTATION_90:
                case Surface.ROTATION_270:
                    imageUrl = Constants.IMAGE_URL + movie.getBackdropURL();
                    break;
            }
            Glide.with(mContext).load(imageUrl)
                    .placeholder(R.mipmap.popcorn)
                    .fitCenter().crossFade().into(holder.poster);
        }
        else{
            // popular movies will show only the backdrop image
            PopularMovieHolder holder;
            if (view != null) {
                holder = (PopularMovieHolder) view.getTag();
            } else {
                view = inflater.inflate(R.layout.movie_item_popular, viewGroup, false);
            }
            holder = new PopularMovieHolder(view);
            view.setTag(holder);

            String imageUrl = "";
            imageUrl = Constants.IMAGE_URL + movie.getBackdropURL();

            Glide.with(mContext).load(imageUrl)
                    .placeholder(R.mipmap.popcorn)
                    .bitmapTransform(new RoundedCornersTransformation(mContext,10,10))
                    .crossFade().into(holder.poster);
        }

        return view;
    }

    static class PopularMovieHolder{
        @BindView(R.id.imgPoster)
        ImageView poster;

        public PopularMovieHolder(View view) {
            ButterKnife.bind(this, view);
        }
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
