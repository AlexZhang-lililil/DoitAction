package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Tag;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class TagDAOImp {

    public List<String> getParentTagWord(){
        List<String> parentTags = new ArrayList<String> ();
        List<Tag> tags = new ArrayList<Tag>();
        tags = LitePal.where("parent_id = ?",String.valueOf( 0 )).find( Tag.class);
        for(int i=0;i<tags.size();i++){
            parentTags.add(tags.get( i ).getTag_Word());
        }
        return parentTags;
    }

    public List<Tag> getParentTags(){
        return LitePal.where("parent_id = ?",String.valueOf( 0 )).find( Tag.class);
    }

    public List<Tag> getTagsByParentId(Long parent_Id){
        return LitePal.where("parent_id = ? ",parent_Id.toString()).find( Tag.class );
    }
}
