package com.example.orquoll.swen90014_2018_or_quoll.db.initializeData;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;

public class ActionData {
    private DAOFactory newDAOFactory;

    public ActionData() {
        this.newDAOFactory = new DAOFactory();
    }

    public void saveAction(long action_Id,String action_Title,String action_Des,String action_Content){
        Action action = new Action(action_Id,action_Title,action_Des,action_Content);
        action.save();
    }

    public void saveAll(){

    }
}
