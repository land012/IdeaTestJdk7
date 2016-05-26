package com.umbrella.action;

import com.umbrella.vo.User;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * Created by 大洲 on 14-11-22.
 * Spring MVC
 */
@Controller
public class HelloWorldAction extends BaseController {
    private static Logger log = Logger.getLogger(HelloWorldAction.class);

    public HelloWorldAction() {
        log.info("this is HelloWorld()");
    }

    /**
     * request 获取请求参数的方式
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("hello")
    public String hello(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("utf-8");
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
            log.info("reqBody=" + reqBody);

//            resp.getWriter().write("Hello " + id + "," + userName);
            resp.getWriter().write("Hello World!");
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 获取客户端ip
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("greet")
    public String greet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            /**
             * 客户端IP
             */
            String clientIP = "";
            clientIP = req.getHeader("x-forwarded-for");
            if(StringUtils.isEmpty(clientIP) || "unknown".equalsIgnoreCase(clientIP)) {
                clientIP = req.getHeader("Proxy-Client-IP");
            }
            if(StringUtils.isEmpty(clientIP) || "unknown".equalsIgnoreCase(clientIP)) {
                clientIP = req.getHeader("WL-Proxy-Client-IP");
            }
            if(StringUtils.isEmpty(clientIP) || "unknown".equalsIgnoreCase(clientIP)) {
                clientIP = req.getRemoteAddr();
            }
            if(StringUtils.isNotEmpty(clientIP) && clientIP.indexOf(",")>=0) {
                clientIP = clientIP.substring(0, clientIP.indexOf(","));
            }

            String res = StringEscapeUtils.unescapeJava("\\u4F60\\u7684") + "哈哈 IP=" + clientIP;
            log.info(res);

            log.info("哈哈"); // 乱码

            log.info(StringEscapeUtils.unescapeJava("\\u4F60\\u597D"));

            resp.setContentType("text/html; charset=utf-8");
            PrintWriter pw = resp.getWriter();
            pw.write(res);
            pw.flush();
            pw.close();
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 接收日期参数
     * @param resp
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("susanoo")
    public String susanoo(HttpServletResponse resp, Model model, User user) {
        try {
            log.info("user=" + user);
        } catch (Exception e) {
            log.error("异常了", e);
        } finally {
            try {
                resp.setContentType("text/html; charset=utf-8");
                PrintWriter pw = resp.getWriter();
                pw.write("Susanoo");
                pw.flush();
                pw.close();
            } catch (Exception e2) {
                log.error("response 异常了", e2);
            }
        }
        return null;
    }

    /**
     * TODO 以下是 Struts2 实际 jsonp 的代码示例
     * @param req
     * @param resp
     * @return
     */
//    public String testJsonp(HttpServletRequest req, HttpServletResponse resp) {
//        try {
//            String result = "";
//            //生成返回结果
//            String content = "{"
//                    + "\"venderId\":21234,"
//                    + "\"count\":3}";
//            String callback = req.getParameter("callback");
//
//            if(StringUtils.isEmpty(callback)) {
//                result = content;
//            } else {
//                result = callback + "(" + content + ")";
//            }
//
////            resp.setCharacterEncoding("UTF-8");
//            resp.setContentType("application/javascript");
//            ServletOutputStream output = resp.getOutputStream();
//
//            output.write(result.getBytes(Charset.forName("UTF-8")));
//        } catch (Exception e) {
//
//        /*
//        struts.xml 配置
//        <action name="getPendingConfirmCount" method="getPendingConfirmCount"
//                class="com.jd.pop.finance.vendor.web.action.finance.VenderStatementAction">
//            <result name="ajax" type="json">
//                <param name="callbackParameter">callback</param>
//                <param name="ignoreHierarchy">true</param>
//                <param name="excludeNullProperties">true</param>
//                <param name="includeProperties">pendingCount</param>
//                <!--<param name="root">PendingConfirmCount</param>-->
//            </result>
//        </action>
//         */
//        }
//        return null;
//    }
}
