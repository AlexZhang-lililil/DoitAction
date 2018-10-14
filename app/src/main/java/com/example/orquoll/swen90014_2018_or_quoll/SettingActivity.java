package com.example.orquoll.swen90014_2018_or_quoll;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

public class SettingActivity extends AppCompatActivity {

    private Toolbar tb_setting;
    private Button btn_back;
    private Switch switch_GPS,swithc_Research,switch_Accelerator,switch_Device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        tb_setting = findViewById(R.id.tb_setting);
        btn_back = findViewById(R.id.btn_back_setting);
        switch_GPS = findViewById( R.id.switch_GPS );
        swithc_Research = findViewById( R.id.switch_Research );
        switch_Accelerator = findViewById( R.id.switch_Accelerator );
        switch_Device = findViewById( R.id.switch_Device );


        //Spinner spinner = super.findViewById(R.id.spinnerNotification);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setOnCheckListener();
        /*
        String[] frequency = new String[]{"Three","One","Two","Five","Ten"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,frequency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        */
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
}
