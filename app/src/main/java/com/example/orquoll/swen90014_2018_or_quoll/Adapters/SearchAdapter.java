package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.LinearViewHolder> {

    private Context mContext;
    private DAOFactory newDAOFactory;
    private Action[] searchActions;

    public SearchAdapter (Context context,Action[] searchActions){
        this.mContext = context;
        this.searchActions = searchActions;

    }

    public void setSearchActions(Action[] searchActions) {
        this.searchActions = searchActions;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView action_tittle;
        private WebView action_des;
        private ImageView action_image;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            action_des = (WebView)itemView.findViewById(R.id.aciton_content);
            action_tittle = (TextView)itemView.findViewById(R.id.aciton_tittle);
            action_image = (ImageView) itemView.findViewById(R.id.action_images);
        }
    }

    @NonNull
    @Override
    public SearchAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.newDAOFactory = new DAOFactory();
        return new SearchAdapter.LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_suggestion,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.LinearViewHolder viewHolder, int i) {
            final String tittle = searchActions[i].getActionTittle();
            final String des = searchActions[i].getActionDes();
            final Long id = searchActions[i].getId();
            viewHolder.action_tittle.setText( tittle );

            viewHolder.action_des.getSettings().setJavaScriptEnabled( true );
            viewHolder.action_des.setWebViewClient( new WebViewClient() );
            viewHolder.action_des.loadData( des,"text/html","UTF-8" );

            viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle(  );
                    bundle.putLong( "Id",id );
                    intent.putExtras(bundle);
                    intent.setClass( mContext, ActionActivity.class );
                    mContext.startActivity( intent );
                }
            } );

    }

    @Override
    public int getItemCount() {
        return searchActions.length;
    }
}
