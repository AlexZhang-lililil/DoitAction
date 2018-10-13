package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Notification;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class NotificationDAOImp {
    public Notification[] getAll(){
        Notification[] notifications = null;
        LitePal.findAll(Notification.class).toArray(notifications);
        return notifications;
    }

    public void insertNotification(Notification newNoti){
        newNoti.save();
    }


}
