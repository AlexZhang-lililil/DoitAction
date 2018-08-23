package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

import org.litepal.LitePal;

import java.util.List;

public class ActionDAOImp implements ActionDAO {


    public ActionDAOImp (){

    }



    public void insert(Action newAction){
        newAction.save();

    }

    @Override
    public Action[] display(){

        List<Action> actions = LitePal.findAll( Action.class );
        Action[] actions_1 = new Action[actions.size()];
        actions.toArray(actions_1);
        return actions_1;
    }

    @Override
    public Action searchById(String id){
        List<Action> action = LitePal.where("id = ?",id).find(Action.class);

        return action.get(0);
    }
    @Override
    public void markAction (String actionId){

        Action actionMark = new Action();
        actionMark = searchById(actionId);
        if(actionMark.isActionMarked()){
            actionMark.setToDefault("actionMarked");
            actionMark.update(Long.valueOf(actionId));

        }else{
            actionMark.setActionMarked(true);
            actionMark.update(Long.valueOf(actionId));

        }
    }

    @Override
    public Action[] displayMarkedAction(){

        List<Action> actions  = LitePal.where("actionMarked = ?","1").find(Action.class);
        Action[] action = new Action[actions.size()];
        actions.toArray(action);
        return action;
    }


}
