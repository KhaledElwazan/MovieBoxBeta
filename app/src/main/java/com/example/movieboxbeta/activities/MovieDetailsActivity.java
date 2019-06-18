package com.example.movieboxbeta.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.movieboxbeta.R;
import com.example.movieboxbeta.db.MovieDB;
import com.example.movieboxbeta.movies.GetDataService;
import com.example.movieboxbeta.movies.RetrofitClientInstance;
import com.example.movieboxbeta.movies.movie_details.MovieDetails;
import com.example.movieboxbeta.movies.movie_reviews.MovieReviews;
import com.example.movieboxbeta.movies.movie_reviews.Review;
import com.example.movieboxbeta.movies.movies_list.Movie;
import com.example.movieboxbeta.movies.videos.Results;
import com.example.movieboxbeta.movies.videos.Video;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private boolean isFavorited = false;
    private Context context;
    private FloatingActionButton favorite;
    private Movie movie;
    private ImageView poster;
    private ScrollView view;
    private WebView movieDetails;

    private MovieDB movieDB;

    private String data = "";

    // TODO: create gui and infrastructure for the MovieDetails Fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        this.context = this;


        Bundle b = getIntent().getExtras();
        movie = null;
        if (b != null) {
            movie = (Movie) b.getSerializable("movie");


            view = findViewById(R.id.scrollView);

            // setting movie poster
            poster = findViewById(R.id.posterInDetailsFragment);
            new AsyncTask<String, Void, Bitmap>() {

                @SuppressLint("LongLogTag")
                @Override
                protected Bitmap doInBackground(String... strings) {

                    Bitmap bitmap = null;

                    try {
                        InputStream inputStream = new URL(strings[0]).openStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);

                    } catch (IOException e) {
                        Log.e("loading poster in movie details", e.toString());
                    }

                    return bitmap;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    poster.setImageBitmap(bitmap);
                }
            }.execute(movie.getPosterURL());


            // setting movie title
            setTitle(movie.getTitle());

            //setting release date
            TextView rDate = findViewById(R.id.releaseDate), voteAvg = findViewById(R.id.voteAverage);

            rDate.setText(movie.getReleaseDate());
            voteAvg.setText(movie.getVoteAverage().toString());


            GetDataService service = RetrofitClientInstance.getRetrofitInstance(getString(R.string.baseURL)).create(GetDataService.class);

// the display view
            movieDetails = findViewById(R.id.movieDetails);


// loading details
            {

                Call<MovieDetails> call = service.getMovieDetails(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<MovieDetails>() {
                    @Override
                    public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {

                        MovieDetails movie = response.body();

                        if (response.isSuccessful()) {


                            data = "<hr><h4>Language: </h4>" + (movie.getOriginalLanguage() == null ? "NA" : movie.getOriginalLanguage());
                            data += "</br>" + "<h4>Adult: </h4>" + (movie.getAdult() ? "Yes" : "No");
                            data += "</br>" + "<h4>Overview: </h4>" + movie.getOverview() + "</br>";
                            data += "<h4>Homepage: </h4><a href=\"" + (movie.getHomepage() == null ? "NA" : movie.getHomepage()) + "\">" + (movie.getHomepage() == null ? "NA" : movie.getHomepage()) + "</a></br>";
                            data += "<h4>Duration: </h4>" + (movie.getRuntime() != null ? movie.getRuntime() : "NA") + " minutes</br>";
                            data += "<hr>";

                            movieDetails.loadData(data, "text/html", "utf-8");


                        }

                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                        Log.e("failed loading Mdetails", t.toString());

                    }
                });
            }


            {

                Call<MovieReviews> call = service.getMovieReviews(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<MovieReviews>() {
                    @Override
                    public void onResponse(Call<MovieReviews> call, Response<MovieReviews> response) {


                        if (response.isSuccessful()) {

                            if (response.body() != null) {

                                data += "<h4>Reviews</h4>";

                                List<Review> results = response.body().getResults();

                                for (Review review : results) {
                                    //System.out.println(review.toString());

                                    data += "<b>" + review.getAuthor() + "</b>";

                                    if (review.getContent().length() > 200)

                                        data += " <i>" + review.getContent().substring(0, 200) + "</i> ... <a href = \"" + review.getUrl() + "\"> read more!</a> <br><br>";
                                    else
                                        data += " <i>" + review.getContent() + "</i><br><br>";

                                }


                            } else {
                                data += "<h4>No reviews available!</h4>";
                            }
                            data += "<hr>";

                            movieDetails.loadData(data, "text/html", "utf-8");
                        }

                    }

                    @Override
                    public void onFailure(Call<MovieReviews> call, Throwable t) {

                        Log.e("failed loading reviews", t.toString());

                    }
                });
            }


            {
                Call<Results> call = service.getMovieVideos(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {


                        if (response.isSuccessful()) {

                            List<Video> videos = response.body().getResults();

                            data += "<h4>Trailers</h4><br>";

                            if (videos != null)
                                for (Video video : videos) {


                                    data += "<a href=\"http://" + video.getSite() + ".com/watch?v=" + video.getKey() + "\">" + video.getName().replaceAll("[^\\u0000-\\uFFFF]", "") + "</a><br>";


                                }
                            data += "<hr>";

                            movieDetails.loadData(data, "text/html", "utf-8");

                        }


                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {

                        Log.e("failed loading Mtrailer", t.toString());

                    }
                });


            }

            favorite = findViewById(R.id.favButton);
            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (movieDB == null)
                        movieDB = MovieDB.getInstance(context);

                    if (!isFavorited) {

                        Snackbar.make(view, "Added to favorite movie list", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_icon));
                        isFavorited = true;
                        movieDB.getMovieDBDao().insert(movie);

                    } else {
                        Snackbar.make(view, "Removed from favorite movie list", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_icon_hollow));
                        isFavorited = false;
                        movieDB.getMovieDBDao().delete(movie);
                    }
                    if (movieDB != null)
                        movieDB.cleanUp();
                }

            });


            if (movieDB == null)
                movieDB = MovieDB.getInstance(context);

            new AsyncTask<Movie, Void, Boolean>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                protected Boolean doInBackground(Movie... pars) {

                    List<Movie> results = movieDB.getMovieDBDao().getAll();

                    for (Movie t : results) {


                        if (t.equals(pars[0]))
                            return true;

                    }


                    return false;
                }


                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    isFavorited = aBoolean;

                    if (isFavorited) {
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_icon));
                    } else
                        favorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.fav_icon_hollow));

                    movieDB.cleanUp();
                }


            }.execute(movie);


        } else {// TODO error while loading movie details

        }


    }
}
