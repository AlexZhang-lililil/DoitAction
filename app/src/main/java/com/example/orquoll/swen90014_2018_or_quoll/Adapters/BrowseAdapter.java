package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.StrengthActivity;
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

        private TextView tittle,description;
        private ImageView strengthImage;

        public LinearViewHolder(View itemView){
            super(itemView);
            tittle = (TextView)itemView.findViewById(R.id.txt_strength_title);
            description = (TextView)itemView.findViewById(R.id.txt_strength_description);
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
        String description = allStrength[i].getStrength_description();
        int drawableId = allStrength[i].getDrawableId();
        final long id = allStrength[i].getId();
        viewHolder.tittle.setText(tittle);
        viewHolder.description.setText(description);
        viewHolder.strengthImage.setImageDrawable(newDrawable(drawableId));
        viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle(  );
                bundle.putLong( "strength_Id",id );
                intent.putExtras( bundle );
                intent.setClass( mContext, StrengthActivity.class );
                mContext.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return allStrength.length;
    }

    public Drawable newDrawable(int resid){
        return mContext.getDrawable(resid);
    }
}
