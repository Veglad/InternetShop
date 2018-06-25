package com.example.vlad.internetshop.Presenters;

import com.example.vlad.internetshop.Data.ShopData;
import com.example.vlad.internetshop.Enteties.DeviceCard;
import com.example.vlad.internetshop.Views.IMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPresenter {
    private ShopData data;
    private IMainActivity view;
    private AtomicInteger counter;

    private List<DeviceCard> deviceCardList = new ArrayList<>();
    private List<DeviceCard> promotionalDeviceCardList = new ArrayList<>();

    public MainPresenter(IMainActivity view){
        this.view = view;
        data = new ShopData();
        counter = new AtomicInteger();
    }

    public void swipeRefresh(){
        counter.set(2);
        //new TaskLoadMainDevices(counter, this, data).execute();
        //new TaskLoadPromotionalDevices(counter, this, data).execute();
    }

    public void initRecyclerViews(){
        view.recyclerViewsDataSetChange(deviceCardList, promotionalDeviceCardList);
    }

    public void stopSwipeRefreshing(){
        view.stopSwipeRefresh();
    }

    public void setDeviceCardList(List<DeviceCard> list){
        deviceCardList = list;
    }

    public void setPromotionalDeviceCardList(List<DeviceCard> list){
        promotionalDeviceCardList = list;
    }

    public ShopData getShopData(){
        return data;
    }
}
