package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Notification;

public class DAOFactory {

    public  ActionDAOImp getActionDAOImpInstance(){
        return new ActionDAOImp();
    }

    public SearchRecordDAOImp getSearchRecorDAOImp(){
        return new SearchRecordDAOImp();
    }

    public StrengthDAOImp getStrengthDAOImp(){ return new StrengthDAOImp();}

    public S_ADAOImp getS_ADAOImp(){return new S_ADAOImp();}

    public TagDAOImp getTagDAOImp(){return new TagDAOImp();}

    public T_ADAOImp getT_ADAOImp(){return new T_ADAOImp();}

    public NotificationDAOImp getNotificationDAOImp(){
        return new NotificationDAOImp();
    }
}
