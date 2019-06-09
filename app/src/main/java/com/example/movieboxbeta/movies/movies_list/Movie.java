
package com.example.movieboxbeta.movies.movies_list;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


@Entity
public class Movie implements Serializable {

    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @ColumnInfo(name = "id")
    @SerializedName("id")
    @Expose
    private Integer id;
    @ColumnInfo(name = "video")
    @SerializedName("video")
    @Expose
    private Boolean video;
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    @PrimaryKey
    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    @NonNull
    private String title;
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @TypeConverters({DataConverter.class})
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> genreIds = null;
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    @Expose
    private String overview;
    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    @Expose
    private String releaseDate;


    @ColumnInfo(name = "posterURL")
    private String posterURL;

    @Override
    public String toString() {
        return "Result{" +
                "voteCount=" + voteCount +
                ", id=" + id +
                ", video=" + video +
                ", voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", popularity=" + popularity +
                ", posterPath='" + posterPath + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", genreIds=" + genreIds +
                ", backdropPath='" + backdropPath + '\'' +
                ", adult=" + adult +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", posterURL='" + posterURL + '\'' +
                '}';
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Movie withVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie withId(Integer id) {
        this.id = id;
        return this;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Movie withVideo(Boolean video) {
        this.video = video;
        return this;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Movie withVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Movie withTitle(String title) {
        this.title = title;
        return this;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie result = (Movie) o;
        return Objects.equals(getVoteCount(), result.getVoteCount()) &&
                Objects.equals(getId(), result.getId()) &&
                Objects.equals(getVideo(), result.getVideo()) &&
                Objects.equals(getVoteAverage(), result.getVoteAverage()) &&
                Objects.equals(getTitle(), result.getTitle()) &&
                Objects.equals(getPopularity(), result.getPopularity()) &&
                Objects.equals(getPosterPath(), result.getPosterPath()) &&
                Objects.equals(getOriginalLanguage(), result.getOriginalLanguage()) &&
                Objects.equals(getOriginalTitle(), result.getOriginalTitle()) &&
                Objects.equals(getGenreIds(), result.getGenreIds()) &&
                Objects.equals(getBackdropPath(), result.getBackdropPath()) &&
                Objects.equals(getAdult(), result.getAdult()) &&
                Objects.equals(getOverview(), result.getOverview()) &&
                Objects.equals(getReleaseDate(), result.getReleaseDate()) &&
                Objects.equals(getPosterURL(), result.getPosterURL());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(getVoteCount(), getId(), getVideo(), getVoteAverage(), getTitle(), getPopularity(), getPosterPath(), getOriginalLanguage(), getOriginalTitle(), getGenreIds(), getBackdropPath(), getAdult(), getOverview(), getReleaseDate(), getPosterURL());
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Movie withPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Movie withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Movie withOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public Movie withOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public Movie withGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
        return this;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Movie withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Movie withAdult(Boolean adult) {
        this.adult = adult;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Movie withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Movie withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }


}