package com.example.orquoll.swen90014_2018_or_quoll.NotifyAction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.orquoll.swen90014_2018_or_quoll.entity.Notification;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;


public class LocationTrigger implements TriggerStrategy {

    private Context mContext;
    private Activity mActivity;
    private Boolean mLocationPermitted = false;
    private String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private static final String TAG = "google map";

    public LocationTrigger(Context mContext,Activity mActivity){
        this.mContext = mContext;
        this.mActivity = mActivity;
    }

    @Override
    public Notification getNotification() {
        getLocationPermission();
        mGeoDataClient = Places.getGeoDataClient(mContext, null);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(mContext, null);
        getCurrenctLocation();
        return null;
    }

    private void getLocationPermission(){
        String[] permissions = { ACCESS_COARSE_LOCATION,
                ACCESS_FINE_LOCATION};
        if(ContextCompat.checkSelfPermission( mContext,ACCESS_COARSE_LOCATION ) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission( mContext,ACCESS_FINE_LOCATION ) == PackageManager.PERMISSION_GRANTED){
                mLocationPermitted = true;
            }else{
                ActivityCompat.requestPermissions( mActivity,permissions,123 );
            }
        }else{
            ActivityCompat.requestPermissions( mActivity,permissions,123 );
        }
        Log.d("permission",String.valueOf( mLocationPermitted ));
    }

    private void getCurrenctLocation (){
        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient( mActivity );
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
