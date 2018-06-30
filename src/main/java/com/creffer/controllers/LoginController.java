package com.creffer.controllers;

import com.creffer.models.LoginModel;
import com.creffer.models.SuccessModel;
import com.creffer.models.users.UserModel;
import com.creffer.services.security.GetTokenService;
import com.creffer.services.users.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    /*@Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager;
    public LoginController(){
        super();
    }*/
    @Autowired
    private GetTokenService tokenService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView login(){
        return new ModelAndView("forward:/pages/login.html");
    }

    @RequestMapping(method = RequestMethod.POST, produces = "json/application")
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ModelAndView login(@ModelAttribute LoginModel loginModel, final HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        SuccessModel successModel = userService.validateUser(loginModel);
        mav.addObject(successModel);
        if (successModel.isCorrectPassword()&&successModel.getStatus()==1){
            try {
                //SecurityContextHolder.getContext().setAuthentication();
                tokenService.getToken(loginModel);

                if ("ADMIN".equals(successModel.getRole())) {
                    System.out.println("Admin Passed");
                    mav.setViewName("forward:/pages/protected/admin/dashboard.html");
                }
                if ("MANAGER".equals(successModel.getRole())) {
                    System.out.println("Manager Passed");
                    mav.setViewName("forward:/pages/protected/manager/dashboard.html");
                }
                if ("PUBLISHER".equals(successModel.getRole())) {
                    System.out.println("Publisher Passed");
                    mav.setViewName("forward:/pages/protected/publisher/dashboard.html");
                }
                if ("ADVERTISER".equals(successModel.getRole())) {
                    System.out.println(" Advertiser Passed");
                    mav.setViewName("forward:/pages/protected/advertiser/dashboard.html");
                }
            }catch (Exception ex){
                System.out.println("Auth error");
            }
        }else{
            System.out.println("Login Error");
            mav.setViewName("/login?error=true");
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
    }

    @RequestMapping(value = "/print", method = RequestMethod.POST)
    public void printUser(){
        SecurityContext sc = SecurityContextHolder.getContext();
        System.out.println("Logged User: "+sc.getAuthentication().getName());
    }*/
}
