package com.example.orquoll.swen90014_2018_or_quoll.entity;

import org.litepal.crud.LitePalSupport;

public class Tag extends LitePalSupport{
    String tag_Word;
    long parent_id;
    long tag_id;
    long id;

    public Tag(String tag_Word,long parent_id,long tag_id){
        this.tag_Word = tag_Word;
        this.parent_id = parent_id;
        this.tag_id = tag_id;
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

    public long getParent_id() {
        return parent_id;
    }

    public void setParent_id(long parent_id) {
        this.parent_id = parent_id;
    }

    public long getTag_id() {
        return tag_id;
    }

    public void setTag_id(long tag_id) {
        this.tag_id = tag_id;
    }
}
