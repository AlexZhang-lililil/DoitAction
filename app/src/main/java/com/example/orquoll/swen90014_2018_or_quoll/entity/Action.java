package com.example.orquoll.swen90014_2018_or_quoll.entity;

import org.litepal.crud.LitePalSupport;

public class Action extends LitePalSupport {
    private String actionTittle;
    private String actionContent;
    private boolean actionMarked;
    private long id;

    public Action(String actionTittle, String actionContent, boolean actionMarked) {
        this.actionTittle = actionTittle;
        this.actionContent = actionContent;
        this.actionMarked = actionMarked;
    }

    public Action(String actionTittle, String actionContent) {
        this.actionTittle = actionTittle;
        this.actionContent = actionContent;
    }

    public Action(){

    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getActionTittle() {
        return actionTittle;
    }

    public void setActionTittle(String actionTittle) {
        this.actionTittle = actionTittle;
    }

    public String getActionContent() {
        return actionContent;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    public boolean isActionMarked() {
        return actionMarked;
    }

    public void setActionMarked(boolean actionMarked) {
        this.actionMarked = actionMarked;
    }
}
