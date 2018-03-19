package com.varchar.whatwatch.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.varchar.whatwatch.R;
import com.varchar.whatwatch.fragment.DetailFragment;
import com.varchar.whatwatch.model.VideoMedia;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by reneg on 18/03/2018.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteItemHolder> {


    // INNER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    static class FavouriteItemHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        ConstraintLayout constraintLayout;

        VideoMedia videoMedia;

        public FavouriteItemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.fv_image);
            textViewName = (TextView) itemView.findViewById(R.id.fv_name);
            constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.fv_layout);
        }
    }

    // ATTRIBUTES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private List<VideoMedia> videoMediaItems;

    public FavouriteAdapter(List<VideoMedia> videoMediaItems) {
        if (videoMediaItems == null) {this.videoMediaItems = new ArrayList<>();}
        else {this.videoMediaItems = videoMediaItems;}
    }


    @NonNull
    @Override
    public FavouriteAdapter.FavouriteItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite,parent,false);
        final FavouriteItemHolder videoMediaItemHolder = new FavouriteItemHolder(item);
        videoMediaItemHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                DetailFragment detailFragment = new DetailFragment();
                activity.getIntent().putExtra(GenreItemAdapter.VIDEO_MEDIA_KEY, videoMediaItemHolder.videoMedia);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.containerMain,detailFragment)
                        .addToBackStack("BACK_TAG").commit();

            }
        });
        return videoMediaItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteAdapter.FavouriteItemHolder holder, int position) {
        holder.videoMedia = videoMediaItems.get(position);
        Glide.with(holder.imageView.getContext())
                .load(holder.videoMedia.getDetailImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.poster_default))
                .into(holder.imageView);
        holder.textViewName.setText(holder.videoMedia.getName());
    }

    @Override
    public int getItemCount() {
        return videoMediaItems.size();
    }

}
