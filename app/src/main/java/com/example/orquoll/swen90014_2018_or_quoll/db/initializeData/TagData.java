package com.example.orquoll.swen90014_2018_or_quoll.db.initializeData;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Tag;

public class TagData {
    private DAOFactory newDAOFactory;

    public TagData(){
        this.newDAOFactory = new DAOFactory();
    }

    private void saveTag(String tag_Word){
        Tag tag = new Tag(tag_Word);
        tag.save();
    }

    public void saveAll(){
        saveTag("Apathy");
        saveTag("Depression");
        saveTag("Panic");
        saveTag("Worrying");
        saveTag("Social life");
        saveTag("Social anxiety");
        saveTag("Managing work and study");
        saveTag("Finding a job");
        saveTag("Problem solving");
        saveTag("Self-care");
        saveTag("Feeling positive");
        saveTag("Learning more about psychosis");
        saveTag("Stories like yours");
        saveTag("Strengths");
        saveTag("Changing a habit");

    }
}
