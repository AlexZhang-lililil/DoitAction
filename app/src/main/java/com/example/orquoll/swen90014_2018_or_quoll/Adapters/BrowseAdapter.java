package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;

import java.util.List;

public class BrowseAdapter extends RecyclerView.Adapter<BrowseAdapter.LinearViewHolder> {

    private Context mContext;
    private DAOFactory newDAOFactory;
    private Strength[] allStrength;

    public  BrowseAdapter (Context mContext){
        this.mContext = mContext;
        this.newDAOFactory = new DAOFactory();
        this.allStrength = newDAOFactory.getStrengthDAOImp().display();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private TextView tittle,content;
        private ImageView strengthImage;

        public LinearViewHolder(View itemView){
            super(itemView);
            tittle = (TextView)itemView.findViewById(R.id.txt_strength_title);
            content = (TextView)itemView.findViewById(R.id.txt_strength_description);
            strengthImage = (ImageView)itemView.findViewById(R.id.img_strength);
        }
    }
    @NonNull
    @Override
    public BrowseAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_strength,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BrowseAdapter.LinearViewHolder viewHolder, int i) {
        String tittle = allStrength[i].getStrength_Tittle();
        String content = allStrength[i].getStrength_Content();
        int drawableId = allStrength[i].getDrawableId();
        long id = allStrength[i].getId();
        viewHolder.tittle.setText(tittle);
        viewHolder.content.setText(content);
        viewHolder.strengthImage.setImageDrawable(newDrawable(drawableId));
    }

    @Override
    public int getItemCount() {
        return allStrength.length;
    }

    public Drawable newDrawable(int resid){
        return mContext.getDrawable(resid);
    }
}
