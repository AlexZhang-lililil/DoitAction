package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

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
}
