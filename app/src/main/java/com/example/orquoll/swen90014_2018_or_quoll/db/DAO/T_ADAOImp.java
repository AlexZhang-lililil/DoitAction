package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Tag_Action;

import org.litepal.LitePal;

import java.util.List;

public class T_ADAOImp {
    Long[] getActionsIdByTagId (Long tagId){
        List<Tag_Action> tag_actions = LitePal.where("tag_Id = ?",tagId.toString()).find(Tag_Action.class);
        Long[] actionIds = new Long[tag_actions.size()];
        for(int i=0;i<actionIds.length;i++){
            actionIds[i] = tag_actions.get(i).getAction_Id();
        }
        return actionIds;
    }
}
