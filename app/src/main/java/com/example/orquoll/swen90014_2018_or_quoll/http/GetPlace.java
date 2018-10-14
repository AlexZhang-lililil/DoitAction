package com.example.orquoll.swen90014_2018_or_quoll.http;

import android.annotation.SuppressLint;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetPlace {

    private String response;
    private static final int UPDATE =1;

    public GetPlace(){
        this.response = "haven't got";
    }

    public void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request newRequest = new Request.Builder()
                                            .url("http://baidu.com").build();
                    Response response = client.newCall(newRequest).execute();
                    String responseData = response.body().string();

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void setResponse(String response){
        this.response = response;
    }

    public String getResponse(){
        return this.response;
    }
}
