package com.umbrella.demo.spring.factory;

import com.umbrella.demo.spring.service.LeahService;
import com.umbrella.vo.User;

/**
 * Created by xudazhou on 2015/11/2.
 */
public class UserFactory {

    private static UserFactory uFactory;
    private static LeahService leahService;

    private UserFactory() { }

    public static User create(Long id) {
        User u = new User();
        u.setId(id);
        return u;
    }

    public static User make(Long id, String... strings) {
        User u = new User();
        u.setId(id);
        return u;
    }

    public synchronized static UserFactory getInstance() {
        if(uFactory==null) {
            System.out.println("===================== UserFactory getInstance =====================");
            uFactory = new UserFactory();
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
