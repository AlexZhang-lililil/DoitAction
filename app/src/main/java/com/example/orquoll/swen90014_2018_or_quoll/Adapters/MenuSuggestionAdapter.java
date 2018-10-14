package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.ActionActivity;
import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

import java.util.HashSet;
import java.util.Random;


public class MenuSuggestionAdapter extends RecyclerView.Adapter<MenuSuggestionAdapter.LinearViewHolder> {

    private Context mContext;
    private Action[] actions;

    public MenuSuggestionAdapter(Context context ,Action[] actions){
        this.mContext = context;
        this.actions = actions;
    }


    DAOFactory newDAOFactory = new DAOFactory();

    @NonNull
    @Override
    public MenuSuggestionAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_suggestion,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuSuggestionAdapter.LinearViewHolder viewHolder, int i) {

        final String tittle = actions[i].getActionTittle();
        final String des = actions[i].getActionDes();
        final Long id = actions[i].getId();
        viewHolder.action_tittle.setText(tittle);
        viewHolder.action_des.loadData( des,"text/html","UTF-8" );
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass( mContext,ActionActivity.class );
                Bundle actionBundle = new Bundle();
                actionBundle.putLong("Id",id);
                intent.putExtras( actionBundle );
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
        }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private TextView action_tittle;
        private WebView action_des;
        private ImageView action_image;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            action_des = (WebView)itemView.findViewById(R.id.aciton_content);
            action_tittle = (TextView)itemView.findViewById(R.id.aciton_tittle);
            action_image = (ImageView) itemView.findViewById(R.id.action_images);

            action_des.setWebViewClient( new WebViewClient() );
            action_des.getSettings().setJavaScriptEnabled( true );
        }
    }

    public void setActions(Action[] actions){
        this.actions = actions;
    }
}
