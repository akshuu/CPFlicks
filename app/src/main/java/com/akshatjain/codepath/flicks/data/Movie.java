package com.akshatjain.codepath.flicks.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akshatjain on 7/16/16.
 */
public class Movie implements Serializable {

    private int id;

    @SerializedName("title")
    private String name;

    @SerializedName("overview")
    private String description;

    @SerializedName("vote_average")
    private float rating;

    @SerializedName("poster_path")
    private String posterURL;

    @SerializedName("backdrop_path")
    private String backdropURL;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getBackdropURL() {
        return backdropURL;
    }

    public void setBackdropURL(String backdropURL) {
        this.backdropURL = backdropURL;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", posterURL='" + posterURL + '\'' +
                ", backdropURL='" + backdropURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (rating != movie.rating) return false;
        if (!name.equals(movie.name)) return false;
        if (!description.equals(movie.description)) return false;
        if (posterURL != null ? !posterURL.equals(movie.posterURL) : movie.posterURL != null)
            return false;
        return backdropURL != null ? backdropURL.equals(movie.backdropURL) : movie.backdropURL == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = (int) (31 * result + rating);
        result = 31 * result + (posterURL != null ? posterURL.hashCode() : 0);
        result = 31 * result + (backdropURL != null ? backdropURL.hashCode() : 0);
        return result;
    }
}
