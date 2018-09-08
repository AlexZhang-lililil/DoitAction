package com.example.orquoll.swen90014_2018_or_quoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.HistoryAdapter;

public class SuggestionHistoryActivity extends AppCompatActivity {

    private Button btn_back;
    private RecyclerView rv_history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_history);

        btn_back = (Button) findViewById(R.id.btn_back_suggestion_history);
        rv_history = (RecyclerView) findViewById( R.id.rv_doit_history );

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
