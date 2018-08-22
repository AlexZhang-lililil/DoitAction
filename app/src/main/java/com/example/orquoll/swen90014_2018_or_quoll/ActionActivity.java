package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActionActivity extends AppCompatActivity {

    private Button btn_back;
    private TextView txt_content;
    private TextView txt_tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        btn_back = (Button)findViewById( R.id.btn_back_action );
        txt_content = (TextView)findViewById( R.id.txt_action_content );
        txt_tittle = (TextView)findViewById( R.id.txt_action_tittle );

        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        Intent intent = getIntent();
        txt_tittle.setText( intent.getStringExtra( "Tittle" ) );
        txt_content.setText( intent.getStringExtra( "Content" ) );



    }

}
