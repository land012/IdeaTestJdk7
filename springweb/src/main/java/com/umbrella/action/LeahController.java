package com.umbrella.action;

import com.umbrella.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by xudazhou on 2017/1/20.
 */
@Controller
@RequestMapping("leah")
public class LeahController {

    @ResponseBody
    @RequestMapping("getUser")
    public User getUser(Long id) {
        User u1 = new User();
        u1.setId(id);
        u1.setUserName("Leah");
        u1.setBirthDay(new Date());
        return u1;
    }
}
