package com.example.orquoll.swen90014_2018_or_quoll.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_ACTION = "create table Actions ("
            + "id integer primary key autoincrement,"
            + "tittle text,"
            + "content text,"
            + "marked blob)";

    public  MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL( CREATE_ACTION );
        Toast.makeText(mContext,"CreateSuccessed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL( "drop table if exists Actions" );
        onCreate(db);
    }


}
