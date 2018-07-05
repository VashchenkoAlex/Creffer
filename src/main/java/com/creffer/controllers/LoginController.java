package com.creffer.controllers;

import com.creffer.models.SuccessModel;
import com.creffer.services.security.GetTokenService;
import com.creffer.services.security.UserDetailsServiceImpl;
import com.creffer.services.users.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("forward:/pages/login.html");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView login(@RequestParam("email") final String email,
                              @RequestParam("password") final String password,
                              final HttpServletRequest request,
                              final HttpServletResponse response) {

        ModelAndView mav = new ModelAndView();
        SuccessModel successModel = userService.validateUser(email, password);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
        if (successModel.isAccessed()) {
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                String role = successModel.getRoles().get(0).getRole();
                if ("ROLE_ADMIN".equals(role)) {
                    System.out.println("Admin passed");
                    mav.setViewName("redirect:/adminDashboard");
                }
                if ("ROLE_MANAGER".equals(role)) {
                    System.out.println("Manager Passed");
                    mav.setViewName("redirect:/managerDashboard");
                }
                if ("ROLE_PUBLISHER".equals(role)) {
                    System.out.println("Publisher Passed");
                    mav.setViewName("redirect:/publisherDashboard");
                }
                if ("ROLE_ADVERTISER".equals(role)) {
                    System.out.println(" Advertiser Passed");
                    mav.setViewName("redirect:/advertiserDashboard");
                }
            } catch (Exception ex) {
                System.out.println("Auth error");
                mav.setViewName("forward:/login?error=true");
            }
        } else {
            System.out.println("Login Error");
            mav.setViewName("forward:/login?error=true");
        }

        return mav;
    }
}
