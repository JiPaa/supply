package com.jconvey.supplier.bean;

public class Black {
    private String username;

    private String market;

    private String manage;

    private String name;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market == null ? null : market.trim();
    }

    public String getManage() {
        return manage;
    }

    public void setManage(String manage) {
        this.manage = manage == null ? null : manage.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}