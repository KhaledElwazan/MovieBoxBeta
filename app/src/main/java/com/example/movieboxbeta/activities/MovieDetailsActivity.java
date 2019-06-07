package com.example.movieboxbeta.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.movieboxbeta.R;
import com.example.movieboxbeta.movies.RetrofitClientInstance;
import com.example.movieboxbeta.movies.movie_details.MovieDetails;
import com.example.movieboxbeta.movies.movie_reviews.MovieReviews;
import com.example.movieboxbeta.movies.GetDataService;
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


                            String data = "<hr><h5>Language: </h5>" + movie.getOriginalLanguage();
                            data += "</br>" + "<h5>Adult: </h5>" + (movie.getAdult() ? "Yes" : "No");
                            data += "</br>" + "<h5>Overview: </h5>" + movie.getOverview() + "</br>";
                            data += "<h5>Homepage: </h5><a href=\"" + movie.getHomepage() + "\">"+movie.getHomepage()+"</a></br>";
                            data += "<h5>Duration: </h5>" + movie.getRuntime().toString() + " minutes</br>";
                            data+="<hr>";

                            movieDetails.loadData(data, "text/html", "utf-8");


                        }

                    }

                    @Override
                    public void onFailure(Call<MovieDetails> call, Throwable t) {

                        Log.e("loading movie details", t.toString());

                    }
                });
            }


// TODO: loading reviews
            {

                Call<MovieReviews> call = service.getMovieReviews(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<MovieReviews>() {
                    @Override
                    public void onResponse(Call<MovieReviews> call, Response<MovieReviews> response) {

                        System.out.println(response.body().toString());

                        if (response.isSuccessful()) {

//                            if (response.body().getTotalResults() != 0) {
//                                List<Review> reviews = response.body().getReviews();
//
//                                for (Review review : reviews) {
//                                    TextView author = new TextView(context), content = new TextView(context);
//                                    author.setText(review.getAuthor());
//                                    content.setText(review.getContent());
//                                    view.addView(author);
//                                    view.addView(content);
//
//                                }
//
//                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<MovieReviews> call, Throwable t) {

                    }
                });
            }

            // TODO: loading videos

            {
                Call<Results> call = service.getMovieVideos(movie.getId().toString(), getString(R.string.API_KEY));


                call.enqueue(new Callback<Results>() {
                    @Override
                    public void onResponse(Call<Results> call, Response<Results> response) {

                        System.out.println(response.toString());
                        System.out.println(response.body());

                        if (response.isSuccessful()) {

                            List<Video> videos = response.body().getVideos();

                            String data="";

                            for(Video video:videos)
                            {



                            }

                            movieDetails.loadData(data, "text/html", "utf-8");


                        }

                    }

                    @Override
                    public void onFailure(Call<Results> call, Throwable t) {

                    }
                });
            }


        } else {// TODO error while loading movie details

        }


    }
}
