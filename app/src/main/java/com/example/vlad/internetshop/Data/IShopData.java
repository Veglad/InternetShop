package com.example.vlad.internetshop.Data;

import com.example.vlad.internetshop.Enteties.DeviceCard;

import java.util.List;

public interface IShopData {
    List<DeviceCard> getAllDevices();
    List<DeviceCard> getAllPromotionalDevices();
}
