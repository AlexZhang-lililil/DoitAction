package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

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
        strength = LitePal.find(Strength.class,id);
        return strength;
    }
}
