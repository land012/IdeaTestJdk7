package com.umbrella.demo.spring.factory;

import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.vo.User;

/**
 * Created by xudazhou on 2015/11/2.
 */
public class UserFactory3 {

    private static UserFactory3 uFactory;
    private static LeahService leahService;

    private UserFactory3() { }

    public synchronized static UserFactory3 getInstance() {
        if(uFactory==null) {
            System.out.println("========================================== UserFactory3 getInstance");
            uFactory = new UserFactory3();
        }
        return uFactory;
    }

    public void hello() {
        System.out.println("========================= Hello UserFactory begin ===============================");
        this.leahService.hello("Hector");
        System.out.println("========================= Hello UserFactory end ===============================");
    }


    public void setLeahService(LeahService leahService) {
        this.leahService = leahService;
    }
}
