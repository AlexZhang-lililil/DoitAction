package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.ActionActivity;
import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;


public class MenuSuggestionAdapter extends RecyclerView.Adapter<MenuSuggestionAdapter.LinearViewHolder> {

    private Context mContext;

    public MenuSuggestionAdapter(Context context){
        this.mContext = context;
    }


    DAOFactory newDAOFactory = new DAOFactory();

    @NonNull
    @Override
    public MenuSuggestionAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_suggestion,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuSuggestionAdapter.LinearViewHolder viewHolder, int i) {

        Action[] actions = new Action[newDAOFactory.getActionDAOImpInstance().display().length];
        actions = newDAOFactory.getActionDAOImpInstance().display();
        final String tittle = actions[i].getActionTittle();
        final String content = actions[i].getActionContent();
        final String id = actions[i].getId();
        viewHolder.action_tittle.setText(tittle);
        viewHolder.action_content.setText(content);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass( mContext,ActionActivity.class );
                Bundle actionBundle = new Bundle();
                actionBundle.putString("Tittle",tittle);
                actionBundle.putString("Content",content);
                actionBundle.putString("Id",id);
                intent.putExtras( actionBundle );
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {

        return newDAOFactory.getActionDAOImpInstance().display().length;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private TextView action_tittle,action_content;
        private ImageView action_image;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            action_content = (TextView)itemView.findViewById(R.id.aciton_content);
            action_tittle = (TextView)itemView.findViewById(R.id.aciton_tittle);
            action_image = (ImageView) itemView.findViewById(R.id.action_images);
        }
    }
}
