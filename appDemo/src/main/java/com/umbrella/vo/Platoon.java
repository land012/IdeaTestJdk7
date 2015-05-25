package com.umbrella.vo;

import java.util.List;

/**
 * Created by 大洲 on 15-4-22.
 */
public class Platoon {
    private long id;
    private String name;
    private List<Soldier> soldiers;

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

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(List<Soldier> soldiers) {
        this.soldiers = soldiers;
    }
}
