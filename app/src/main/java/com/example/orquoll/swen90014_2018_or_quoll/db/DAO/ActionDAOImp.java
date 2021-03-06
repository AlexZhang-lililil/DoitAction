package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import android.database.Cursor;
import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    public Action searchById(Long id){
        List<Action> action = LitePal.where("id = ?",id.toString()).find(Action.class);

        return action.get(0);
    }

    public Action searchByActionId(Long id){
        List<Action> action = LitePal.where("actionId = ?",id.toString()).find(Action.class);
        return action.get(0);
    }

    @Override
    public void markAction (Long actionId){

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

    @Override
    public Action[] searchAll(String name){
        List<Action> actions = LitePal.where( "actionTitle = ?",name ).find( Action.class );
        Action[] actions1 = new Action[actions.size()];
        actions.toArray(actions1);
        return  actions1;
    }

    @Override
    public Action[] fuzzySearch(String keywords){
        List<Action> actions = new ArrayList<Action>(  );
        Cursor c = LitePal.findBySQL( "select * from Action where actionTitle like '%"+keywords+"%'" );

        if(c.moveToFirst()){
            do{
                Action action = new Action();
                action.setActionDes( c.getString( c.getColumnIndex( "actiondes" ) ) );
                action.setActionContent( c.getString( c.getColumnIndex( "actioncontent" )) );
                action.setActionTittle( c.getString( c.getColumnIndex( "actiontitle" ) ) );
                action.setId( c.getLong(c.getColumnIndex( "id" )) );
                actions.add( action );
            }while(c.moveToNext());
        }

        c.close();
        Action[] actions1 = new Action[actions.size()];
        actions.toArray(actions1);
        return actions1;
    }

    public Action[] showDoneAction(){
        List<Action> actions  = LitePal.where("actionDone = ?","1").find(Action.class);
        Action[] action = new Action[actions.size()];
        actions.toArray(action);
        return action;
    }

    public void doAction(Long actionId){
        Action actionDone = new Action();
        actionDone = searchById(actionId);

        //get current time
        SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-YYYY HH:mm");
        Date currentTime = new Date(System.currentTimeMillis());
        String doneDate = newFormat.format(currentTime);

        if(!actionDone.isActionDone()){
            actionDone.setActionDone(true);
            actionDone.setDoneDate(doneDate);
            actionDone.update(Long.valueOf(actionId));
        }else{
            actionDone.setDoneDate(doneDate);
            actionDone.update(Long.valueOf(actionId));
        }
    }

    public Action[] showActionByIds(Long[] actions_Id){
        Action[] actions = new Action[actions_Id.length];
        for(int i=0;i<actions.length;i++){
            actions[i] = searchByActionId(actions_Id[i]);
        }
        return actions;
    }

    public Action[] combineSearch(String keywords,Long[] action_Id){
        Action[] actions1 = fuzzySearch(keywords);
        Action[] actions2 = showActionByIds(action_Id);
        List<Action> actionlist1 = new ArrayList<Action>();
        List<Action> actionlist2 = Arrays.asList(actions2);
       for(int i=0;i<actions1.length;i++){
           for(int m = 0;m<actionlist2.size();m++){
               if(actionlist2.get(m).getId() == actions1[i].getId())
                   actionlist1.add(actionlist2.get(m));
           }
       }
       Action[] result = new Action[actionlist1.size()];
       return actionlist1.toArray(result);
    }

}
