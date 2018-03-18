package com.varchar.whatwatch.fragment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.varchar.whatwatch.adapter.GenreItemAdapter;
import com.varchar.whatwatch.R;
import com.varchar.whatwatch.model.VideoMedia;
import com.varchar.whatwatch.sqlite.DataBase.WhatWatchDB;


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
        TextView textView = (TextView) view.findViewById(R.id.dv_vm_title);
        textView.setText( videoMedia.getName() );
        // Set the image
        // TODO: fetch imagee from web
        ImageView imageView = (ImageView) view.findViewById(R.id.dv_app_bar_image);
        imageView.setImageResource(videoMedia.getImageId());

        Toast.makeText(getActivity().getBaseContext(),videoMedia.getType().toString(), Toast.LENGTH_LONG).show();
        //TODO edit favourite button dispay logic
        final FloatingActionButton buttonFavourites = (FloatingActionButton) view.findViewById(R.id.dv_add_favourite);
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

        return view;
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
