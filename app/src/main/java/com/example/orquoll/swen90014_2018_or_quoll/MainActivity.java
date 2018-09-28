package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.ActionDAO;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.ActionData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.StrengthData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.Strength_ActionData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.TagData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.Tag_ActionData;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength_Action;

import org.litepal.tablemanager.Connector;


public class MainActivity extends AppCompatActivity {
    private Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Login = (Button) findViewById(R.id.btn_login);

        Connector.getDatabase();

        DAOFactory newDAOFactory = new DAOFactory();
        if(newDAOFactory.getStrengthDAOImp().display().length==0) {

            StrengthData strengthData = new StrengthData();
            Strength_ActionData strength_actionData = new Strength_ActionData();
            Tag_ActionData tag_actionData = new Tag_ActionData();
            TagData tagData = new TagData();
            ActionData actionData = new ActionData();
            strengthData.saveAll();
            strength_actionData.saveAll();
            tag_actionData.saveAll();
            tagData.saveAll();
            actionData.saveAll();

        }

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this,MenuActivity.class);
                startActivity( i );
            }
        });


    }
}
