package com.example.orquoll.swen90014_2018_or_quoll;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.SearchAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private Button btn_back;
    private SearchView search;
    private RecyclerView rv_search;
    private SearchAdapter newSearchAdapter;
    private ArrayList<String> searchTagList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchTagList = new ArrayList<String>();
        btn_back = (Button) findViewById(R.id.btn_back_search);
        search = (SearchView) findViewById(R.id.srh) ;
        rv_search = (RecyclerView) findViewById(R.id.rv_search);

        initialize();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchTagList.add(s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s.trim())){

                }else{
                    searchTagList.clear();
                    newSearchAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    class Decoration extends RecyclerView.ItemDecoration{
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            super.getItemOffsets(outRect,view,parent,state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divider));
        }
    }

    public void initialize(){
        this.newSearchAdapter = new SearchAdapter(this);
        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(true);

        rv_search.setLayoutManager(new LinearLayoutManager(this));
        rv_search.setAdapter(newSearchAdapter);
        rv_search.addItemDecoration(new Decoration());
    }
}
