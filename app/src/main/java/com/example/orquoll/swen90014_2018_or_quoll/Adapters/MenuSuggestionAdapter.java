package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.ActionActivity;
import com.example.orquoll.swen90014_2018_or_quoll.MenuActivity;
import com.example.orquoll.swen90014_2018_or_quoll.R;

public class MenuSuggestionAdapter extends RecyclerView.Adapter<MenuSuggestionAdapter.LinearViewHolder> {

    private Context mContext;

    public MenuSuggestionAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public MenuSuggestionAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_suggestion,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuSuggestionAdapter.LinearViewHolder viewHolder, int i) {
        viewHolder.action_tittle.setText("This is the tittle of action "+i);
        viewHolder.action_content.setText("This is the context of action "+i);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass( mContext,ActionActivity.class );
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 15;
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
