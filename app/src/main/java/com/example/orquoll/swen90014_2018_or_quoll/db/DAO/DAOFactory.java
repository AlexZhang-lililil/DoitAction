package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

public class DAOFactory {

    public  ActionDAOImp getActionDAOImpInstance(){
        return new ActionDAOImp();
    }

    public SearchRecordDAOImp getSearchRecorDAOImp(){
        return new SearchRecordDAOImp();
    }

    public StrengthDAOImp getStrengthDAOImp(){ return new StrengthDAOImp();}
}
