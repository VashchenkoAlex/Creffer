package com.creffer.controllers.manager;

import com.creffer.models.users.UserModel;
import com.creffer.services.user.UserService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ManagerDashBoardController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/managerDashboard")
    public ModelAndView dashGet(){


String view = "/protected/manager/dashboard";

        return new ModelAndView(view);
    }
    @PostMapping(value = "/managerDashboard")
    public ModelAndView dashPost(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);

        //UserModel userModel = userService.findUserByEmail("Staspropisnov@yandex.ru");
        Map<String, Object> map= new HashMap<>();
        map.put("contact", new UserModel());
        map.put("contactList", userService.findUserByEmail("Staspropisnov@yandex.ru"));


        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        response.setStatus(HttpServletResponse.SC_OK);
        return new ModelAndView("forward:/protected/manager/dashboard", map);
    }




}
