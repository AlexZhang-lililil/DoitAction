package com.example.orquoll.swen90014_2018_or_quoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;

import org.w3c.dom.Text;

public class StrengthActivity extends AppCompatActivity {

    private Button btn_back_stength;
    private Button btn_my_strength;
    private WebView wv_strength;
    private TextView tb_strength_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_strength );

        btn_back_stength = (Button) findViewById( R.id.btn_back_strength );
        wv_strength = (WebView) findViewById( R.id.wv_strength );
        tb_strength_title = (TextView) findViewById( R.id.tb_strength_title );
        btn_my_strength = (Button) findViewById( R.id.my_Strength );

        btn_back_stength.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        } );

        Bundle bundle = getIntent().getExtras();
        DAOFactory newDAOFactory = new DAOFactory();

        Strength thisStrength = newDAOFactory.getStrengthDAOImp().getStrengthById( bundle.getLong( "strength_Id" ) );

        tb_strength_title.setText( thisStrength.getStrength_Title() );
        btn_my_strength.setText( "My "+ thisStrength.getStrength_Title() );

        wv_strength.getSettings().setJavaScriptEnabled( true );
        wv_strength.setWebViewClient( new WebViewClient() );
        wv_strength.loadData( thisStrength.getStrength_Content(),"text/html","UTF-8" );


    }
}
