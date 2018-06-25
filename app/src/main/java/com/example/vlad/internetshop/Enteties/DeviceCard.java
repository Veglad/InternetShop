package com.example.vlad.internetshop.Enteties;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceCard {

    @SerializedName("deviceId")
    @Expose
    private Integer deviceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("price")
    @Expose
    private Double price;
    @SerializedName("imageThumbnailUrl")
    @Expose
    private String imageThumbnailUrl;

    public DeviceCard(){
        deviceId = 0;
        name = "Noname";
        shortDescription = "Unknown";
        price = 677d;
        imageThumbnailUrl = "https://goo.gl/images/ZQzi7q";
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageThumbnailUrl() {
        return imageThumbnailUrl;
    }

    public void setImageThumbnailUrl(String imageThumbnailUrl) {
        this.imageThumbnailUrl = imageThumbnailUrl;
    }

}
