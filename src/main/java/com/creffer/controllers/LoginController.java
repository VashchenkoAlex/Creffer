package com.creffer.controllers;

import com.creffer.models.LoginModel;
import com.creffer.models.SuccessModel;
import com.creffer.models.system.TokenModel;
import com.creffer.security.TokenAuth;
import com.creffer.services.security.GetTokenService;
import com.creffer.services.security.UserDetailsServiceImpl;
import com.creffer.services.users.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
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

    @RequestMapping(method = RequestMethod.POST)
    //@ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ModelAndView login(@ModelAttribute LoginModel loginModel, final HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        SuccessModel successModel = userService.validateUser(loginModel);
        mav.addObject(successModel);
        if (successModel.isCorrectPassword()&&successModel.getStatus()==1){
            try {
                String token = tokenService.getToken(loginModel).getToken();
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginModel.getEmail());

                TokenAuth tokenAuth = new TokenAuth(token,successModel.getRoles(),successModel.isCorrectPassword(), userDetails);
                SecurityContextHolder.getContext().setAuthentication(tokenAuth);
                String role = successModel.getRoles().get(0).getRole();
                if ("ADMIN".equals(role)) {
                    System.out.println("Admin Passed");
                    mav.setViewName("forward:/pages/protected/admin/dashboard.html");
                }
                if ("MANAGER".equals(role)) {
                    System.out.println("Manager Passed");
                    mav.setViewName("forward:/pages/protected/manager/dashboard.html");
                }
                if ("PUBLISHER".equals(role)) {
                    System.out.println("Publisher Passed");
                    mav.setViewName("forward:/pages/protected/publisher/dashboard.html");
                }
                if ("ADVERTISER".equals(role)) {
                    System.out.println(" Advertiser Passed");
                    mav.setViewName("forward:/pages/protected/advertiser/dashboard.html");
                }
            }catch (Exception ex){
                ex.printStackTrace();
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
    }*/
}
