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
            catalogItemAdapter = new CatalogItemAdapter(getGendersMovies(), catalog, getContext());
        }
        else {
            catalogItemAdapter = new CatalogItemAdapter(getGenders(), catalog, getContext());
        }
        genreRecyclerView.setAdapter(catalogItemAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public List<Genre> getGenders(){
        final List<Genre> genres = new ArrayList<>();
        WebServiceHandler.requestSeries(
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                        Iterator<String> keys = response.keys();
                        try {
                            while( keys.hasNext() ) {
                                String genreName = keys.next();
                                Genre genre = Genre.fromJSON(genreName, response.getJSONArray(genreName));
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
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
//        catalogItemAdapter.notifyDataSetChanged();
        return genres;

        /*
        List<Genre> genres = new ArrayList<>();
        Genre accion = new Genre("Accion");
        accion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sarrow, "Arrow" ));
        accion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sdaredevil , "DareDevil"));
        accion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sspartacus , "Spartacus"));
        genres.add( accion );
        Genre animacion = new Genre("Animacion");
        animacion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sclonewars , "Clone Wars"));
        animacion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sdragones, "Dragones"));
        genres.add( animacion );
        Genre anime = new Genre("Anime");
        anime.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sfma , "FMA"));
        anime.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.syugioh , "Yu-gi-oh"));
        anime.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.ssao , "SAO"));
        genres.add( anime );
        Genre sciFi = new Genre("Ciencia Ficción");
        sciFi.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sblackmirror , "Black mirror"));
        sciFi.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sdoctorwho , "Dr. Who"));
        sciFi.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.ssense8 , "Sense 8"));
        genres.add( sciFi );
        Genre comedia = new Genre("Comedia");
        comedia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.shimym , "Himym"));
        comedia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sfullerhouse , "Fuller House"));
        comedia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sfriends , "Friends"));
        genres.add( comedia );
        Genre drama = new Genre("Drama");
        drama.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sclubcuervos , "Club de los cuervos"));
        drama.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.selchapo , "El chapo"));
        drama.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.srevenge , "Revenge"));
        genres.add( drama );
        Genre fantasia = new Genre("Accion");
        fantasia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sonceuponatime, "Once upon a time"));
        fantasia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.ssupergirl, "Super Girl"));
        fantasia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.sfate, "Fate"));
        genres.add( fantasia );
        return genres;
        */
    }

    public List<Genre> getGendersMovies(){
        final List<Genre> genres = new ArrayList<>();
        WebServiceHandler.requestMovies(
                new Response.Listener<JSONObject> () {
                    @Override
                    public void onResponse(JSONObject response) {
                            Iterator<String> keys = response.keys();
                            try {
                                while( keys.hasNext() ) {
                                    String genreName = keys.next();
                                    Genre genre = Genre.fromJSON(genreName, response.getJSONArray(genreName));
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
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
//        catalogItemAdapter.notifyDataSetChanged();
        return genres;

        /*
        List<Genre> genres = new ArrayList<>();
        // COMEDIA
        Genre comedia = new Genre("Comedia");
        comedia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.comoninos , "Como niños"));
        comedia.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.scottpilgrim, "Scott"));
        genres.add( comedia );
        // ACCION
        Genre accion = new Genre("Accion");
        accion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.transformers, "Transformers"));
        accion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.olimpo , "Olimpo"));
        genres.add( accion );
        Genre sciFi = new Genre("Cienci Ficción");
        sciFi.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.meninblack, "Men in back"));
        genres.add( sciFi );
        Genre terror = new Genre("Terror");
        terror.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.avengers, "Avengers"));
        terror.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.elmstreet , "Elm street"));
        terror.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.nochedemonio, "Noche del demonio"));
        genres.add( terror );
        Genre animacion = new Genre("Animacion");
        animacion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.ratatouille, "Ratatouille"));
        animacion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.hoteltransylvania , "Hotel transilvania"));
        animacion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.toystory, "Toy story"));
        animacion.getVideoMediaList().add(VideoMedia.fromLocalResources(R.drawable.jack, "Jack"));
        genres.add( animacion );
        return genres;
        */
    }


}
