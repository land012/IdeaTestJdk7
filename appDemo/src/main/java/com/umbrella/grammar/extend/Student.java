package com.umbrella.grammar.extend;

/**
 * Created by 大洲 on 14-12-24.
 */
public class Student extends Person {
    private String name;

    public String getName() {
        return "student";
    }

    public void setName(String name) {
        this.name = name;
    }
}
