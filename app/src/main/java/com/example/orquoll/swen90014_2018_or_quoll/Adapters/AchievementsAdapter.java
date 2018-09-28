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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.MyStrengthActivity;
import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;

import java.util.zip.Inflater;

public class AchievementsAdapter extends RecyclerView.Adapter <AchievementsAdapter.LinearViewHolder> {
    private Context mContext;
    private DAOFactory newDAOFactory;
    private Strength[] strengths;

    public AchievementsAdapter (Context context){
        this.mContext = context;
        this.newDAOFactory = new DAOFactory();
        this.strengths = newDAOFactory.getStrengthDAOImp().display();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{

        private ImageView achieveImg;
        private TextView achieveTitle,lvl;
        private ProgressBar achievementProgress;
        private TextView achievePoint;

        public LinearViewHolder(View itemView){
            super(itemView);
            achieveImg = (ImageView)itemView.findViewById( R.id.achievement_img );
            achievementProgress = (ProgressBar)itemView.findViewById( R.id.achievement_progressbar );
            achieveTitle = (TextView) itemView.findViewById( R.id.achievement_title );
            lvl = (TextView) itemView.findViewById( R.id.achievement_level );
            achievePoint = (TextView) itemView.findViewById(R.id.achievement_progress);

        }
    }

    @NonNull
    @Override
    public AchievementsAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder( LayoutInflater.from( mContext ).inflate( R.layout.layout_list_achievements,viewGroup,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementsAdapter.LinearViewHolder viewHolder, int i) {
        this.strengths = newDAOFactory.getStrengthDAOImp().display();

        int level = strengths[i].getPoints()/25;
        viewHolder.achieveImg.setImageResource(strengths[i].getDrawableId());
        viewHolder.achieveTitle.setText(strengths[i].getStrength_Title());
        viewHolder.lvl.setText("Current Level :"+ level );
        viewHolder.achievementProgress.setProgress(strengths[i].getPoints()-level*25);
        viewHolder.achievePoint.setText((strengths[i].getPoints()-level*25)+"/25");
        final Long idInBundle =strengths[i].getId();
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putLong("StrengthId",idInBundle);
                Intent intent = new Intent ();
                intent.putExtras(bundle);
                intent.setClass(mContext, MyStrengthActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return strengths.length;
    }
}
