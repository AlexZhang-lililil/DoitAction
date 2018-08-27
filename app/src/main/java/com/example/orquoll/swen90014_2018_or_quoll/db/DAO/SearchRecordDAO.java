package com.example.orquoll.swen90014_2018_or_quoll.db.DAO;

import com.example.orquoll.swen90014_2018_or_quoll.entity.SearchRecord;

public interface SearchRecordDAO {
    public void insertNewRecord (String name);
    public SearchRecord[] display();
    public void removeAll ();


}
