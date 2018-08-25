package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.SearchRecord;

import org.litepal.LitePal;

import java.util.List;

public class SearchRecordDAOImp implements SearchRecordDAO {

    public SearchRecordDAOImp(){

    }
    @Override
    public void insertNewRecord(String name) {
        SearchRecord newSearchRecord = new SearchRecord( name );
        if(!isSaved( name ))
        newSearchRecord.save();
    }

    @Override
    public SearchRecord[] display() {
        List<SearchRecord> records = LitePal.findAll( SearchRecord.class );
        SearchRecord[] records1 = new SearchRecord[records.size()];
        records.toArray(records1);
        return records1;
    }

    /* deprecated */
    public void removeRecord(String name) {
        LitePal.deleteAll( SearchRecord.class,"name = ?",name );
    }

    @Override
    public void removeAll(){
        LitePal.deleteAll( SearchRecord.class );
    }
    private boolean isSaved(String name){
        List<SearchRecord> records = LitePal.where("name = ?",name).find(SearchRecord.class);
        if(records.size()!=0){
            return true;
        }
        else return false;
    }
}
