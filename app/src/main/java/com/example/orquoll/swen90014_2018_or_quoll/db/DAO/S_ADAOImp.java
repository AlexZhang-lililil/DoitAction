package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength_Action;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class S_ADAOImp {

    /*    public int getActionsStrength(Action[] actions){
            List<Strength> strengths;
            List<Strength> allStrength = new ArrayList<Strength>(  );
            for(int i=0;i<actions.length;i++){
                strengths = LitePal.where("actionTitle",actions[i].getActionTittle()).find(Strength.class);
                for(int m=0;m<strengths.size();i++){
                    for(int n=0;n<allStrength.size();n++){

                    }
                }
            }

            return 0;
        }
        */
    public int getActionsStrength(Action[] actions) {

        List<Strength_Action> allStrength = new ArrayList<Strength_Action>();
        for (int i = 0; i < actions.length; i++) {

            List<Strength_Action> strength_actions = LitePal.where( "action_id = ?", new Long( actions[i].getActionId() ).toString() ).find( Strength_Action.class );
            Log.d("dddd",String.valueOf( actions[i].getActionId() ));
            for (int m = 0; m < strength_actions.size(); m++) {
                boolean isInserted = false;
                for (int n = 0; n < allStrength.size(); n++) {
                    if (allStrength.get( n ).getStrength_Id() == strength_actions.get( m ).getStrength_Id()) {
                        isInserted = true;
                        break;
                    }
                }
                if (!isInserted) {
                    allStrength.add( strength_actions.get( m ) );
                }
            }
        }
        return allStrength.size();
    }
}
