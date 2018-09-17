package com.example.orquoll.swen90014_2018_or_quoll.db.initializeData;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Tag;

public class TagData {
    private DAOFactory newDAOFactory;

    public TagData(){
        this.newDAOFactory = new DAOFactory();
    }

    private void saveTag(String tag_Word,long parent_id,long tag_id){
        Tag tag = new Tag(tag_Word,parent_id,tag_id);
        tag.save();
    }

    public void saveAll(){
        saveTag("Coping with bad days",0,1);
        saveTag("Anxiety and stress",0,2);
        saveTag("Social life",0,3);
        saveTag("Work and study",0,4);
        saveTag("Gaining confidence",0,5);
        saveTag("Getting the most from life",0,6);
        saveTag("Make sense of psychosis",0,8);
        saveTag("Changing a habit",0,9);
        saveTag("Apathy",1,10);
        saveTag("Depression",1,11);
        saveTag("Panic",2,12);
        saveTag("Worrying",2,13);
        saveTag("Social life",3,14);
        saveTag("Social anxiety",3,15);
        saveTag("Managing work and study",4,16);
        saveTag("Finding a job",4,17);
        saveTag("Problem solving",5,18);
        saveTag("Self-care",5,19);
        saveTag("Feeling positive",6,20);
        saveTag("Learning more about psychosis",8,21);
        saveTag("Stories like yours",8,22);
        saveTag("Strengths",6,23);
        saveTag("Changing a habit",9,24);
    }
}
