package com.example.orquoll.swen90014_2018_or_quoll.entity;

import org.litepal.crud.LitePalSupport;

public class Strength extends LitePalSupport{
    private String strength_Tittle;
    private String strength_Content;
    private String strength_description;
    private int drawableId;
    private long id;

    public String getStrength_description() {
        return strength_description;
    }

    public void setStrength_description(String strength_description) {
        this.strength_description = strength_description;
    }



    public Strength(String strength_Tittle, String strength_Content,String description, int drawableId) {
        this.strength_Tittle = strength_Tittle;
        this.strength_Content = strength_Content;
        this.drawableId = drawableId;
        this.strength_description = description;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getStrength_Tittle() {
        return strength_Tittle;
    }

    public void setStrength_Tittle(String strength_Tittle) {
        this.strength_Tittle = strength_Tittle;
    }

    public String getStrength_Content() {
        return strength_Content;
    }

    public void setStrength_Content(String strength_Content) {
        this.strength_Content = strength_Content;
    }

    public long getId() {
        return id;
    }
}
