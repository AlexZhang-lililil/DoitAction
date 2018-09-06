package com.example.orquoll.swen90014_2018_or_quoll.entity;

import org.litepal.crud.LitePalSupport;

public class Tag extends LitePalSupport{
    String tag_Word;
    long id;

    public Tag(String tag_Word){
        this.tag_Word = tag_Word;
    }
    public long getId() {
        return id;
    }

    public String getTag_Word() {
        return tag_Word;
    }

    public void setTag_Word(String tag_Word) {
        this.tag_Word = tag_Word;
    }
}
