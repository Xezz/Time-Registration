package org.xezz.timeregistration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: bkoch
 * Date: 18.05.13
 * Time: 22:13
 * To change this template use File | Settings | File Templates.
 * Serve a default view
 */
@Controller
@RequestMapping(value = "/*")
public class UIController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UIController.class);
    /*
        Map index.jsp to / see:
        http://stackoverflow.com/questions/1483063/spring-mvc-3-and-handling-static-content-am-i-missing-something
     */
    @RequestMapping(value = "/")
    public String index() {
        LOGGER.info("INSIDE REQUESTMAPPING FOR /");
        return "index";
    }
}
