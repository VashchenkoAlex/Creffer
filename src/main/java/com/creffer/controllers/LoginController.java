package com.creffer.controllers;

import com.creffer.models.SuccessModel;
import com.creffer.services.users.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/login")
    public ModelAndView login() {
        log.info("we're at /login.GET");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login");
        return mav;
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public ModelAndView login(@RequestParam("email") final String email,
                              @RequestParam("password") final String password,
                              final HttpServletRequest request,
                              final HttpServletResponse response) {
        log.info("we're at /login.POST.email+password");
        ModelAndView mav = new ModelAndView();
        SuccessModel successModel = userService.validateUser(email, password);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        if (successModel!=null&&successModel.isAccessed()) {
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                String role = successModel.getRoles().get(0).getRole();
                if ("ROLE_ADMIN".equals(role)) {
                    log.info("Admin success");
                    mav.setViewName("redirect:/adminDashboard");
                }
                if ("ROLE_MANAGER".equals(role)) {
                    log.info("Manager success");
                    mav.setViewName("redirect:/managerDashboard");
                }
                if ("ROLE_PUBLISHER".equals(role)) {
                    log.info("Publisher success");
                    mav.setViewName("redirect:/publisherDashboard");
                }
                if ("ROLE_ADVERTISER".equals(role)) {
                    log.info("Advertiser success");
                    mav.setViewName("redirect:/advertiserDashboard");
                }
            } catch (Exception ex) {
                log.info("Authentication error");
                mav.addObject("messge","Authentication_error");
                mav.addObject("error",true);
                mav.setViewName("redirect:/login");
            }
        } else {
            log.info("Login Error");
            mav.addObject("error",true);
            mav.addObject("message","Success_error");
            mav.setViewName("redirect:/login");
        }

        return mav;
    }

    @GetMapping(value = "/login", params = {"error","message"})
    @ResponseBody
    public ModelAndView login(@RequestParam("error") boolean error,
                              @RequestParam("message") String message){
        log.info("we're at parametrized /login.GET.error"+message);
        ModelAndView mav = new ModelAndView();
        mav.addObject("message",message);
        mav.addObject("error",error);
        mav.setViewName("/login");
        return mav;
    }
}
