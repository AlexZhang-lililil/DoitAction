package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;


import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class ActionDAOImp implements ActionDAO {


    public ActionDAOImp (){

    }



    public void insert(Action newAction){
        newAction.save();
        Log.d( "MenuActivity","insert complete" );

    }

    @Override
    public Action[] display(){

        return null;
    }
}
