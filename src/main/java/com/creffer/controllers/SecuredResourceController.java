package com.creffer.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecuredResourceController {
    @RequestMapping("/secured")
    public void secureResource(HttpServletRequest request, HttpServletResponse response){
        System.out.println("accessing secured resource");
    }
}
