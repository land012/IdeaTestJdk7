package com.umbrella.velocity;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.VelocityViewServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 大洲 on 15-3-31.
 */
public class VelocityServlet extends VelocityViewServlet {
    @Override
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context ctx) {
        ctx.put("name", "Orochimaru");
        return super.getTemplate("index.vm");
    }

    @Override
    protected void setContentType(HttpServletRequest request, HttpServletResponse response) {
        super.setContentType(request, response);
    }
}
