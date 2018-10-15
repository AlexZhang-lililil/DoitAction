package com.example.orquoll.swen90014_2018_or_quoll;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.MenuSuggestionAdapter;
import com.example.orquoll.swen90014_2018_or_quoll.NotifyAction.LocationTrigger;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.ActionDAOImp;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;

import org.litepal.tablemanager.Connector;
import org.w3c.dom.Text;

import java.util.HashSet;
import java.util.Random;


public class MenuActivity extends AppCompatActivity {

    private Button btn_search;
    private Button btn_menu ;
    private DrawerLayout dlo_menu;
    private TextView txt_bookmark;
    private TextView txt_history;
    private TextView txt_achievement;
    private TextView txt_setting;
    private TextView txt_browse;
    private RecyclerView rv_suggestion;
    private DAOFactory newFactory = new DAOFactory();
    private Button refresh;
    private LocationTrigger newTrigger;
    private final MenuSuggestionAdapter newMenuSuggestionAdapter = new MenuSuggestionAdapter( this,getRandom() );


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
        txt_browse = (TextView) findViewById(R.id.txt_browse);
        btn_search = (Button) findViewById(R.id.btn_search);
        rv_suggestion = (RecyclerView)findViewById(R.id.rv_suggestion);
        refresh = (Button) findViewById( R.id.refresh );
        this.newTrigger = new LocationTrigger( this,this );

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlo_menu.openDrawer (Gravity.LEFT);
            }
        });

        setListeners();

        rv_suggestion.setLayoutManager(new LinearLayoutManager(MenuActivity.this));
        rv_suggestion.setAdapter(newMenuSuggestionAdapter);
        rv_suggestion.addItemDecoration(new Decoration());

        refresh.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Action[] actions = newTrigger.getNotification();
                newMenuSuggestionAdapter.setActions( actions );
                newMenuSuggestionAdapter.notifyDataSetChanged();
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();

        int newNum = newFactory.getSettingDAOImp().getSetting().getNumber();
        newMenuSuggestionAdapter.setSuggestionNum(newNum);
        newMenuSuggestionAdapter.notifyDataSetChanged();
    }


    private void setListeners (){
        Onclick onclick = new Onclick();
        txt_history.setOnClickListener(onclick);
        txt_bookmark.setOnClickListener(onclick);
        txt_achievement.setOnClickListener(onclick);
        btn_search.setOnClickListener(onclick);
        txt_setting.setOnClickListener(onclick);
        txt_browse.setOnClickListener(onclick);
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
                case R.id.txt_browse:
                    intent = new Intent(MenuActivity.this,BrowseActivity.class);
                    break;
            }
            startActivity(intent);
        }
    }

    //draw the lines between items
    class Decoration extends RecyclerView.ItemDecoration{
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            super.getItemOffsets(outRect,view,parent,state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divider));
        }
    }

    private Action[] getRandom (){
        Random random = new Random();
        HashSet<Action> actions = new HashSet<Action>();
        Action[] allActions = newFactory.getActionDAOImpInstance().display();
        while(actions.size()<10){
            actions.add(allActions[random.nextInt(allActions.length)]);
        }
        Action[] randomedActions = new Action[actions.size()];
        actions.toArray(randomedActions);
        return randomedActions;
    }
}
