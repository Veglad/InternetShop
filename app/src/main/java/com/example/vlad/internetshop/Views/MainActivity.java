package com.example.vlad.internetshop.Views;

import com.example.vlad.internetshop.Data.TaskLoadMainDevices;
import com.example.vlad.internetshop.Enteties.DeviceCard;
import com.example.vlad.internetshop.Presenters.MainPresenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.vlad.internetshop.Data.IShopData;
import com.example.vlad.internetshop.Data.ShopData;
import com.example.vlad.internetshop.R;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, IMainActivity,
        LoaderManager.LoaderCallbacks<List<DeviceCard>>{

    private MainPresenter presenter;
    private RecyclerViewMainAdapter deviceCardAdapter;

    List<DeviceCard> mainDevicesList = new ArrayList<>();
    List<DeviceCard> promotionalDeviceCardList = new ArrayList<>();

    private static AtomicInteger loaderToLoad = new AtomicInteger();

    private RecyclerView recyclerViewDevicesCards, recyclerViewPromotional;
    private SwipeRefreshLayout swipeRefreshLayoutMain;

    //Loaders ids
    private final int LOADER_ALL_DEVICES_ID = 1;
    private final int LOADER_ALL_PROMOTIONAL_DEVICES_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        swipeRefreshLayoutMain = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshMain);
        swipeRefreshLayoutMain.setOnRefreshListener(this);
        initRecyclerViews();

        //load all devices and promotional devices
        loadAllDeives(false, true);
        loadAllDeives(false, false);
    }

    public void initRecyclerViews() {
        //Recyclerview Main
        mainDevicesList.add(new DeviceCard());
        promotionalDeviceCardList.add(new DeviceCard());
        recyclerViewDevicesCards = (RecyclerView)findViewById(R.id.recyclerViewMain);
        deviceCardAdapter = new RecyclerViewMainAdapter(mainDevicesList, promotionalDeviceCardList, getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerViewDevicesCards.setLayoutManager(layoutManager);
        recyclerViewDevicesCards.setAdapter(deviceCardAdapter);

        //Recyclerview with promotional devices
        //recyclerViewPromotional = (RecyclerView)findViewById(R.id.recyclerViewPromotional);
        //promotionalDeviceCardAdapter = new RecyclerViewPromAdapter(promotionalDeviceCardList, getApplicationContext());
        //LinearLayoutManager horizontalManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        //recyclerViewPromotional.setLayoutManager(horizontalManager);
        //recyclerViewPromotional.setAdapter(promotionalDeviceCardAdapter);
    }

    public void recyclerViewsDataSetChange(List<DeviceCard> deviceCardList, List<DeviceCard> promotionalDeviceCardList){
        mainDevicesList.clear();
        mainDevicesList.addAll(deviceCardList);
        //update data to the list
        deviceCardAdapter.notifyDataSetChanged();

        this.promotionalDeviceCardList.clear();
        this.promotionalDeviceCardList.addAll(promotionalDeviceCardList);
        //Update data to the list
        deviceCardAdapter.getPromAdapter().notifyDataSetChanged();
    }

    @Override
    public void stopSwipeRefresh() {
        if(swipeRefreshLayoutMain.isRefreshing())
            swipeRefreshLayoutMain.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        loadAllDeives(true, false);
        loadAllDeives(true, true);
    }

    @NonNull
    @Override
    public Loader<List<DeviceCard>> onCreateLoader(int id, @Nullable Bundle args) {
        if(id == LOADER_ALL_DEVICES_ID)
            return new TaskLoadMainDevices(MainActivity.this, presenter, presenter.getShopData(), false);
        else
            return new TaskLoadMainDevices(MainActivity.this, presenter, presenter.getShopData(), true);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<DeviceCard>> loader, List<DeviceCard> data) {
        switch (loader.getId()){
            case LOADER_ALL_DEVICES_ID:
                mainDevicesList.clear();
                mainDevicesList.addAll(data);
                //update data to the list
                deviceCardAdapter.notifyDataSetChanged();
                break;
            case LOADER_ALL_PROMOTIONAL_DEVICES_ID:
                this.promotionalDeviceCardList.clear();
                this.promotionalDeviceCardList.addAll(data);
                //Update data to the list
                deviceCardAdapter.getPromAdapter().notifyDataSetChanged();
                break;
        }

        Log.d("Tagg", "onFinished, beforedec::" + loaderToLoad);
            if(loaderToLoad.decrementAndGet() == 0) {
                Log.d("Tagg", "stop refreshing");
                swipeRefreshLayoutMain.setRefreshing(false);
                //recyclerViewsDataSetChange(mainDevicesList, promotionalDeviceCardList);
            }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<DeviceCard>> loader) {

    }

    private void loadAllDeives(boolean restart, boolean isPromotional){
        swipeRefreshLayoutMain.setRefreshing(true);
        loaderToLoad.incrementAndGet();
        Log.d("Tagg", "loadAll, afterinc:" + loaderToLoad);

        if(restart){
            if(isPromotional)
                getSupportLoaderManager().restartLoader(LOADER_ALL_PROMOTIONAL_DEVICES_ID, Bundle.EMPTY, this);
            else
                getSupportLoaderManager().restartLoader(LOADER_ALL_DEVICES_ID, Bundle.EMPTY, this);
        }
        else{
            if(isPromotional)
                getSupportLoaderManager().initLoader(LOADER_ALL_PROMOTIONAL_DEVICES_ID, Bundle.EMPTY, this);
            else
                getSupportLoaderManager().initLoader(LOADER_ALL_DEVICES_ID, Bundle.EMPTY, this);
        }
    }
}
