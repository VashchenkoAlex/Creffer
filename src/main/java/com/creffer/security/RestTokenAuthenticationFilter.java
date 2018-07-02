package com.creffer.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public RestTokenAuthenticationFilter(){
        super("/pages/protected/**");
        setAuthenticationSuccessHandler((request,response,authentication)->
        {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String path = request.getServletPath();
            if (request.getPathInfo()!=null) {
                path += request.getPathInfo();
            }
            System.out.println("Path = "+path);
            request.getRequestDispatcher(path).forward(request,response);
        });
        setAuthenticationFailureHandler((request,response,authenticationException)->{
            System.out.println("Authentication Failure");
            response.getOutputStream().print(authenticationException.getMessage());
                });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader("token");
        if (token==null){
            token = request.getParameter("token");
        }
        if (token==null){
            System.out.println("Set Auth false");
            TokenAuth tokenAuth = new TokenAuth(null,null);
            tokenAuth.setAuthenticated(false);
            return tokenAuth;
        }
        TokenAuth tokenAuth = new TokenAuth(token,request);
        return getAuthenticationManager().authenticate(tokenAuth);
    }

    @Override
    public void doFilter(ServletRequest rq, ServletResponse rp, FilterChain chain)throws IOException,ServletException{
        super.doFilter(rq, rp, chain);
        System.out.println("overrided doFilter() -  RestTokenAuthFilter.class");
    }
}
