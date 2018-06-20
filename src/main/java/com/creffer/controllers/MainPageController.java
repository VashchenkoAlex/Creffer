package com.creffer.controllers;

import com.creffer.repository.MainPageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
    @Autowired
    private MainPageRepo repo;

    @RequestMapping(value = {"/","/main"})
    public String showMainPage(){
        return "main";
    }
}
