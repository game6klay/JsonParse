package com.example.jay.parsejson.Models.API;

import com.example.jay.parsejson.Models.POJO.Flower;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by jay on 5/5/17.
 */

public interface FlowerApi {
    @GET("/feeds/flowers.json")
    void getFlowers(Callback<List<Flower>> flowers);
}
