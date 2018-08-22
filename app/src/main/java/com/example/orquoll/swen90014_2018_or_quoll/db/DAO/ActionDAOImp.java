package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.db.MyDatabaseHelper;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class ActionDAOImp implements ActionDAO {

    private MyDatabaseHelper dbHelper;
    private SQLiteDatabase db;

    public ActionDAOImp (MyDatabaseHelper dbHelper){
        this.dbHelper = dbHelper;
    }



    @Override
    public void insert(Action newAction){

        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues(  );
        values.put("tittle",newAction.getActionTittle());
        values.put( "content",newAction.getActionContent() );
        values.put( "marked",newAction.isActionMarked() );
        db.insert( "Actions",null,values );

    }

    @Override
    public Action[] display(){
        Action[] actions = new Action[100];
        Action action1 = new Action("1","2");
        actions[0] = action1;
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query( "Actions",null,null,null,null,null,null );
        if(cursor.moveToFirst()){
            do{
                int i=0;
                String tittle = cursor.getString(cursor.getColumnIndex( "tittle" ));
                String content = cursor.getString( cursor.getColumnIndex( "content" ) );
                Log.d("MenuActivity","actiontittle is "+tittle);
                Log.d("MenuActivity","actioncontent is "+content);
                Action newAction = new Action(cursor.getString(cursor.getColumnIndex( "tittle" )),cursor.getString( cursor.getColumnIndex( "content" )));

                actions[i] = newAction;
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
        return actions;
    }
}
