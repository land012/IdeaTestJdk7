package com.umbrella.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 大洲 on 14-11-22.
 * Spring MVC
 */
@Controller
public class HelloWorldAction {
    private static Logger log = Logger.getLogger(HelloWorldAction.class);

    public HelloWorldAction() {
        log.info("this is HelloWorld()");
    }

    @RequestMapping("hello")
    public String hello(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println("k1=" + req.getHeader("k1"));

            InputStream is = req.getInputStream();
            StringBuilder res = new StringBuilder();
            byte[] buf = new byte[1024];
            int i = -1;
            while((i=is.read(buf))!=-1) {
                res.append(new String(buf, 0, i, "utf-8"));
            }
            System.out.println(res);
            resp.getWriter().write("Hello World");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}