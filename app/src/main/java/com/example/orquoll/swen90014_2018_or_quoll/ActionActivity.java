package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class ActionActivity extends AppCompatActivity {

    private Button btn_back;
    private WebView txt_content;
    private TextView txt_tittle;
    private Long id;
    private Long action_Id;
    private Button btn_bookmark;
    private DAOFactory newDAOFactory;
    private Button btn_did_it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);

        final DAOFactory newFactory = new DAOFactory();
        btn_back = (Button)findViewById( R.id.btn_back_action );
        txt_content = (WebView)findViewById( R.id.txt_action_content );
        txt_tittle = (TextView)findViewById( R.id.txt_action_tittle );
        btn_bookmark = (Button)findViewById(R.id.btn_mark);
        btn_did_it = (Button) findViewById( R.id.btn_did_it );
        newDAOFactory = new DAOFactory();

        btn_back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );
        btn_did_it.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFactory.getActionDAOImpInstance().doAction( id );
                action_Id = newFactory.getActionDAOImpInstance().searchById(id).getActionId();
                for(int i=0;i<newFactory.getS_ADAOImp().getSIdByAId(action_Id).length;i++){
                    newFactory.getStrengthDAOImp().addPoints(newFactory.getS_ADAOImp().getSIdByAId(action_Id)[i]);
                }

                feedback();

            }
        } );

        Bundle actionBundle = getIntent().getExtras();

        id = actionBundle.getLong("Id");

        setMarkState(id);

        Action thisAction = newDAOFactory.getActionDAOImpInstance().searchById( id );
        txt_content.setWebViewClient( new WebViewClient() );
        txt_content.getSettings().setJavaScriptEnabled( true );
        txt_content.loadData( thisAction.getActionContent(),"text/html","UTF-8" );
        txt_tittle.setText( thisAction.getActionTittle() );



        btn_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFactory.getActionDAOImpInstance().markAction(id);
                setMarkState(id);
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

    private void feedback(){
        final String[] feedbacks = {"Really Helpful!","Alright~","Useless","Really Bad!"};
        AlertDialog.Builder newAlert = new AlertDialog.Builder( this );
        newAlert.setTitle( "After doing it,I think this action is ..." );
        newAlert.setSingleChoiceItems( feedbacks, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        } );
        newAlert.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText( ActionActivity.this,"You have earned 5 marks for doing it!",Toast.LENGTH_LONG ).show();
            }
        } );
        newAlert.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        } );
        newAlert.show();
    }
}


