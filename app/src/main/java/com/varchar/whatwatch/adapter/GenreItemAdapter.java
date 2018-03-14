package com.varchar.whatwatch.adapter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.varchar.whatwatch.R;
import com.varchar.whatwatch.fragment.DetailFragment;
import com.varchar.whatwatch.model.VideoMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Displays Video Media items horizontally 
 */
//FIXME: Fetch images from web
public class GenreItemAdapter extends RecyclerView.Adapter<GenreItemAdapter.ImageItemHolder> {

    public static final String VIDEO_MEDIA_KEY = "VIDEO_MEDIA";

    // INNER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public class ImageItemHolder extends RecyclerView.ViewHolder {

        public ImageView itemImageView;
        public VideoMedia videoMedia;

        public ImageItemHolder(View itemView) {
            super(itemView);
            itemImageView = (ImageView)itemView.findViewById(R.id.itemImageView);
        }
    }

    // ATTRIBUTES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //Modify to use wS
    private List<VideoMedia> videoMediaItems;
    private Context context;

    // CONSTRUCTOR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public GenreItemAdapter(List<VideoMedia> videoMediaItems, Context context) {
        if (videoMediaItems == null){
            this.videoMediaItems = new ArrayList<>();
        }
        else{
            this.videoMediaItems = videoMediaItems;

        }
        this.context = context;
    }

    @Override
    public ImageItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        final ImageItemHolder imageItemHolder = new ImageItemHolder(item);
        imageItemHolder.itemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                DetailFragment detailFragment = new DetailFragment();
                activity.getIntent().putExtra(VIDEO_MEDIA_KEY, imageItemHolder.videoMedia);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMain,detailFragment)
                        .addToBackStack("BACK_TAG").commit();

            }
        });
        return imageItemHolder;
    }

    @Override
    public void onBindViewHolder(ImageItemHolder holder, int position) {
        Log.d("[Movie]", videoMediaItems.get(position).getName());
        //holder.itemImageView.setImageResource(videoMediaItems.get(position).getImageId());
        // Bind each holder with the corresponding video media
        //holder.videoMedia = videoMediaItems.get(position);
        Glide.with(context).load(videoMediaItems.get(position).getImageUrl()).into(holder.itemImageView);

    }

    @Override
    public int getItemCount() {
        return videoMediaItems.size();
    }

}
