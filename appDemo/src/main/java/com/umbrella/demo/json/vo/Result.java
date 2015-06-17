package com.umbrella.demo.json.vo;

/**
 * Created by 大洲 on 15-6-17.
 */
public class Result<T> {
    private Boolean success;
    private T t;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
