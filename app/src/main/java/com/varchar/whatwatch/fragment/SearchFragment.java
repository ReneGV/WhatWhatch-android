package com.varchar.whatwatch.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.varchar.whatwatch.R;
import com.varchar.whatwatch.adapter.VideoMediaListAdapter;
import com.varchar.whatwatch.model.Genre;
import com.varchar.whatwatch.model.Movie;
import com.varchar.whatwatch.model.Serie;
import com.varchar.whatwatch.model.VideoMedia;
import com.varchar.whatwatch.sqlite.DataBase.WhatWatchDB;
import com.varchar.whatwatch.ws.WebServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by reneg on 18/03/2018.
 */

public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private VideoMediaListAdapter videoMediaListAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView listVideoMediaRecyclerView;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListVideoMediaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_list_video_media, container, false);
        listVideoMediaRecyclerView = (RecyclerView)view.findViewById(R.id.list_video_media_recyclerView);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listVideoMediaRecyclerView.setHasFixedSize(true);
        listVideoMediaRecyclerView.setLayoutManager(manager);
        //FIXME
        videoMediaListAdapter = new VideoMediaListAdapter(new ArrayList<VideoMedia>());
        listVideoMediaRecyclerView.setAdapter(videoMediaListAdapter);
        SearchView searchView = (SearchView) getActivity().findViewById(R.id.action_search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchVideoMedia(newText);
                return false;
            }
        });
        return view;
    }


    private List<VideoMedia> searchVideoMedia(String queryString){
        final List<VideoMedia> searchResults = new ArrayList<>();

        Response.Listener<JSONArray> responseListener= new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int index = 0; index < response.length(); index++){
                        JSONObject videoResponse = response.getJSONObject(index);
                        VideoMedia videoMedia = new VideoMedia();
                        videoMedia.setId(videoResponse.getInt("id"));
                        videoMedia.setName(videoResponse.getString("name"));
                        videoMedia.setGender(new Genre(videoResponse.getString("genre")));
                        videoMedia.setImageUrl(videoResponse.getString("posterImage"));
                        videoMedia.setReleaseDate(videoResponse.getString("productionDate"));
                        videoMedia.setType(videoResponse.getString("type"));
                        searchResults.add(videoMedia);
                    }
                } catch (JSONException exception) {
                    exception.printStackTrace();
                }
                for(VideoMedia v : searchResults){
                    Log.d("[JSON-Search]", v.toString());
                }
                videoMediaListAdapter.setVideoMediaItems(searchResults);
                videoMediaListAdapter.notifyDataSetChanged();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        };

        WebServiceHandler.searchVideoMedia(queryString, responseListener, errorListener);
        return searchResults;
    }

}
