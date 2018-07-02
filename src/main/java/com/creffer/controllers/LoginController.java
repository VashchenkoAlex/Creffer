package com.creffer.controllers;

import com.creffer.models.LoginModel;
import com.creffer.models.SuccessModel;
import com.creffer.models.system.TokenModel;
import com.creffer.security.TokenAuth;
import com.creffer.services.security.GetTokenService;
import com.creffer.services.security.UserDetailsServiceImpl;
import com.creffer.services.users.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    /*
    public LoginController(){
        super();
    }*/
    @Autowired
    private GetTokenService tokenService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("forward:/pages/login.html");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ModelAndView login(@RequestParam("email") final String email,
                              @RequestParam("password") final String password,
                              final HttpServletRequest request,
                              final HttpServletResponse response){
        ModelAndView mav = new ModelAndView();
        SuccessModel successModel = userService.validateUser(email, password);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());
        //mav.addObject("token",authentication.getToken());
        if (successModel.isCorrectPassword()&&successModel.getStatus()==1){
            response.setStatus(HttpServletResponse.SC_OK);
            try {
                String role = successModel.getRoles().get(0).getRole();
                if ("ROLE_ADMIN".equals(role)) {
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
                    mav.setViewName("redirect:advertiserDashboard");
                }
            }catch (Exception ex){
                System.out.println("Auth error");
                mav.setViewName("forward:/login?error=true");
            }
        }else{
            System.out.println("Login Error");
            mav.setViewName("forward:/login?error=true");
        }

        return mav;
    }

    /*@PostMapping("/login")
    public void login(@RequestParam("email")final String email,
                      @RequestParam("password") final String password,
                      final HttpServletRequest request){
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(email,password);
        Authentication auth = authenticationManager.authenticate(authReq);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT",sc);
    }*/
}
