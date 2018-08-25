package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.LinearViewHolder> {

    private Context mContext;

    public SearchAdapter (Context context){
        this.mContext = context;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView action_tittle,action_content;
        private ImageView action_image;

        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            action_content = (TextView)itemView.findViewById(R.id.aciton_content);
            action_tittle = (TextView)itemView.findViewById(R.id.aciton_tittle);
            action_image = (ImageView) itemView.findViewById(R.id.action_images);
        }
    }

    @NonNull
    @Override
    public SearchAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SearchAdapter.LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_suggestion,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.LinearViewHolder linearViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
