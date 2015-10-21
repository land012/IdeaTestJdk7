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
            System.out.println("h1=" + req.getHeader("h1"));

            /*
             * 当客户端使用 POST提交时，如果这里用了 request.getParameter()，那么 request.getInputStream就取不到内容了
             * request.getParameter() 的底层是 request.getInputStream()
             */
            String id = req.getParameter("id");
            String userName = req.getParameter("userName");
            System.out.println("id=" + id + ",userName=" + userName);

            InputStream is = req.getInputStream();
            StringBuilder reqBody = new StringBuilder();
            byte[] buf = new byte[1024];
            int i = -1;
            while((i=is.read(buf))!=-1) {
                reqBody.append(new String(buf, 0, i, "utf-8"));
            }
            System.out.println("reqBody=" + reqBody);

//            resp.getWriter().write("Hello " + id + "," + userName);
            resp.getWriter().write("Hello World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("greet")
    public String greet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            System.out.println("h1=" + req.getHeader("h1"));

            /*
             * 当客户端使用 POST提交时，如果这里用了 request.getParameter()，那么 request.getInputStream就取不到内容了
             * request.getParameter() 的底层是 request.getInputStream()
             */
            String id = req.getParameter("id");
            String userName = req.getParameter("userName");
            System.out.println("id=" + id + ",userName=" + userName);

//            InputStream is = req.getInputStream();
//            StringBuilder reqBody = new StringBuilder();
//            byte[] buf = new byte[1024];
//            int i = -1;
//            while((i=is.read(buf))!=-1) {
//                reqBody.append(new String(buf, 0, i, "utf-8"));
//            }
//            System.out.println("reqBody=" + reqBody);

//            resp.getWriter().write("Hello " + id + "," + userName);
            resp.getWriter().write("Greet World!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
