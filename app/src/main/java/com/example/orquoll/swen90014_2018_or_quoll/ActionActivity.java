package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class ActionActivity extends AppCompatActivity {

    private Button btn_back;
    private WebView txt_content;
    private TextView txt_tittle;
    private Long actionId;
    private Button btn_bookmark;
    private DAOFactory newDAOFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        final DAOFactory newFactory = new DAOFactory();
        btn_back = (Button)findViewById( R.id.btn_back_action );
        txt_content = (WebView)findViewById( R.id.txt_action_content );
        txt_tittle = (TextView)findViewById( R.id.txt_action_tittle );
        btn_bookmark = (Button)findViewById(R.id.btn_mark);
        newDAOFactory = new DAOFactory();

        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        Bundle actionBundle = getIntent().getExtras();

        actionId = actionBundle.getLong("Id");

        setMarkState(actionId);

        Action thisAction = newDAOFactory.getActionDAOImpInstance().searchById( actionId );
        txt_content.setWebViewClient( new WebViewClient() );
        txt_content.getSettings().setJavaScriptEnabled( true );
        txt_content.loadData( thisAction.getActionContent(),"text/html","UTF-8" );
        txt_tittle.setText( thisAction.getActionTittle() );



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


