package com.example.vlad.internetshop.Data;

import com.example.vlad.internetshop.Enteties.DeviceCard;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Query;

public interface ShopAPI {
    //@FormUrlEncoded
    @GET("/api/DeviceData")
    Call<List<DeviceCard>> getAllDevices();

    //@FormUrlEncoded
    @GET("/api/DeviceData")//TODO: Correct here. New API request
    Call<List<DeviceCard>> getAllPromotionalDevices();
}
