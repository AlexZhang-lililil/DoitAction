package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Notification;

public class DAOFactory {
    private ActionDAOImp mAction;
    private SearchRecordDAOImp mSearch;
    private StrengthDAOImp mStrength;
    private S_ADAOImp mS_A;
    private T_ADAOImp mT_A;
    private SettingDAOImp mSetting;
    private TagDAOImp mTag;

    public DAOFactory(){
        this.mAction = new ActionDAOImp();
        this.mSearch = new SearchRecordDAOImp();
        this.mStrength = new StrengthDAOImp();
        this.mS_A = new S_ADAOImp();
        this.mT_A = new T_ADAOImp();
        this.mSetting = new SettingDAOImp();
        this.mTag = new TagDAOImp();
    }

    public ActionDAOImp getActionDAOImpInstance(){
        return mAction;
    }

    public SearchRecordDAOImp getSearchRecorDAOImp(){
        return mSearch;
    }

    public StrengthDAOImp getStrengthDAOImp(){ return mStrength;}

    public S_ADAOImp getS_ADAOImp(){return mS_A;}

    public TagDAOImp getTagDAOImp(){return mTag;}

    public T_ADAOImp getT_ADAOImp(){return mT_A;}

    public NotificationDAOImp getNotificationDAOImp(){
        return new NotificationDAOImp();
    }

    public SettingDAOImp getSettingDAOImp(){return mSetting;}
}
