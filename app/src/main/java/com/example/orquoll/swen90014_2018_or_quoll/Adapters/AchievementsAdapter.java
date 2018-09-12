package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.R;

import java.util.zip.Inflater;

public class AchievementsAdapter extends RecyclerView.Adapter <AchievementsAdapter.LinearViewHolder> {
    private Context mContext;

    public AchievementsAdapter (Context context){
        this.mContext = context;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private ImageView achieveImg;
        private TextView achieveTitle,lvl;
        private ProgressBar achievementProgress;

        public LinearViewHolder(View itemView){
            super(itemView);
            achieveImg = (ImageView)itemView.findViewById( R.id.achievement_img );
            achievementProgress = (ProgressBar)itemView.findViewById( R.id.achievement_progressbar );
            achieveTitle = (TextView) itemView.findViewById( R.id.achievement_title );
            lvl = (TextView) itemView.findViewById( R.id.achievement_level );
        }
    }

    @NonNull
    @Override
    public AchievementsAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder( LayoutInflater.from( mContext ).inflate( R.layout.layout_list_achievements,viewGroup,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
