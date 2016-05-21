package com.umbrella.grammar.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 大洲 on 14-12-24.
 */
public class Person {

    private static final Logger log = LoggerFactory.getLogger(Person.class);

    private String name;

    public String getName() {
        return "person";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void perform() {
        run();
    }

    public void run() {
        log.info("I am running");
    }
}
