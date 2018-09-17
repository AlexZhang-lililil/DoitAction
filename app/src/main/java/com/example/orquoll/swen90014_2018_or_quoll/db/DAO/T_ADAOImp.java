package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Tag_Action;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class T_ADAOImp {

    public Long[] getActionIdByTagId(Long tagId){
        Log.d("this",tagId.toString());
        List<Tag_Action> tag_actions= LitePal.where( "tag_id = ? ",tagId.toString() ).find( Tag_Action.class );
        Log.d("this",String.valueOf(tag_actions.size()));
        Long[] actionIds = new Long[tag_actions.size()];
        for(int i =0;i<tag_actions.size();i++){
            actionIds[i] = tag_actions.get( i ).getAction_Id();
        }
        return actionIds;
    }
}
