package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

import org.litepal.tablemanager.Connector;


public class MainActivity extends AppCompatActivity {
    private Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Login = (Button) findViewById(R.id.btn_login);

        Connector.getDatabase();
        Action action_5 = new Action("Mindful Thoughts Practise","Lorem ipsum dolor sit amet",false);
        action_5.save();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this,MenuActivity.class);
                startActivity( i );
            }
        });


    }
}
