package com.example.movieboxbeta.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.movieboxbeta.R;
import com.example.movieboxbeta.db.MovieDB;
import com.example.movieboxbeta.movies.GetDataService;
import com.example.movieboxbeta.movies.MovieAdapter;
import com.example.movieboxbeta.movies.RetrofitClientInstance;
import com.example.movieboxbeta.movies.movies_list.Movie;
import com.example.movieboxbeta.movies.movies_list.Results;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.movieboxbeta.fragment.DataDisplayFragment.Categories.FAVORITE;
import static com.example.movieboxbeta.fragment.DataDisplayFragment.Categories.LATEST;
import static com.example.movieboxbeta.fragment.DataDisplayFragment.Categories.NOW_PLAYING;
import static com.example.movieboxbeta.fragment.DataDisplayFragment.Categories.POPULAR;
import static com.example.movieboxbeta.fragment.DataDisplayFragment.Categories.TOP_RATED;
import static com.example.movieboxbeta.fragment.DataDisplayFragment.Categories.UPCOMING;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataDisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DataDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataDisplayFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "category";

    private int category;
    private MovieAdapter movieAdapter;
    private OnFragmentInteractionListener mListener;
    private MovieDB movieDB;
    private GridView gridView;

    public DataDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataDisplayFragment.
     */
    public static DataDisplayFragment newInstance(String param1, String param2) {
        DataDisplayFragment fragment = new DataDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        if (category == FAVORITE) {

            View view = getView();

            if (movieDB == null)
                movieDB = MovieDB.getInstance(this.getContext());

            movieDB = MovieDB.getInstance(this.getContext());


            new AsyncTask<Void, Void, List<Movie>>() {


                @Override
                protected List<Movie> doInBackground(Void... voids) {
                    return movieDB.getMovieDBDao().getAll();
                }

                @Override
                protected void onPostExecute(List<Movie> movies) {
                    loadData(movies);
                    movieDB.cleanUp();
                }
            }.execute();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_display, container, false);


        gridView = view.findViewById(R.id.dataGrid);
        movieAdapter = new MovieAdapter(view.getContext());
        gridView.setAdapter(movieAdapter);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance(getString(R.string.baseURL)).create(GetDataService.class);
        Call<Results> call = null;

        switch (category) {
            case POPULAR:
                call = service.getPopular(getString(R.string.API_KEY));
                break;
            case NOW_PLAYING:

                call = service.getNowPlaying(getString(R.string.API_KEY));
                break;

            case TOP_RATED:
                call = service.getTopRated(getString(R.string.API_KEY));
                break;
            case UPCOMING:
                call = service.getUpComing(getString(R.string.API_KEY));
                break;
            case LATEST:

//                Call<Object> tCall = service.getLatest(getString(R.string.API_KEY));
//
//                tCall.enqueue(new Callback<Object>() {
//                    @Override
//                    public void onResponse(Call<Object> call, Response<Object> response) {
//
//
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<Object> call, Throwable t) {
//
//                    }
//                });

                break;


        }

        if (category != FAVORITE && category != LATEST)
            call.enqueue(new Callback<Results>() {
                @Override
                public void onResponse(Call<Results> call, Response<Results> response) {

                    System.out.println(response.body());


                    if (response.isSuccessful()) {

                        final List<Movie> results = response.body().getResults();

                        loadData(results);

                    }
                }

                @Override
                public void onFailure(Call<Results> call, Throwable t) {

                    Log.e("DataDisplayFragment", t.toString());

                }
            });
        else if (category == FAVORITE) {
            //refresh the fav


            movieDB = MovieDB.getInstance(this.getContext());


            new AsyncTask<Void, Void, List<Movie>>() {


                @Override
                protected List<Movie> doInBackground(Void... voids) {
                    return movieDB.getMovieDBDao().getAll();
                }

                @Override
                protected void onPostExecute(List<Movie> movies) {
                    loadData(movies);
                    movieDB.cleanUp();
                }
            }.execute();


        } else {

        }


        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface Categories {

        int LATEST = 0;
        int NOW_PLAYING = 1;
        int POPULAR = 2;
        int TOP_RATED = 3;
        int UPCOMING = 4;
        int FAVORITE = 5;

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }


    public void loadData(final List<Movie> results) {
        new AsyncTask<List<Movie>, Void, List<Bitmap>>() {


            private ProgressDialog dialog;

            @Override
            protected void onPreExecute() {


                dialog = ProgressDialog.show(getContext(), "Loading", "Wait while loading...");
            }

            @Override
            protected List<Bitmap> doInBackground(List<Movie>... lists) {

                List<Bitmap> posters = new ArrayList<>();

                try {
                    for (int i = 0; i < lists[0].size(); i++) {
                        InputStream inputStream = null;

                        lists[0].get(i).setPosterURL(getContext().getString(R.string.imageURL) + lists[0].get(i).getPosterPath());

                        inputStream = new URL(lists[0].get(i).getPosterURL()).openStream();

                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        posters.add(bitmap);

                    }
                } catch (IOException e) {
                    Log.e("loading popular", e.toString());

                }

                return posters;
            }

            @Override
            protected void onPostExecute(List<Bitmap> bitmaps) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                    dialog.cancel();
                }

                movieAdapter.setMovies(results, bitmaps);
                movieAdapter.notifyDataSetChanged();

            }
        }.execute(results);

    }
}
