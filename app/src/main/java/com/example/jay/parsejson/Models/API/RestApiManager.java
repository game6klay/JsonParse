package com.example.jay.parsejson.Models.API;

import com.example.jay.parsejson.Models.Utilities.Constants;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by jay on 5/5/17.
 */

public class RestApiManager {

    private FlowerApi mFlowerApi;

    public FlowerApi getFlowerApi() {

        if(mFlowerApi == null) {
            Gson gson = new GsonBuilder()
                    .create();
            mFlowerApi = new RestAdapter.Builder()
                    .setEndpoint(Constants.BASE_URL)
                    .setConverter(new GsonConverter(gson))
                    .build()
                    .create(FlowerApi.class);
        }
        return mFlowerApi;
    }
}
