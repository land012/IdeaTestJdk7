package com.umbrella.demo.java.lang.enumtype;

/**
 * Created by 大洲 on 14-11-19.
 */
public enum StatusEnum {

    SUCCESS(0, "success"),
    FAIL(1, "fail"),
    UNKNOWN(2, "unknown");

    private int key;
    private String value;

    private StatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}
