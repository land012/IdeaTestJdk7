package com.umbrella.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by 大洲 on 15-3-30.
 */
public class GreetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String propsFilename = this.getInitParameter("velocity_properties");
//        Properties p = new Properties();
//        p.put(Velocity.FILE_RESOURCE_LOADER_PATH, this.getServletContext().getRealPath("/vmtemplates"));
//        Velocity.init(p);

        Velocity.addProperty(Velocity.FILE_RESOURCE_LOADER_PATH, this.getServletContext().getRealPath("/vmtemplates"));
        Velocity.init();

        Template t = Velocity.getTemplate("greet.vm");

        VelocityContext context = new VelocityContext();
        context.put("name", "Velocity");

        t.merge(context, resp.getWriter());
    }
}
