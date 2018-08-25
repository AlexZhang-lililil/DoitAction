package com.example.orquoll.swen90014_2018_or_quoll.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.SearchRecord;

import java.util.zip.Inflater;

public class SearchRecordAdapter extends RecyclerView.Adapter <SearchRecordAdapter.LinearViewHolder>{

    private Context mContext;
    private DAOFactory newDAOFactory;

    public SearchRecordAdapter (Context context){
        this.mContext = context;
        newDAOFactory = new DAOFactory();
    }
    class LinearViewHolder extends RecyclerView.ViewHolder{
        private TextView record;

        public LinearViewHolder(View itemView){
            super(itemView);
            record = itemView.findViewById( R.id.txt_record );
        }
    }
    @NonNull
    @Override
    public SearchRecordAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder( LayoutInflater.from( mContext ).inflate( R.layout.layout_list_record ,viewGroup,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull SearchRecordAdapter.LinearViewHolder viewHolder, int i) {
        SearchRecord[] records = newDAOFactory.getSearchRecorDAOImp().display();
        final String recordName = records[i].getName();
        final Long id = records[i].getId();
        viewHolder.record.setText( recordName );


    }

    @Override
    public int getItemCount() {
        return newDAOFactory.getSearchRecorDAOImp().display().length;
    }
}
