package com.example.orquoll.swen90014_2018_or_quoll.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.SearchRecordAdapter;
import com.example.orquoll.swen90014_2018_or_quoll.R;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;


public class SearchHistoryFragment extends Fragment {

    private RecyclerView rv_searchHistory;
    private TextView txt_clearHistory;
    private SearchRecordAdapter newSearchRecordAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.layout_list_history,container,false );
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );

        rv_searchHistory = (RecyclerView) view.findViewById( R.id.rv_searchHistory );
        txt_clearHistory = (TextView) view.findViewById( R.id.txt_clearHistory );
        newSearchRecordAdapter = new SearchRecordAdapter(getActivity());

        txt_clearHistory.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DAOFactory newDAOFactory = new DAOFactory();
                newDAOFactory.getSearchRecorDAOImp().removeAll();
                newSearchRecordAdapter.notifyDataSetChanged();

            }
        } );

        rv_searchHistory.setLayoutManager( new LinearLayoutManager( this.getActivity() ) );
        rv_searchHistory.setAdapter( newSearchRecordAdapter );
    }
}
