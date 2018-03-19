package com.varchar.whatwatch.fragment;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.varchar.whatwatch.adapter.GenreItemAdapter;
import com.varchar.whatwatch.R;
import com.varchar.whatwatch.model.Genre;
import com.varchar.whatwatch.model.VideoMedia;
import com.varchar.whatwatch.sqlite.DataBase.WhatWatchDB;
import com.varchar.whatwatch.ws.WebServiceHandler;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private VideoMedia videoMedia;

    private TextView descriptionTextView;
    private ImageView detailImageView;
    private TextView releaseDateTextView;
    private TextView genreTextView;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
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
        videoMedia = (VideoMedia) getActivity().getIntent().getSerializableExtra(GenreItemAdapter.VIDEO_MEDIA_KEY);
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // Set detail view text
        TextView titleTextView = (TextView) view.findViewById(R.id.dv_vm_title);
        titleTextView.setText( videoMedia.getName() );

        descriptionTextView = (TextView) view.findViewById(R.id.dv_vm_description);
        // Set the image
        // TODO: fetch imagee from web
        detailImageView = (ImageView) view.findViewById(R.id.dv_app_bar_image);

        releaseDateTextView = (TextView) view.findViewById(R.id.dv_vm_releaseDate);
        genreTextView = (TextView) view.findViewById(R.id.dv_vm_genre);

        SearchView searchView = (SearchView) getActivity().findViewById(R.id.action_search);
        searchView.onActionViewCollapsed();
        // Fetch data from web service
        getSelectedVideoMedia();

        return view;
    }


    private void getSelectedVideoMedia(){
        Response.Listener<JSONObject> responseListener= new Response.Listener<JSONObject> () {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    videoMedia.setDescription(response.getString("description"));
                    videoMedia.setDetailImageUrl(response.getString("descriptionImage"));
                    videoMedia.setGender(new Genre(response.getString("genderName")));
                    videoMedia.setReleaseDate(response.getString("productionDate"));

                    render();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };

        if (videoMedia.getType() == VideoMedia.VideoType.MOVIE){
            WebServiceHandler.requestMovie(videoMedia.getId(),responseListener,errorListener);
        }else{
            WebServiceHandler.requestSerie(videoMedia.getId(),responseListener,errorListener);
        }
    }

    private void render(){
        descriptionTextView.setText(videoMedia.getDescription());
        Glide.with(getActivity().getBaseContext())
                .load(videoMedia.getDetailImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.video_default))
                .into(detailImageView);
        releaseDateTextView.setText("Fecha de estreno: " + videoMedia.getReleaseDate().substring(0,10));
        genreTextView.setText("Género: " + videoMedia.getGender().getName());

        setFavouriteIcon();
    }

    // FAVOURITES LOGIC

    private void setFavouriteIcon(){
        final FloatingActionButton buttonFavourites = (FloatingActionButton) getActivity().findViewById(R.id.dv_add_favourite);
        buttonFavourites.setVisibility(View.VISIBLE);
        if (WhatWatchDB.isFavoutie(videoMedia)){
            //buttonFavourites.setVisibility(View.GONE);
            buttonFavourites.setImageResource(R.drawable.ic_dislike);
            buttonFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteFromFavourites(DetailFragment.this.videoMedia);
                    buttonFavourites.setVisibility(View.GONE);
                }
            });

        }else{
            buttonFavourites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addToFavourites(DetailFragment.this.videoMedia);
                    buttonFavourites.setVisibility(View.GONE);
                }
            });
        }
    }

    private void addToFavourites(VideoMedia videoMedia){
        WhatWatchDB.saveFavourite(videoMedia);
        Snackbar.make(getView(), videoMedia.getName() + " fue añadido a favoritos", Snackbar.LENGTH_LONG).show();
    }

    private void deleteFromFavourites(VideoMedia videoMedia){
        WhatWatchDB.deleteFavourite(videoMedia);
        Snackbar.make(getView(), videoMedia.getName() + " fue removido de favoritos", Snackbar.LENGTH_LONG).show();
    }


}
