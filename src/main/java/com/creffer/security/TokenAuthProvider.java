package com.creffer.security;

import com.creffer.models.users.RoleModel;
import com.creffer.models.users.UserModel;
import com.creffer.services.security.GetTokenServiceImpl;
import com.creffer.services.users.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenAuthProvider implements AuthenticationProvider{
    @Autowired
    UserServiceImpl userService;
    @Autowired
    GetTokenServiceImpl getTokenService;
    /**
     * Performs authentication with the same contract as
     * {@link AuthenticationManager#authenticate(Authentication)}
     * .
     *
     * @param authentication the authentication request object.
     * @return a fully authenticated object including credentials. May return
     * <code>null</code> if the <code>AuthenticationProvider</code> is unable to support
     * authentication of the passed <code>Authentication</code> object. In such a case,
     * the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException if authentication fails.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("authenticate() TokenAuthProvider");
        String email = authentication.getName();
        String pass = authentication.getCredentials().toString();
        UserModel user = userService.findUserByEmail(email);
        if(user.getPassword().equals(pass)){
            final List<RoleModel> grantedAuths  = user.getRoles();
            Authentication authent = null;
            try {
                authent = new TokenAuth(getTokenService.getToken(email,pass),grantedAuths,true,user,pass);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return authent;
        }
        return null;
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the
     * indicated <Code>Authentication</code> object.
     * <p>
     * Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented
     * instance of the <code>Authentication</code> class. It simply indicates it can
     * support closer evaluation of it. An <code>AuthenticationProvider</code> can still
     * return <code>null</code> from the {@link #authenticate(Authentication)} method to
     * indicate another <code>AuthenticationProvider</code> should be tried.
     * </p>
     * <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing
     * authentication is conducted at runtime the <code>ProviderManager</code>.
     * </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public boolean supports(final Class<?> authentication) {
        System.out.println("overrided supports() TokenAuthProvider.class");
        return authentication.equals(TokenAuth.class);
    }
}
