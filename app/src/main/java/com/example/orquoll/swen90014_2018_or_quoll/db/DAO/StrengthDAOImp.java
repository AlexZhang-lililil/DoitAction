package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength;

import org.litepal.LitePal;

import java.util.List;

public class StrengthDAOImp implements StrengthDAO {

    @Override
    public Strength[] display(){
         List<Strength> strengths = LitePal.findAll(Strength.class);
         Strength[] strengths1 = new Strength[strengths.size()] ;
         strengths.toArray(strengths1);
        return strengths1;
    }

    public void insertNewStrength (Strength newStrength){
        newStrength.save();
    }

    public Strength getStrengthById(Long id){
        Strength strength ;
        List<Strength> strengths;
        strengths = LitePal.where("id = ?",id.toString()).find(Strength.class);
        strength = strengths.get(0);
        return strength;
    }

    public void addPoints(Long strengthId){
        Strength thisStrength = getStrengthById(strengthId);
        Strength updatedStrength = new Strength();
        updatedStrength.setPoints(thisStrength.getPoints()+5);
        updatedStrength.update(strengthId);
    }
}
