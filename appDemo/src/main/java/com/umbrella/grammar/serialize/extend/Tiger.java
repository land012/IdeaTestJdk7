package com.umbrella.grammar.serialize.extend;

import java.io.Serializable;

/**
 * Created by 大洲 on 14-12-31.
 */
public class Tiger extends Animal implements Serializable {
    private String color;

    public Tiger(String name, String color) {
        super(name);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
