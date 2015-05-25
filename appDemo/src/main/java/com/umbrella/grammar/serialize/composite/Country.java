package com.umbrella.grammar.serialize.composite;

/**
 * Created by 大洲 on 14-12-29.
 */
public class Country {
    private long id;
    private String name;
    private String nationalFlag;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationalFlag() {
        return nationalFlag;
    }

    public void setNationalFlag(String nationalFlag) {
        this.nationalFlag = nationalFlag;
    }
}
