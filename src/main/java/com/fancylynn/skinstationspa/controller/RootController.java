package com.fancylynn.skinstationspa.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Lynn on 2018/3/7.
 */

@RestController
public class RootController {
    @Value("${app-name}")
    private String appName;

    // welcome page
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }


    // swagger page
    @RequestMapping(path="/docs", method = RequestMethod.GET)
    public void swaggerpage(HttpServletResponse response) throws IOException {
        if (appName == null || appName.length() == 0) {
            response.sendRedirect("/swagger-ui.html");
        } else {
            response.sendRedirect("/" + appName + "/swagger-ui.html");
        }

    }

}
