package com.varchar.whatwatch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.varchar.whatwatch.model.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

//import com.varchar.WhatWatch.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView genderRecyclerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SeriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeriesFragment newInstance(String param1, String param2) {
        SeriesFragment fragment = new SeriesFragment();
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
        View fragment = inflater.inflate(R.layout.fragment_series, container, false);
        genderRecyclerView = (RecyclerView)fragment.findViewById(R.id.genderRecyclerView);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
        genderRecyclerView.setHasFixedSize(true);
        genderRecyclerView.setLayoutManager(manager);
        CatalogItemAdapter catalogItemAdapter = new CatalogItemAdapter(getGenders());
        genderRecyclerView.setAdapter(catalogItemAdapter);

        return fragment;
    }

    public List<String> getGenders(){
        List<String> genders = new ArrayList<>();
        genders.add("Acción");
        genders.add("Animación");
        genders.add("Anime");
        genders.add("Ciencia Ficción");
        genders.add("Comedia");
        genders.add("Drama");
        genders.add("Fantasía");

        return genders;
    }

}
