package com.example.jay.parsejson.Controllers;

import com.example.jay.parsejson.Models.API.RestApiManager;
import com.example.jay.parsejson.Models.POJO.Flower;

import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jay on 5/5/17.
 */

public class Controller {

    private static final String TAG = Controller.class.getSimpleName();
    private FlowerCallbackListener mListener;
    private RestApiManager mApiManager;

    public Controller(FlowerCallbackListener listener) {
        mListener = listener;
        mApiManager = new RestApiManager();
    }

    public void startFetching() {

        mApiManager.getFlowerApi().getFlowers(new Callback<List<Flower>>() {
            @Override
            public void success(List<Flower> s, Response response) {
                mListener.onFetchProgress(s);
                /*try {
                    JSONArray array = new JSONArray(s);

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Flower flower = new Flower.Builder()
                                .setCategory(object.getString("category"))
                                .setPrice(object.getDouble("price"))
                                .setInstructions(object.getString("instructions"))
                                .setPhoto(object.getString("photo"))
                                .setName(object.getString("name"))
                                .setProductId(object.getInt("productId"))
                                .build();

                        mListener.onFetchProgress(flower);

                    }

                } catch (JSONException e) {
                    mListener.onFetchFailed();
                }*/
                mListener.onFetchComplete();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Error :: " + error.getMessage());
                mListener.onFetchComplete();
            }
        });
    }


    public interface FlowerCallbackListener {
        void onFetchStart();
        void onFetchProgress(Flower flower);
        void onFetchProgress(List<Flower> flowerList);
        void onFetchComplete();
        void onFetchFailed();
        void onItemClick(int p);
    }
 }
