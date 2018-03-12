package com.varchar.whatwatch.adapter;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.varchar.whatwatch.R;
import com.varchar.whatwatch.fragment.DetailFragment;
import com.varchar.whatwatch.model.VideoMedia;

import java.util.ArrayList;
import java.util.List;

public class VideoMediaListAdapter extends RecyclerView.Adapter<VideoMediaListAdapter.VideoMediaItemHolder>{

    // INNER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    static class VideoMediaItemHolder extends RecyclerView.ViewHolder {


        public ImageView imageView;
        public TextView textViewName;
        public VideoMedia videoMedia;
        public LinearLayout linearLayout;

        public VideoMediaItemHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.fv_poster);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.fv_linear_layout);
            textViewName = (TextView) itemView.findViewById(R.id.fv_name);
        }

    }

    // ATTRIBUTES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private List<VideoMedia> videoMediaItems;

    // CONSTRUCTOR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


    public VideoMediaListAdapter(List<VideoMedia> videoMediaItems) {
        if (videoMediaItems == null) {this.videoMediaItems = new ArrayList<>();}
        else {this.videoMediaItems = videoMediaItems;}
    }

    @Override
    public VideoMediaListAdapter.VideoMediaItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite,parent,false);
        final VideoMediaItemHolder videoMediaItemHolder = new VideoMediaItemHolder(item);
        videoMediaItemHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
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
    public void onBindViewHolder(VideoMediaListAdapter.VideoMediaItemHolder holder, int position) {
        holder.videoMedia = videoMediaItems.get(position);
        holder.imageView.setImageResource(holder.videoMedia.getImageId());
        holder.textViewName.setText(holder.videoMedia.getName());
    }

    @Override
    public int getItemCount() {
        return videoMediaItems.size();
    }

}
