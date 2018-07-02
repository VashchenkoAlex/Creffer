package com.creffer.security.access_handlers;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class CrefSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * Called when a user has been successfully authenticated.
     *
     * @param request        the request which caused the successful authentication
     * @param response       the response
     * @param authentication the <tt>Authentication</tt> object which was created during
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess() - CrefSuccessHandler");
        response.setStatus(HttpServletResponse.SC_OK);
        logger.info("AT onAuthenticationSuccess(...) function!");
        for (GrantedAuthority auth:authentication.getAuthorities()){
            String authority = auth.getAuthority();
            if ("ROLE_ADMIN".equals(authority)){
                response.sendRedirect("/adminDashboard");
            }
            if ("ROLE_MANAGER".equals(authority)){
                response.sendRedirect("/managerDashboard");
            }
            if ("ROLE_PUBLISHER".equals(authority)){
                response.sendRedirect("/publisherDashboard");
            }
            if ("ROLE_ADVERTISER".equals(authority)){
                response.sendRedirect("/advertiserDashboard");
            }
        }

    }
}
