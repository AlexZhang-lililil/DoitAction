package com.example.orquoll.swen90014_2018_or_quoll;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.orquoll.swen90014_2018_or_quoll.Adapters.SearchAdapter;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Tag;
import com.example.orquoll.swen90014_2018_or_quoll.fragment.SearchHistoryFragment;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private DAOFactory newDAOFactory;
    private Button btn_back;
    private SearchView search;
    private RecyclerView rv_search;
    private SearchAdapter newSearchAdapter;
    private ArrayList<String> searchTagList;
    private SearchHistoryFragment historyFragment;
    private Spinner sp_ParentTag;
    private Spinner sp_Tag;
    private long parentTagId;
    private long tagId;
    private ArrayAdapter adapterForTag;
    private List<String> tagWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.parentTagId = 1;
        newDAOFactory = new DAOFactory();
        tagWords = getTags();
        adapterForTag = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,tagWords){};
        searchTagList = new ArrayList<String>();
        historyFragment = new SearchHistoryFragment();
        btn_back = (Button) findViewById(R.id.btn_back_search);
        search = (SearchView) findViewById(R.id.srh) ;
        rv_search = (RecyclerView) findViewById(R.id.rv_search);
        sp_ParentTag = (Spinner) findViewById( R.id.spinner_parent_tag );
        sp_Tag = (Spinner) findViewById( R.id.spinner_tag );


        initialize();

        //getSupportFragmentManager().beginTransaction().add( R.id.fl_container,historyFragment ).commit();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                newDAOFactory.getSearchRecorDAOImp().insertNewRecord( s );
                removeFragment( historyFragment );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    //display the search history when there are some records
                    if(newDAOFactory.getSearchRecorDAOImp().display().length != 0)
                        replaceFragment( historyFragment );
                    else
                        removeFragment( historyFragment );

                    //dynamically display search results

                    newSearchAdapter.setSearchActions( newDAOFactory.getActionDAOImpInstance().fuzzySearch( s ) );
                    newSearchAdapter.notifyDataSetChanged();

                }else{

                    if(newDAOFactory.getSearchRecorDAOImp().display().length != 0)
                        replaceFragment( historyFragment );
                    else
                        removeFragment( historyFragment );

                    newSearchAdapter.setSearchActions( newDAOFactory.getActionDAOImpInstance().display() );
                    newSearchAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    class Decoration extends RecyclerView.ItemDecoration{
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            super.getItemOffsets(outRect,view,parent,state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.divider));
        }
    }

    public void initialize(){

        this.newDAOFactory = new DAOFactory();
        this.newSearchAdapter = new SearchAdapter(this,newDAOFactory.getActionDAOImpInstance().display());

        //the icon of search view
        search.setIconifiedByDefault(false);
        search.setSubmitButtonEnabled(true);

        //search recycler view initialize
        rv_search.setLayoutManager(new LinearLayoutManager(this));
        rv_search.setAdapter(newSearchAdapter);
        rv_search.addItemDecoration(new Decoration());

        //Spinner adapter setting
        sp_ParentTag.setAdapter( new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,getParentTags()) {
        } );
        sp_Tag.setAdapter( this.adapterForTag );
        filter();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace( R.id.fl_container, fragment );
        transaction.commit();
    }

    private   void removeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove( fragment );
        transaction.commit();
    }

    private List<String> getParentTags(){
        return newDAOFactory.getTagDAOImp().getParentTagWord();
    }

    private List<String> getTags(){
        List<String> tag_Word = new ArrayList<String>();
        List<Tag> tags = newDAOFactory.getTagDAOImp().getTagsByParentId( this.parentTagId );
        for(int i=0;i<tags.size();i++){
            tag_Word.add( tags.get(i).getTag_Word() );
        }
        return tag_Word;
    }

    private void filter(){
        sp_ParentTag.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<Tag> tags = new ArrayList<Tag>();
                tags = newDAOFactory.getTagDAOImp().getParentTags();
                parentTagId = tags.get( i ).getTag_id();
                tagWords = getTags();
                sp_Tag.setAdapter( new ArrayAdapter( view.getContext(),R.layout.support_simple_spinner_dropdown_item,tagWords ) );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_Tag.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tagId = newDAOFactory.getTagDAOImp().getTagsByParentId( parentTagId ).get( i ).getTag_id();
                newSearchAdapter.setSearchActions( newDAOFactory.getActionDAOImpInstance().showActionByIds( newDAOFactory.getT_ADAOImp().getActionIdByTagId( tagId ) ));
                newSearchAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        } );
    }
}
