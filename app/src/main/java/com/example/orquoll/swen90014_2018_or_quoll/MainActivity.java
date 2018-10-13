package com.example.orquoll.swen90014_2018_or_quoll;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationProvider;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.ActionDAO;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.ActionData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.StrengthData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.Strength_ActionData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.TagData;
import com.example.orquoll.swen90014_2018_or_quoll.db.initializeData.Tag_ActionData;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Strength_Action;
import com.google.android.gms.gcm.Task;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;

import org.litepal.tablemanager.Connector;


public class MainActivity extends AppCompatActivity {
    private Button btn_Login;
    private Boolean mLocationPermitted = false;
    private String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private static final String TAG = "google map";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Login = (Button) findViewById(R.id.btn_login);

        Connector.getDatabase();

        DAOFactory newDAOFactory = new DAOFactory();
        if(newDAOFactory.getStrengthDAOImp().display().length==0) {

            StrengthData strengthData = new StrengthData();
            Strength_ActionData strength_actionData = new Strength_ActionData();
            Tag_ActionData tag_actionData = new Tag_ActionData();
            TagData tagData = new TagData();
            ActionData actionData = new ActionData();
            strengthData.saveAll();
            strength_actionData.saveAll();
            tag_actionData.saveAll();
            tagData.saveAll();
            actionData.saveAll();

        }

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (MainActivity.this,MenuActivity.class);
                startActivity( i );
            }
        });



    }

    private void notifyAction(){

        NotificationManager notificationManager = (NotificationManager) getSystemService
                (NOTIFICATION_SERVICE);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);


        mBuilder.setContentTitle("Title")
                .setContentText("content")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setWhen(System.currentTimeMillis())
                .setTicker("I'm content")
                .setDefaults(Notification.DEFAULT_SOUND);
        notificationManager.notify(10, mBuilder.build());
    }

    private void getLocationPermission(){
        String[] permissions = { ACCESS_COARSE_LOCATION,
        ACCESS_FINE_LOCATION};
        if(ContextCompat.checkSelfPermission( this,ACCESS_COARSE_LOCATION ) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission( this,ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED){
                mLocationPermitted = true;
            }else{
                ActivityCompat.requestPermissions( this,permissions,123 );
            }
        }else{
            ActivityCompat.requestPermissions( this,permissions,123 );
        }
        Log.d("permission",String.valueOf( mLocationPermitted ));
    }

    private void getCurrenctLocation (){
        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient( this );
        try{
            if(mLocationPermitted){
                com.google.android.gms.tasks.Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
                placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<PlaceLikelihoodBufferResponse> task) {
                        PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                        for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                            Log.i("LocationPlaces", String.format("Place '%s' has likelihood: %g",
                                    placeLikelihood.getPlace().getName(),
                                    placeLikelihood.getLikelihood()));
                            for(Integer i:placeLikelihood.getPlace().getPlaceTypes()){
                                Log.i("LocationTypes",i.toString());
                            }
                        }
                        likelyPlaces.release();

                    }
                });
            }
        }catch(SecurityException e){
            Log.e( "wrong?",e.getMessage() );
        }
    }
}
