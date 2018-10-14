package com.example.orquoll.swen90014_2018_or_quoll.NotifyAction;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.ActionDAO;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.ActionDAOImp;
import com.example.orquoll.swen90014_2018_or_quoll.db.DAO.DAOFactory;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Action;
import com.example.orquoll.swen90014_2018_or_quoll.entity.Notification;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


public class LocationTrigger implements TriggerStrategy {

    private Context mContext;
    private Activity mActivity;
    private Boolean mLocationPermitted = false;
    private String ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private String ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private static final String TAG = "google map";
    private HashSet<Integer> typeHashSet ;
    private ActionDAOImp actionDAOImp ;

    public LocationTrigger(Context mContext,Activity mActivity){
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.typeHashSet = new HashSet<Integer>();
        this.actionDAOImp = new DAOFactory().getActionDAOImpInstance();
    }

    //override the interface TriggerStrategy
    @Override
    public Action[] getNotification() {
        getLocationPermission();
        mGeoDataClient = Places.getGeoDataClient(mContext, null);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(mContext, null);
        Action[] actions =  getCurrenctLocation();
        Log.d("actionsNum",String.valueOf( actions.length ));
        return actions;
    }

    //get Location Permission service
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

    //get current location and it's place type
    private Action[] getCurrenctLocation (){
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

                            //if the likelyhood lower than 0.1, this place will be ignored
                            if(placeLikelihood.getLikelihood()>=0.1){
                                for(Integer i:placeLikelihood.getPlace().getPlaceTypes()){
                                    Log.i("LocationTypes",i.toString());
                                    typeHashSet.add( i );
                                }
                            }
                        }
                        likelyPlaces.release();
                    }
                });
            }else{
                Toast.makeText( mContext,"Your location service is closed, please open it to get relative actions",Toast.LENGTH_SHORT );
            }
        }catch(SecurityException e){
            Log.e( "wrong?",e.getMessage() );
        }
        return getRecommend();
    }

    private void printTypes(){
        Iterator iterator = typeHashSet.iterator();
        Log.d("hashSet",String.valueOf( typeHashSet.size()));
        while (iterator.hasNext()){
            Log.d("all types", String.valueOf(iterator.next()));
        }
    }

    private Action[] getRecommend(){

        Action[] allActions = actionDAOImp.display();
        int numOfAllActions = allActions.length;
        HashSet<Integer> recommendActionId = new HashSet<Integer>();
        Random newRandom = new Random();
        Integer[] recommends = new Integer[typeHashSet.size()];
        typeHashSet.toArray(recommends);
        for(int i=0 ; i<recommends.length ; i++){
            int type = recommends[i].intValue();
            switch(type){
                case 2://airport
                    recommendActionId.add(4);
                    recommendActionId.add(6);
                    recommendActionId.add(12);
                    recommendActionId.add(13);
                    recommendActionId.add(151);
                    recommendActionId.add(52);
                    break;
                case 3://amusement part and art gallery
                case 5:
                    recommendActionId.add(65);
                    recommendActionId.add(66);
                    recommendActionId.add(112);
                    break;
                case 12://book store and cafe
                case 15:
                    recommendActionId.add(101);
                    recommendActionId.add(112);
                    break;
                case 23://church
                    recommendActionId.add(4);
                    recommendActionId.add(6);
                    recommendActionId.add(66);
                    break;
                case 96://zoo and park
                case 69:
                    recommendActionId.add(45);
                    recommendActionId.add(46);
                    recommendActionId.add(47);
                    break;
                default:
                    recommendActionId.add((int)allActions[newRandom.nextInt(numOfAllActions)].getActionId());
            }
        }
        while(recommendActionId.size()<10){
            recommendActionId.add((int)allActions[newRandom.nextInt(numOfAllActions)].getActionId());
        }
        return transferAction( recommendActionId );
    }

    private Action[] transferAction(HashSet<Integer> actionIds){
        Action[] actions = new Action[actionIds.size()];
        Integer[] ids = new Integer[actionIds.size()];
        actionIds.toArray(ids);
        for(int i=0 ; i<actions.length ; i++){
            actions[i] = actionDAOImp.searchByActionId( ids[i].longValue() );
            Log.d("actionTitles",actions[i].getActionTittle());
        }
        return actions;
    }
}


