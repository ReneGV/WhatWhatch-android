package com.varchar.whatwatch;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varchar.whatwatch.model.Gender;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeider on 14/02/2018.
 */

public class CatalogItemAdapter extends RecyclerView.Adapter<CatalogItemAdapter.CatalogItemHolder> {

    private List<String> Gender;

    public CatalogItemAdapter(List<String> genders) {
        if (genders == null){
            this.Gender = new ArrayList<>();
        }
        else{
            this.Gender = genders;
        }
    }
//    private RecyclerView imagesRecyclerView;

    @Override
    public CatalogItemAdapter.CatalogItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog,parent,false);
        CatalogItemHolder catalogItemHolder = new CatalogItemHolder(item);

        return catalogItemHolder;
    }

    @Override
    public void onBindViewHolder(CatalogItemAdapter.CatalogItemHolder holder, int position) {
        holder.genderTextView.setText(Gender.get(position));
        ImageItemAdapter imageItemAdapter = new ImageItemAdapter(getImages(Gender.get(position)));
        holder.imagesRecyclerView.setAdapter(imageItemAdapter);

    }

    @Override
    public int getItemCount() {
        return Gender.size();
    }

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


    public List<Integer> getImages(String gender){
        List<Integer> images = new ArrayList<>();
        //images.add(R.drawable)
        switch (gender){
            case "Acción":
                images.add(R.drawable.sarrow);
                images.add(R.drawable.sdaredevil);
                images.add(R.drawable.sspartacus);
                break;
            case "Animación":
                images.add(R.drawable.sclonewars);
                images.add(R.drawable.sdragones);
                break;
            case "Anime":
                images.add(R.drawable.sfma);
                images.add(R.drawable.syugioh);
                images.add(R.drawable.ssao);
                break;
            case "Ciencia Ficción":
                images.add(R.drawable.sblackmirror);
                images.add(R.drawable.sdoctorwho);
                images.add(R.drawable.ssense8);
                break;
            case "Comedia":
                images.add(R.drawable.shimym);
                images.add(R.drawable.sfullerhouse);
                images.add(R.drawable.sfriends);
                break;
            case "Drama":
                images.add(R.drawable.sclubcuervos);
                images.add(R.drawable.selchapo);
                images.add(R.drawable.srevenge);
                break;
            case "Fantasía":
                images.add(R.drawable.sfate);
                images.add(R.drawable.sonceuponatime);
                images.add(R.drawable.ssupergirl);
                break;
            default:
                images.add(R.drawable.sarrow);
                images.add(R.drawable.sdaredevil);
                images.add(R.drawable.sspartacus);
                break;
        }
        return images;

    }
}
