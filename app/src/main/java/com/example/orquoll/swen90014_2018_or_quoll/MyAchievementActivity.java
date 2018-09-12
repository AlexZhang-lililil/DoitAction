package com.example.orquoll.swen90014_2018_or_quoll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.AchievementsAdapter;

public class MyAchievementActivity extends AppCompatActivity {

    private Button btn_back;
    private RecyclerView rv_achievement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_achievement);

        btn_back = (Button) findViewById(R.id.btn_back_achievement);
        rv_achievement = (RecyclerView) findViewById(R.id.rv_achievement);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rv_achievement.setLayoutManager(new LinearLayoutManager(this));
        rv_achievement.setAdapter(new AchievementsAdapter(this));

    }
}
