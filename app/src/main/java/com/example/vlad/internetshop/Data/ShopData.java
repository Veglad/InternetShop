package com.example.vlad.internetshop.Data;

import android.util.Log;

import com.example.vlad.internetshop.Enteties.DeviceCard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopData implements IShopData{

    private final String BASE_URL = "http://webshopnetcore220180619022619.azurewebsites.net";
    private Gson gson = new GsonBuilder().create();
    private Retrofit retrofit = new Retrofit.Builder().
            addConverterFactory(GsonConverterFactory.create(gson)).
            baseUrl(BASE_URL).
            build();

    private ShopAPI shopAPI = retrofit.create(ShopAPI.class);

    /**
     * Get all devicesCards
     * @return List of the devicesCards from the server
     */
    @Override
    public List<DeviceCard> getAllDevices(){
        List<DeviceCard> list = null;

        Call<List<DeviceCard>> call = shopAPI.getAllDevices();
        try{
            Response<List<DeviceCard>> response = call.execute();
            list = response.body();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Get all devicesCards
     * @return List of the promotional devices cards from the server
     */
    @Override
    public List<DeviceCard> getAllPromotionalDevices(){
        List<DeviceCard> list = null;

        Call<List<DeviceCard>> call = shopAPI.getAllPromotionalDevices();
        try{
            list = call.execute().body();
        }
        catch (IOException e){
            e.printStackTrace();
            list.clear();
            list.add(new DeviceCard());
        }

        return list;
    }

}
