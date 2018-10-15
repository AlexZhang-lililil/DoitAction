package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Setting;

import org.litepal.LitePal;

import java.util.List;

public class SettingDAOImp {
    public Setting getSetting(){
        List<Setting> settings = LitePal.findAll( Setting.class );
        return settings.get(0);
    }

    public void updateSetting(int number){
        Setting settingData = getSetting();
        settingData.setNumber(number);
        settingData.save();
    }

    public void initialSetting(int number){
        Setting newSetting = new Setting(number);
        newSetting.save();
    }
}
