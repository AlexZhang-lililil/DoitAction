package com.example.orquoll.swen90014_2018_or_quoll;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.BookmarksAdapter;

public class BookmarksActivity extends AppCompatActivity {

    private Button btn_back;
    private RecyclerView rv_bookmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        btn_back = (Button) findViewById(R.id.btn_back_bookmarks);
        rv_bookmarks = (RecyclerView) findViewById((R.id.rv_bookmarks));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rv_bookmarks.setLayoutManager(new LinearLayoutManager(BookmarksActivity.this));
        rv_bookmarks.setAdapter(new BookmarksAdapter(BookmarksActivity.this));
        rv_bookmarks.addItemDecoration(new Decoration());


    }

    class Decoration extends RecyclerView.ItemDecoration{
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            super.getItemOffsets(outRect,view,parent,state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divider));
        }
    }

}
