package com.example.orquoll.swen90014_2018_or_quoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

public class SearchActivity extends AppCompatActivity  {

    private Button btn_back;
    private SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn_back = (Button) findViewById(R.id.btn_back_search);
        search = (SearchView) findViewById(R.id.srh) ;

        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(true);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
