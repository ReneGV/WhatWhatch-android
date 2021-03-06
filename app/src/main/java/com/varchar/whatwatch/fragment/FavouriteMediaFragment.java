package com.varchar.whatwatch.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.varchar.whatwatch.R;
import com.varchar.whatwatch.adapter.FavouriteAdapter;
import com.varchar.whatwatch.adapter.SearchListAdapter;
import com.varchar.whatwatch.sqlite.DataBase.WhatWatchDB;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavouriteMediaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteMediaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView listVideoMediaRecyclerView;

    public FavouriteMediaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteMediaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteMediaFragment newInstance(String param1, String param2) {
        FavouriteMediaFragment fragment = new FavouriteMediaFragment();
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
        FavouriteAdapter favouriteAdapter = new FavouriteAdapter(WhatWatchDB.getAllFavourites());
        listVideoMediaRecyclerView.setAdapter(favouriteAdapter);
        return view;
    }

}
