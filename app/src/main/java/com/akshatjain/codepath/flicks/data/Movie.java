package com.akshatjain.codepath.flicks.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by akshatjain on 7/16/16.
 */
public class Movie implements Serializable {

    private int id;

    @SerializedName("title")
    private String name;

    @SerializedName("overview")
    private String description;

    @SerializedName("poster_path")
    private String posterURL;

    @SerializedName("backdrop_path")
    private String backdropURL;

    @SerializedName("vote_average")
    private float vote_average;

    @SerializedName("release_date")
    private Date releaseDate;


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
                ", posterURL='" + posterURL + '\'' +
                ", backdropURL='" + backdropURL + '\'' +
                ", vote_average='" + vote_average + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (vote_average != movie.vote_average) return false;
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
        result = (int) (31 * result + vote_average);
        result = 31 * result + (posterURL != null ? posterURL.hashCode() : 0);
        result = 31 * result + (backdropURL != null ? backdropURL.hashCode() : 0);
        return result;
    }

    public float getVoteAverage() {
        return vote_average;
    }

    public void setVoteAverage(float vote_average) {
        this.vote_average = vote_average;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
