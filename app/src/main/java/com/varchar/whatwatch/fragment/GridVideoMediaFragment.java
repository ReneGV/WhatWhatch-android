package com.varchar.whatwatch.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.varchar.whatwatch.adapter.CatalogItemAdapter;
import com.varchar.whatwatch.R;
import com.varchar.whatwatch.model.Genre;
import com.varchar.whatwatch.model.Movie;
import com.varchar.whatwatch.model.Serie;
import com.varchar.whatwatch.model.VideoMedia;
import com.varchar.whatwatch.ws.WebServiceHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GridVideoMediaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridVideoMediaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    final String  CATALOG = "Catalog";

    private RecyclerView genreRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GridVideoMediaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GridVideoMediaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GridVideoMediaFragment newInstance(String param1, String param2) {
        GridVideoMediaFragment fragment = new GridVideoMediaFragment();
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
        View view = inflater.inflate(R.layout.fragment_grid_video_media, container, false);
        genreRecyclerView = (RecyclerView)view.findViewById(R.id.genderRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        genreRecyclerView.setHasFixedSize(true);
        genreRecyclerView.setLayoutManager(manager);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        int catalog = preferences.getInt(CATALOG,0);
        CatalogItemAdapter catalogItemAdapter;
        if (catalog==0) {
            catalogItemAdapter = new CatalogItemAdapter(getCatalog(Movie.class), catalog, getContext());
        }
        else {
            catalogItemAdapter = new CatalogItemAdapter(getCatalog(Serie.class), catalog, getContext());
        }
        genreRecyclerView.setAdapter(catalogItemAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public  <T extends VideoMedia> List<Genre> getCatalog ( Class<T> type){
        final List<Genre> genres = new ArrayList<>();
        //final VideoMedia.VideoType videoType = type.equals(Movie.class) ? VideoMedia.VideoType.MOVIE : VideoMedia.VideoType.SERIE;

        Response.Listener<JSONObject> responseListener= new Response.Listener<JSONObject> () {
            @Override
            public void onResponse(JSONObject response) {
                Iterator<String> keys = response.keys();
                try {
                    while( keys.hasNext() ) {
                        String genreName = keys.next();
                        Genre genre = Genre.fromJSON(genreName, response.getJSONArray(genreName)/*,videoType*/);
                        if(genre.getVideoMediaList().size()>0){
                            genres.add(genre);
                        }
                    }
                } catch (JSONException exception) {
                    exception.printStackTrace();
                }
                for(Genre g : genres){
                    Log.d("[JSON-Mapping Genres]", g.toString());
                }
                genreRecyclerView.getAdapter().notifyDataSetChanged();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };

        if (type.equals(Movie.class)){
            WebServiceHandler.requestMovies( responseListener, errorListener );
        } else if(type.equals(Serie.class)){
            WebServiceHandler.requestSeries( responseListener, errorListener );
        }

        return genres;

    }
}
