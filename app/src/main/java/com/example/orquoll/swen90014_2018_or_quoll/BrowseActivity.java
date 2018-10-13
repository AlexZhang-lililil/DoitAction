package com.example.orquoll.swen90014_2018_or_quoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.BrowseAdapter;
import com.example.orquoll.swen90014_2018_or_quoll.NotifyAction.LocationTrigger;
import com.example.orquoll.swen90014_2018_or_quoll.NotifyAction.TriggerStrategy;

public class BrowseActivity extends AppCompatActivity {

    private Button btn_back_browse;
    private RecyclerView rv_browse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);

        btn_back_browse = (Button) findViewById(R.id.btn_back_browse);
        rv_browse = (RecyclerView) findViewById(R.id.rv_browse);

        rv_browse.setLayoutManager(new LinearLayoutManager(this));
        rv_browse.setAdapter(new BrowseAdapter(this));

        btn_back_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LocationTrigger newTrigger = new LocationTrigger(this,this);

    }
}
