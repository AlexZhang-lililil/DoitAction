package com.example.orquoll.swen90014_2018_or_quoll.entity;

import org.litepal.crud.LitePalSupport;

public class Action extends LitePalSupport {
    private String actionTitle;
    private String actionContent;
    private boolean actionMarked;
    private String actionDes;
    private boolean actionDone;
    private long actionId;
    private long id;

    public Action(String actionTittle, String actionContent, boolean actionMarked) {
        this.actionTitle = actionTittle;
        this.actionContent = actionContent;
        this.actionMarked = actionMarked;
    }

    public Action(String actionTittle, String actionContent) {
        this.actionTitle = actionTittle;
        this.actionContent = actionContent;
    }

    public Action(){

    }

    public Action(long actionId,String action_Title,String action_Des,String action_Content){
        this.id=actionId;
        this.actionTitle = action_Title;
        this.actionDes = action_Des;
        this.actionContent = action_Content;
    }

    public String getActionDes() {
        return actionDes;
    }

    public void setActionDes(String actionDes) {
        this.actionDes = actionDes;
    }

    public long getActionId() {
        return actionId;
    }

    public void setActionId(long actionId) {
        this.actionId = actionId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public String getActionTittle() {
        return actionTitle;
    }

    public void setActionTittle(String actionTittle) {
        this.actionTitle = actionTittle;
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

    public boolean isActionDone() {
        return actionDone;
    }

    public void setActionDone(boolean actionDone) {
        this.actionDone = actionDone;
    }
}
