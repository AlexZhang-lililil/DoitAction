package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.MenuSuggestionAdapter;


public class MenuActivity extends AppCompatActivity {

    private Button btn_search;
    private Button btn_menu ;
    private DrawerLayout dlo_menu;
    private TextView txt_bookmark;
    private TextView txt_history;
    private TextView txt_achievement;
    private TextView txt_setting;
    private RecyclerView rv_suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_menu = (Button) findViewById(R.id.btn_menu);
        txt_setting = (TextView) findViewById(R.id.txt_setting);
        dlo_menu = (DrawerLayout) findViewById(R.id.dlo_menu) ;
        txt_achievement = (TextView) findViewById(R.id.txt_achievements);
        txt_bookmark = (TextView) findViewById(R.id.txt_bookmarks);
        txt_history = (TextView) findViewById(R.id.txt_suggestion_history);
        btn_search = (Button) findViewById(R.id.btn_search);
        rv_suggestion = (RecyclerView)findViewById(R.id.rv_suggestion);


        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlo_menu.openDrawer (Gravity.LEFT);
            }
        });

        setListeners();

        rv_suggestion.setLayoutManager(new LinearLayoutManager(MenuActivity.this));
        rv_suggestion.setAdapter(new MenuSuggestionAdapter(MenuActivity.this));
        rv_suggestion.addItemDecoration(new Decoration());

    }

    private void setListeners (){
        Onclick onclick = new Onclick();
        txt_history.setOnClickListener(onclick);
        txt_bookmark.setOnClickListener(onclick);
        txt_achievement.setOnClickListener(onclick);
        btn_search.setOnClickListener(onclick);
        txt_setting.setOnClickListener(onclick);
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
                case R.id.btn_search:
                    intent = new Intent(MenuActivity.this,SearchActivity.class);
                    break;
                case R.id.txt_setting:
                    intent = new Intent(MenuActivity.this,SettingActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    class Decoration extends RecyclerView.ItemDecoration{
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            super.getItemOffsets(outRect,view,parent,state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divider));
        }
    }
}
