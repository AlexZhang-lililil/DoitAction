package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MenuActivity extends AppCompatActivity {

    private Button btn_setting;
    private Button btn_menu ;
    private DrawerLayout dlo_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        dlo_menu = (DrawerLayout) findViewById(R.id.dlo_menu) ;


        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlo_menu.openDrawer (Gravity.LEFT);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MenuActivity.this,SettingActivity.class);
                startActivity(intent );

            }
        });
    }
}
