package com.example.sjha3.networkapplication;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rashmi on 7/31/17.
 */

public class NetworkClientRetro {

    public static final String BASE_URL = "http://toscanyacademy.com/";
    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
