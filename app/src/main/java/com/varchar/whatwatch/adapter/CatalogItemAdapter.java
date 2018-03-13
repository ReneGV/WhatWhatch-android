package com.varchar.whatwatch.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varchar.whatwatch.R;
import com.varchar.whatwatch.model.Genre;
import com.varchar.whatwatch.model.VideoMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets video media grid, separating them by gender
 */
public class CatalogItemAdapter extends RecyclerView.Adapter<CatalogItemAdapter.CatalogItemHolder> {

    // INNER CLASS ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public class CatalogItemHolder extends RecyclerView.ViewHolder {

        protected TextView genderTextView;
        protected RecyclerView imagesRecyclerView;


        public CatalogItemHolder(View itemView) {
            super(itemView);
            genderTextView = (TextView)itemView.findViewById(R.id.genderTextView);
            imagesRecyclerView = (RecyclerView)itemView.findViewById(R.id.imagesRecyclerView);

            //Setting second recycler view (to show images)
            StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
            imagesRecyclerView.setHasFixedSize(true);
            imagesRecyclerView.setLayoutManager(manager);
        }
    }

    // ATTRIBUTES ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private List<Genre> genres;
    int catalog=0;

    public CatalogItemAdapter() {
        this.genres = new ArrayList<>();
    }

    public CatalogItemAdapter(List<Genre> genres, int catalog) {
        if (genres == null){
            this.genres = new ArrayList<>();
        }
        else{
            this.genres = genres;
            this.catalog = catalog;
        }
    }

    @Override
    public CatalogItemAdapter.CatalogItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog,parent,false);
        CatalogItemHolder catalogItemHolder = new CatalogItemHolder(item);

        return catalogItemHolder;
    }

    @Override
    public void onBindViewHolder(CatalogItemAdapter.CatalogItemHolder holder, int position) {
        holder.genderTextView.setText(genres.get(position).getName());
        GenreItemAdapter imageItemAdapter;
        imageItemAdapter = new GenreItemAdapter(genres.get(position).getVideoMediaList() );

        holder.imagesRecyclerView.setAdapter(imageItemAdapter);

    }

    @Override
    public int getItemCount() {
        return genres.size();
    }


}
