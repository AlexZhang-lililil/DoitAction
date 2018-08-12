package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.TextView;
import android.widget.Toolbar;



public class MenuActivity extends AppCompatActivity {

    private Button btn_setting;
    private Button btn_menu ;
    private DrawerLayout dlo_menu;
    private SearchView search;
    private TextView txt_bookmark;
    private TextView txt_history;
    private TextView txt_achievement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_menu = (Button) findViewById(R.id.btn_menu);
        btn_setting = (Button) findViewById(R.id.btn_setting);
        dlo_menu = (DrawerLayout) findViewById(R.id.dlo_menu) ;
        search = (SearchView) findViewById(R.id.srh) ;
        txt_achievement = (TextView) findViewById(R.id.txt_achievements);
        txt_bookmark = (TextView) findViewById(R.id.txt_bookmarks);
        txt_history = (TextView) findViewById(R.id.txt_suggestion_history);


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

        setListeners();

        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(true);

    }

    private void setListeners (){
        Onclick onclick = new Onclick();
        txt_history.setOnClickListener(onclick);
        txt_bookmark.setOnClickListener(onclick);
        txt_achievement.setOnClickListener(onclick);
    }

    private class Onclick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId())
            {
                case R.id.txt_achievements:
                    intent = new Intent(MenuActivity.this,MyAchievementActivity.class);
                    break;
                case R.id.txt_bookmarks:
                    intent = new Intent(MenuActivity.this,BookmarksActivity.class);
                    break;
                case R.id.txt_suggestion_history:
                    intent = new Intent(MenuActivity.this,SuggestionHistoryActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

}
