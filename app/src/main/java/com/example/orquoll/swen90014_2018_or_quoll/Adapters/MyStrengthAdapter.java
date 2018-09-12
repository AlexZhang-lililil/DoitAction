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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.ActionActivity;
import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;

public class MyStrengthAdapter extends RecyclerView.Adapter<MyStrengthAdapter.LinearViewHolder> {

    private Context mContext;
    private DAOFactory newDAOFactory;
    private Action[] actions;

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView actionTitle;
        private WebView actionDes;

        public LinearViewHolder (View itemView){
            super(itemView);

            actionDes =(WebView)itemView.findViewById(R.id.aciton_content);
            actionTitle = (TextView) itemView.findViewById(R.id.aciton_tittle);
        }
    }

    public MyStrengthAdapter(Context context,Long strengthId){
        this.mContext = context;
        newDAOFactory = new DAOFactory();
        actions = newDAOFactory.getActionDAOImpInstance().showActionByIds(newDAOFactory.getS_ADAOImp().getAIdBySId(strengthId));
    }

    @NonNull
    @Override
    public MyStrengthAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_suggestion,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyStrengthAdapter.LinearViewHolder viewHolder, int i) {
        final String title = actions[i].getActionTittle();
        final String des = actions[i].getActionDes();
        final Long id = actions[i].getId();
        viewHolder.actionTitle.setText(title);
        viewHolder.actionDes.loadData(des,"text/html","utf-8");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("Id",id);
                Intent intent = new Intent ();
                intent.putExtras(bundle);
                intent.setClass(mContext, ActionActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return actions.length;
    }
}
