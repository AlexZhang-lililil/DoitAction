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
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.ActionDAO;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.ActionDAOImp;
import com.example.orquoll.swen90014_2018_or_quoll.db.MyDatabaseHelper;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class MenuSuggestionAdapter extends RecyclerView.Adapter<MenuSuggestionAdapter.LinearViewHolder> {

    private Context mContext;

    public MenuSuggestionAdapter(Context context){
        this.mContext = context;
    }

    public ActionDAOImp newDAO;

    //test
    private MyDatabaseHelper dbHelper;
    private Action[] actions;

    @NonNull
    @Override
    public MenuSuggestionAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_list_suggestion,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuSuggestionAdapter.LinearViewHolder viewHolder, int i) {
        dbHelper = new MyDatabaseHelper( mContext,"Do-it",null,1 );
        newDAO = new ActionDAOImp( dbHelper );
        actions = newDAO.display();
        viewHolder.action_tittle.setText(actions[i].getActionTittle());
        viewHolder.action_content.setText(actions[i].getActionContent());
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
        return 1;
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
