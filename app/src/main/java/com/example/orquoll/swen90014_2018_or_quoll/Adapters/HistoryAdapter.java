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
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.ActionActivity;
import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.LinearViewHolder> {

    private Context mContext;
    private Action[] doneActions;
    private DAOFactory newDAOFactory;

    public HistoryAdapter(Context context){
        this.mContext = context;
        this.newDAOFactory = new DAOFactory();
        this.doneActions = newDAOFactory.getActionDAOImpInstance().showDoneAction();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView action_title;
        private WebView action_des;
        public LinearViewHolder(View itemview){
            super(itemview);
            action_title = (TextView) itemview.findViewById( R.id.aciton_tittle );
            action_des = (WebView)itemview.findViewById( R.id.aciton_content );
        }
    }
    @NonNull
    @Override
    public HistoryAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder( LayoutInflater.from( mContext ).inflate( R.layout.layout_list_suggestion,viewGroup,false ) );
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.LinearViewHolder viewHolder, int i) {
        String actionTitle = doneActions[i].getActionTittle();
        String actionDes = doneActions[i].getActionDes();
        final Long actionId = doneActions[i].getId();
        viewHolder.action_title.setText( actionTitle );
        viewHolder.action_des.setWebViewClient( new WebViewClient() );
        viewHolder.action_des.loadData( actionDes,"text/html","utf-8" );

        viewHolder.itemView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong( "Id",actionId );
                Intent intent = new Intent();
                intent.putExtras( bundle );
                intent.setClass( mContext,ActionActivity.class );
                mContext.startActivity( intent );
            }
        } );

    }

    @Override
    public int getItemCount() {
        return this.doneActions.length;
    }
}
