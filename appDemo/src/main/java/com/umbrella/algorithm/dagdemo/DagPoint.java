package com.umbrella.algorithm.dagdemo;

import java.util.List;

/**
 * create by xudazhou 2018/3/16
 */
public class DagPoint {
    private String name;
    private int ins; // 入度
    private List<String> nexts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIns() {
        return ins;
    }

    public void setIns(int ins) {
        this.ins = ins;
    }

    public List<String> getNexts() {
        return nexts;
    }

    public void setNexts(List<String> nexts) {
        this.nexts = nexts;
    }
}
