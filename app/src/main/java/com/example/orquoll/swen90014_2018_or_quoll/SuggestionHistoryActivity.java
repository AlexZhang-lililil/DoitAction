package com.example.orquoll.swen90014_2018_or_quoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.HistoryAdapter;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class SuggestionHistoryActivity extends AppCompatActivity {

    private Button btn_back;
    private RecyclerView rv_history;
    private DAOFactory newDAOFactory;
    private TextView history_doits;
    private TextView history_strengths;
    private Action[] actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_history);

        btn_back = (Button) findViewById(R.id.btn_back_suggestion_history);
        rv_history = (RecyclerView) findViewById( R.id.rv_doit_history );
        history_doits = (TextView) findViewById( R.id.txt_completeDoits );
        history_strengths = (TextView) findViewById( R.id.txt_shownStrength );
        newDAOFactory = new DAOFactory();
        actions = newDAOFactory.getActionDAOImpInstance().showDoneAction();

        history_strengths.setText( "You have shown "+ newDAOFactory.getS_ADAOImp().getActionsStrength( actions )+" strengths!");
        history_doits.setText( "You have completed "+actions.length+" do-its!" );
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        rv_history.setLayoutManager( new LinearLayoutManager( this ) );
        rv_history.setAdapter( new HistoryAdapter( this ) );

    }
}
