package com.umbrella.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by 大洲 on 15-3-30.
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Properties properties = new Properties();
        properties.put(Velocity.FILE_RESOURCE_LOADER_PATH, this.getServletContext().getRealPath("/vmtemplates"));
        VelocityEngine engine = new VelocityEngine();
        engine.init(properties);

        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");

        Template template = engine.getTemplate("hello.vm");
        template.merge(context, resp.getWriter());
    }
}
