package com.example.vlad.internetshop.Data;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.vlad.internetshop.Enteties.DeviceCard;
import com.example.vlad.internetshop.Presenters.MainPresenter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskLoadMainDevices extends AsyncTaskLoader<List<DeviceCard>> {
    private MainPresenter presenter;
    private ShopData data;
    private boolean getPromotional;

    public TaskLoadMainDevices(Context context, MainPresenter presenter, ShopData data, boolean getPromotional){
        super(context);
        this.presenter = presenter;
        this.data = data;
        this.getPromotional = getPromotional;
    }

    @Override
    protected void onStartLoading() {
        Log.d("Tagg", "StartLoading");
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public List<DeviceCard> loadInBackground() {
        if(getPromotional)
            return data.getAllPromotionalDevices();
        else
            return data.getAllDevices();
    }
}
