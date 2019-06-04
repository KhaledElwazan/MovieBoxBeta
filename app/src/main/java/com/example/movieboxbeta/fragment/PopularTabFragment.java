package com.example.movieboxbeta.fragment;

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
import com.example.movieboxbeta.movies.MovieAdapter;
import com.example.movieboxbeta.movies.RetrofitClientInstance;
import com.example.movieboxbeta.movies.movies_list.GetDataService;
import com.example.movieboxbeta.movies.movies_list.Movie;
import com.example.movieboxbeta.movies.movies_list.Results;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PopularTabFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PopularTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularTabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MovieAdapter movieAdapter;

    private OnFragmentInteractionListener mListener;

    public PopularTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopularTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularTabFragment newInstance(String param1, String param2) {
        PopularTabFragment fragment = new PopularTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_popular_tap, container, false);


        GridView gridView = view.findViewById(R.id.dataGrid);
        movieAdapter = new MovieAdapter(view.getContext());
        gridView.setAdapter(movieAdapter);


        GetDataService service = RetrofitClientInstance.getRetrofitInstance(getString(R.string.baseURL)).create(GetDataService.class);
        Call<Results> call = service.getPopular(getString(R.string.API_KEY));

        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (response.isSuccessful()) {

                    final List<Movie> results = response.body().getResults();


                    new AsyncTask<List<Movie>, Void, List<Bitmap>>() {


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


                            } catch (Exception e) {
                                Log.e("loading popular", e.toString());
                            }


                            return posters;
                        }

                        @Override
                        protected void onPostExecute(List<Bitmap> bitmaps) {
                            movieAdapter.setMovies(results, bitmaps);
                            movieAdapter.notifyDataSetChanged();
                        }
                    }.execute(results);
                }
            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {

                Log.e("loading popular", t.toString());

            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
