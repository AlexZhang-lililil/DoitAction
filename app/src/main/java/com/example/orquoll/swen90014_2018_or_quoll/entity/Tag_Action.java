package com.example.orquoll.swen90014_2018_or_quoll.entity;

import org.litepal.crud.LitePalSupport;

public class Tag_Action extends LitePalSupport{
    long id;
    long action_Id;
    long tag_Id;

    public Tag_Action(long action_Id, long tag_Id) {
        this.action_Id = action_Id;
        this.tag_Id = tag_Id;
    }

    public long getId() {
        return id;
    }

    public long getAction_Id() {
        return action_Id;
    }

    public void setAction_Id(long action_Id) {
        this.action_Id = action_Id;
    }

    public long getTag_Id() {
        return tag_Id;
    }

    public void setTag_Id(long tag_Id) {
        this.tag_Id = tag_Id;
    }
}
