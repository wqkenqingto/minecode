package com.minecode.model;

import java.util.Map;

/**
 * Created by wqkenqing on 2017/8/18.
 */
public class ProxyResponse {
    private String success;
    private Map<String, Object> data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}