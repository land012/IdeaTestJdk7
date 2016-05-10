package com.umbrella.demo.java.util;

import com.umbrella.vo.Soldier;
import com.umbrella.vo.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by xudazhou on 2016/5/9.
 */
public class CollectionsDemo {
    @Test
    public void test1() {
        Soldier s1 = new Soldier();
        s1.setId(2);
        Soldier s2 = new Soldier();
        s2.setId(1);
        List<Soldier> soldierList = new ArrayList<>();
        soldierList.add(s1);
        soldierList.add(s2);
        Collections.sort(soldierList, new Comparator<Soldier>() {
            @Override
            public int compare(Soldier o1, Soldier o2) {
                if(o1.getId()<o2.getId()) return -1;
                if(o1.getId()==o2.getId()) return 0;
                return 1;
            }
        });
        System.out.println(soldierList);
    }
}
