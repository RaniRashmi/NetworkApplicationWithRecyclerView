package com.example.sjha3.networkapplication;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rashmi on 7/31/17.
 */

public interface NetworkInterfaceRetro {

    @GET("blog/mp.php")
    Call<ArrayList<ListObject>> getSongList();
}
