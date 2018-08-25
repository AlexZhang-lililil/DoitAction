package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;

public class ActionActivity extends AppCompatActivity {

    private Button btn_back;
    private TextView txt_content;
    private TextView txt_tittle;
    private Long actionId;
    private Button btn_bookmark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        final DAOFactory newFactory = new DAOFactory();
        btn_back = (Button)findViewById( R.id.btn_back_action );
        txt_content = (TextView)findViewById( R.id.txt_action_content );
        txt_tittle = (TextView)findViewById( R.id.txt_action_tittle );
        btn_bookmark = (Button)findViewById(R.id.btn_mark);

        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        Bundle actionBundle = getIntent().getExtras();
        txt_tittle.setText(actionBundle.getString("Tittle"));
        txt_content.setText( actionBundle.getString("Content"));
        actionId = actionBundle.getLong("Id");

        setMarkState(actionId);

        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFactory.getActionDAOImpInstance().markAction(actionId);
                setMarkState(actionId);
            }
        });




    }
    public void setMarkState(Long actionId){
        DAOFactory newFactory = new DAOFactory();
        if(newFactory.getActionDAOImpInstance().searchById(actionId).isActionMarked()) {
            btn_bookmark.setBackground(getResources().getDrawable(R.drawable.icon_bookmark3));
        }else {
            btn_bookmark.setBackground(getResources().getDrawable(R.drawable.icon_bookmark2));
        }
    }
}


