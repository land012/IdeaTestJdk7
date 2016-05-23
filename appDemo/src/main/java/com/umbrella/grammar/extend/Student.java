package com.umbrella.grammar.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 大洲 on 14-12-24.
 * 父类和子类拥有相同的成员变量
 */
public class Student extends Person {

    private static final Logger log = LoggerFactory.getLogger(Student.class);

    private String name;

    public Student() {
        log.info("I am Student");
    }

    public String getName() {
        return "student";
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        log.info("I am running");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("I am Student finalize()");
    }
}
