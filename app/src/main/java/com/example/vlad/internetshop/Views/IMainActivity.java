package com.example.vlad.internetshop.Views;

import com.example.vlad.internetshop.Enteties.DeviceCard;

import java.util.List;

public interface IMainActivity {
    void recyclerViewsDataSetChange(List<DeviceCard> deviceCards, List<DeviceCard> promotionalDeviceCards);
    void stopSwipeRefresh();

}
