package com.example.sjha3.networkapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FrontActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private RecyclerAdapter mAdapter;
    private ArrayList<ListObject> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        mRecyclerView = (RecyclerView) (findViewById(R.id.recycler_view));
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        if(mArrayList==null) {
            mArrayList = new ArrayList<>();
            mAdapter = new RecyclerAdapter(mArrayList, this);
        }
        mRecyclerView.setAdapter(mAdapter);
        // To create divider line between list view items
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
//                mLayoutManager.getOrientation());
//        mRecyclerView.addItemDecoration(dividerItemDecoration);
        // if network is available, else fetch from the db
        // write a function for checking the network
      //  fetchDataUsingRetrofit();
     //   fetchDataUsingVolley();
        fetchDataUsingIon();
    }

    /**
     * get the data store in the mArrayList and then call mAdapter.setData(songList); and mAdapter.notifyDataSetChanged();
     */
    private void fetchDataUsingRetrofit(){
        NetworkInterfaceRetro networkInterfaceRetro = NetworkClientRetro.getClient().create(NetworkInterfaceRetro.class);
        retrofit2.Call<ArrayList<ListObject>> call = networkInterfaceRetro.getSongList();
        call.enqueue(new Callback<ArrayList<ListObject>>() {
            @Override
            public void onResponse(Call<ArrayList<ListObject>> call, Response<ArrayList<ListObject>> response) {
                mArrayList = response.body();
                mAdapter.setData(mArrayList);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<ListObject>> call, Throwable t) {

            }
        });
    }

    /**
     * get the data store in the mArrayList and then call mAdapter.setData(songList); and mAdapter.notifyDataSetChanged();
     * Need to add AppController in manifest else will get null pointer exception
     */
    private void fetchDataUsingVolley(){
        String url ="http://toscanyacademy.com/blog/mp.php";
       StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
           @Override
           public void onResponse(String response) {
               parseJSON(response);

           }
       }, new com.android.volley.Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               // log something
           }
       });
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    /**
     * you can convert directly using GSON but the code is long and this method seems easier
     * Divided in 2 methods for volley and ION
     * @param response
     */
    private void parseJSON(String response){
        try {
            JSONArray jsonArray = new JSONArray(response);
            ArrayList<ListObject> itemArrayList = new ArrayList<>();
            for (int i =0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ListObject itemObject = new ListObject();

                itemObject.setSong_name(jsonObject.getString("song_name"));
                itemObject.setSong_artist(jsonObject.getString("artist_name"));
                itemObject.setSong_year(jsonObject.getString("song_id"));

                itemArrayList.add(itemObject);
                mAdapter = new RecyclerAdapter( itemArrayList, this);
                mRecyclerView.setAdapter(mAdapter);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * get the data store in the mArrayList and then call mAdapter.setData(songList); and mAdapter.notifyDataSetChanged();
     */
    private void fetchDataUsingIon(){
        String url ="http://toscanyacademy.com/blog/mp.php";
        Ion.with(this).load(url).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                if(result!=null) {
                    parseJSON(result);
                }
            }
        });

    }


}
