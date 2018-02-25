package com.varchar.whatwatch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varchar.whatwatch.model.VideoMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets video media grid, separating them by gender
 */
public class CatalogItemAdapter extends RecyclerView.Adapter<CatalogItemAdapter.CatalogItemHolder> {

    private List<String> Gender;
    private Context context;
    final String CATALOG = "Catalog";
    int catalog=0;



    public CatalogItemAdapter(List<String> genders, int catalog) {
        if (genders == null){
            this.Gender = new ArrayList<>();
            this.catalog = 0;

        }
        else{
            this.Gender = genders;
            this.catalog = catalog;

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
        HorizontalItemAdapter imageItemAdapter;

        if (this.catalog == 0){
            imageItemAdapter = new HorizontalItemAdapter(getMovies(Gender.get(position)));
        }
        else {
            imageItemAdapter = new HorizontalItemAdapter(getSeries(Gender.get(position)));
        }

        //imageItemAdapter = new HorizontalItemAdapter(getSeries(Gender.get(position)));
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

    // FIXME: FETCH VideoMedia items from web service
    // TODO : iterate throug series list
    public List<VideoMedia> getSeries(String gender){
        List<VideoMedia> images = new ArrayList<>();
        switch (gender){
            case "Acción":
                images.add( VideoMedia.fromLocalResources(R.drawable.sarrow, "Arrow" ) );
                images.add( VideoMedia.fromLocalResources(R.drawable.sdaredevil , "DareDevil"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sspartacus , "Spartacus"));
                break;
            case "Animación":
                images.add( VideoMedia.fromLocalResources(R.drawable.sclonewars , "Clone Wars"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sdragones, "Dragones"));
                break;
            case "Anime":
                images.add( VideoMedia.fromLocalResources(R.drawable.sfma , "FMA"));
                images.add( VideoMedia.fromLocalResources(R.drawable.syugioh , "Yu-gi-oh"));
                images.add( VideoMedia.fromLocalResources(R.drawable.ssao , "SAO"));
                break;
            case "Ciencia Ficción":
                images.add( VideoMedia.fromLocalResources(R.drawable.sblackmirror , "Black mirror"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sdoctorwho , "Dr. Who"));
                images.add( VideoMedia.fromLocalResources(R.drawable.ssense8 , "Sense 8"));
                break;
            case "Comedia":
                images.add( VideoMedia.fromLocalResources(R.drawable.shimym , "Himym"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sfullerhouse , "Fuller House"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sfriends , "Friends"));
                break;
            case "Drama":
                images.add( VideoMedia.fromLocalResources(R.drawable.sclubcuervos , "Club de los cuervos"));
                images.add( VideoMedia.fromLocalResources(R.drawable.selchapo , "El chapo"));
                images.add( VideoMedia.fromLocalResources(R.drawable.srevenge , "Reveng"));
                break;
            case "Fantasía":
                images.add( VideoMedia.fromLocalResources(R.drawable.sfate , "Fate"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sonceuponatime, "Once upon a time"));
                images.add( VideoMedia.fromLocalResources(R.drawable.ssupergirl, "Super Girl"));
                break;
            default:
                images.add( VideoMedia.fromLocalResources(R.drawable.sarrow, "Arrow"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sdaredevil, "Dare Devil"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sspartacus, "Spartacus"));
                break;
        }
        return images;

    }

    //FIXME: FETCH VideoMedia items from web service
    public List<VideoMedia> getMovies(String gender){
        List<VideoMedia> images = new ArrayList<>();
        switch (gender){
            case "Comedia":
                images.add( VideoMedia.fromLocalResources(R.drawable.comoninos , "Como niños"));
                images.add( VideoMedia.fromLocalResources(R.drawable.scottpilgrim, "Scott"));
                break;
            case "Acción":
                images.add( VideoMedia.fromLocalResources(R.drawable.transformers, "Transformers"));
                images.add( VideoMedia.fromLocalResources(R.drawable.olimpo , "Olimpo"));
                break;
            case "Ciencia Ficción":
                images.add( VideoMedia.fromLocalResources(R.drawable.meninblack, "Men in back"));
                break;
            case "Terror":
                images.add( VideoMedia.fromLocalResources(R.drawable.avengers, "Avengers"));
                images.add( VideoMedia.fromLocalResources(R.drawable.elmstreet , "Elm street"));
                images.add( VideoMedia.fromLocalResources(R.drawable.nochedemonio, "Noche del demonio"));
                break;
            case "Animación":
                images.add( VideoMedia.fromLocalResources(R.drawable.ratatouille, "Ratatouille"));
                images.add( VideoMedia.fromLocalResources(R.drawable.hoteltransylvania , "Hotel transilvania"));
                images.add( VideoMedia.fromLocalResources(R.drawable.toystory, "Toy story"));
                images.add( VideoMedia.fromLocalResources(R.drawable.jack, "Jack"));
                break;
            default:
                images.add( VideoMedia.fromLocalResources(R.drawable.sarrow, "Arrow"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sdaredevil , "Dare Devil"));
                images.add( VideoMedia.fromLocalResources(R.drawable.sspartacus, "Spartacus"));
                break;
        }
        return images;

    }
}
