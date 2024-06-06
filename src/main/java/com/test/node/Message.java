package com.test.node;

import com.google.gson.internal.LinkedTreeMap;

public class Message {
    private String action;
    private LinkedTreeMap<String, Object> data;

    public void setData(LinkedTreeMap<String, Object> data) {
        this.data = data;
    }

    public void setAction(String action) {
        this.action  = action;
    }

    public String getAction() {
        return this.action;
    }

    public LinkedTreeMap<String, Object> getData() {
        return this.data;
    }
}
