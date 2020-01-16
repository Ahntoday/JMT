package com.example.jmt_2;

public class StoreData {
    private String storeName;
    private String address;
    private String location;
    private String menu;
    private String keyword;

    public StoreData() {}
    public StoreData(String storeName, String address, String location, String menu, String keyword) {
        this.storeName = storeName;
        this.address = address;
        this.location = location;
        this.menu = menu;
        this.keyword = keyword;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
