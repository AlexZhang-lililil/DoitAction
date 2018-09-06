package com.example.orquoll.swen90014_2018_or_quoll.entity;

import org.litepal.crud.LitePalSupport;

public class Strength_Action extends LitePalSupport{
    long id;
    long strength_Id;
    long action_Id;

    public Strength_Action(long strength_Id, long action_Id) {
        this.strength_Id = strength_Id;
        this.action_Id = action_Id;
    }

    public long getId() {
        return id;
    }

    public long getStrength_Id() {
        return strength_Id;
    }

    public void setStrength_Id(long strength_Id) {
        this.strength_Id = strength_Id;
    }

    public long getAction_Id() {
        return action_Id;
    }

    public void setAction_Id(long action_Id) {
        this.action_Id = action_Id;
    }
}
