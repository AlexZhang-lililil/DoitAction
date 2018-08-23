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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.ActionActivity;
import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

import org.w3c.dom.Text;

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.LinearViewHolder> {

    private Context mContext;

    public BookmarksAdapter(Context context){
        this.mContext = context;
    }

    private DAOFactory newDAOFactory = new DAOFactory();

    @NonNull
    @Override
    public BookmarksAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_bookmarks,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarksAdapter.LinearViewHolder viewHolder, int i) {
        Action[] actions = newDAOFactory.getActionDAOImpInstance().displayMarkedAction();
        viewHolder.action_Tittle.setText(actions[i].getActionTittle());
        viewHolder.action_Content.setText(actions[i].getActionContent());
        final String tittle = actions[i].getActionTittle();
        final String content = actions[i].getActionContent();
        final String id = actions[i].getId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent ();
                intent.setClass(mContext, ActionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Id",id);
                bundle.putString("Tittle",tittle);
                bundle.putString("Content",content);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newDAOFactory.getActionDAOImpInstance().displayMarkedAction().length;

    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView action_Tittle,action_Content;
        private ImageView action_Image;

        public LinearViewHolder(View itemView){
            super(itemView);
            action_Content = (TextView)itemView.findViewById(R.id.aciton_content1);
            action_Tittle = (TextView)itemView.findViewById(R.id.aciton_tittle1);
            action_Image = (ImageView)itemView.findViewById(R.id.action_images1);

        }
    }
}
