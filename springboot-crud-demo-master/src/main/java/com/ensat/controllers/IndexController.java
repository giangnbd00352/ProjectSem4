package com.ensat.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Homepage controller.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    String index() {
        return "login";
    }
    
    @RequestMapping("/admin")
    String admin() {
        return "admin/dashboard";
    }

}
