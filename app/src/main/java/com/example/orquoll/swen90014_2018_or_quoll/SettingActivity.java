package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;

public class SettingActivity extends AppCompatActivity {

    private Toolbar tb_setting;
    private Button btn_back;
    private Spinner spinner;
    private Switch switch_GPS,swithc_Research,switch_Accelerator,switch_Device;
    private DAOFactory mDAOFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mDAOFactory = new DAOFactory();
        tb_setting = findViewById(R.id.tb_setting);
        btn_back = findViewById(R.id.btn_back_setting);
        switch_GPS = findViewById( R.id.switch_GPS );
        swithc_Research = findViewById( R.id.switch_Research );
        switch_Accelerator = findViewById( R.id.switch_Accelerator );
        switch_Device = findViewById( R.id.switch_Device );
        spinner = (Spinner) findViewById(R.id.spinnerNotification);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setOnCheckListener();
        setSpinner();

    }

    private void setOnCheckListener(){
        OnChecked onChecked = new OnChecked();
        switch_GPS.setOnCheckedChangeListener( onChecked );
        swithc_Research.setOnCheckedChangeListener( onChecked );
        switch_Device.setOnCheckedChangeListener( onChecked );
        switch_Accelerator.setOnCheckedChangeListener( onChecked );

    }


    private class OnChecked implements CompoundButton.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            String switchName = null;
            String switchCondition = null;
            if(b){
                switchCondition = "On";
            }else{
                switchCondition = "Off";
            }
            switch(compoundButton.getId()){
                case R.id.switch_GPS:
                    switchName = "GPS";
                    break;
                case R.id.switch_Research:
                    switchName = "Research sender";
                    break;
                case R.id.switch_Accelerator:
                    switchName = "Accelerometer";
                    break;
                case R.id.switch_Device:
                    switchName = "Device Orientation";
                    break;
            }
            Toast.makeText( SettingActivity.this,"Your "+switchName+" is "+switchCondition,Toast.LENGTH_SHORT ).show();
        }
    }

    private void setSpinner(){
        String[] frequency = new String[]{"Three","Four","Five","Ten"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,frequency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int notiNum = 0;
                switch(i){
                    case 0:
                        notiNum = 3;
                        break;
                    case 1:
                        notiNum = 4;
                        break;
                    case 2:
                        notiNum = 5;
                        break;
                    case 3:
                        notiNum = 10;
                        break;
                        default:notiNum = 10;
                }
                Toast.makeText(SettingActivity.this,"Max number of suggestions is "+notiNum,Toast.LENGTH_SHORT).show();
                mDAOFactory.getSettingDAOImp().updateSetting(notiNum);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
