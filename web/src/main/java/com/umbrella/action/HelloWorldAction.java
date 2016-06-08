package com.umbrella.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xudazhou on 2016/5/15.
 */
public class HelloWorldAction extends BaseAction {

    private static final Logger log = LoggerFactory.getLogger(HelloWorldAction.class);

    public String helloworld() {
        Long id = Long.parseLong(request.getParameter("id"));
        return "helloworld";
    }
}
