package com.varchar.whatwatch;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeider on 14/02/2018.
 */

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageItemHolder> {

    //Modify to use wS
    private List<Integer> images;

    public ImageItemAdapter(List<Integer> images) {
        if (images == null){
            this.images = new ArrayList<>();
        }
        else{
            this.images = images;
        }
    }

    @Override
    public ImageItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image,parent,false);
        ImageItemHolder imageItemHolder = new ImageItemHolder(item);

        return imageItemHolder;
    }

    @Override
    public void onBindViewHolder(ImageItemHolder holder, int position) {
        holder.itemImageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageItemHolder extends RecyclerView.ViewHolder {

        protected ImageView itemImageView;

        public ImageItemHolder(View itemView) {
            super(itemView);
            itemImageView = (ImageView)itemView.findViewById(R.id.itemImageView);
        }
    }
}
