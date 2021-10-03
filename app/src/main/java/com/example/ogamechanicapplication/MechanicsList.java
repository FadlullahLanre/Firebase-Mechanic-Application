package com.example.ogamechanicapplication;

import java.io.Serializable;

public class MechanicsList implements Serializable {

    private String id;
    private String companyName;
    private String serviceType;
    private String location;
    private String price;
    private String phoneNumber;
    private String imageUrl;

    public MechanicsList(){}

    public MechanicsList(String companyName, String serviceType, String location, String price, String phoneNumber, String imageUrl) {
        this.setId(id);
        this.setCompanyName(companyName);
        this.setServiceType(serviceType);
        this.setLocation(location);
        this.setPrice(price);
        this.setPhoneNumber(phoneNumber);
        this.setImageUrl(imageUrl);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
