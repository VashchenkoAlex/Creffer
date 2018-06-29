package com.creffer.services.security;

import com.creffer.security.TokenAuth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

@Service
public class TokenAuthManager implements AuthenticationManager{
    @Autowired
    private UserDetailsService userDetailsService;
    /**
     * Attempts to authenticate the passed {@link Authentication} object, returning a
     * fully populated <code>Authentication</code> object (including granted authorities)
     * if successful.
     * <p>
     * An <code>AuthenticationManager</code> must honour the following contract concerning
     * exceptions:
     * <ul>
     */
     /*<li>A {@link DisabledException} must be thrown if an account is disabled and the
     * <code>AuthenticationManager</code> can test for this state.</li>
     * <li>A {@link LockedException} must be thrown if an account is locked and the
     * <code>AuthenticationManager</code> can test for account locking.</li>
     * <li>A {@link BadCredentialsException} must be thrown if incorrect credentials are
     * presented. Whilst the above exceptions are optional, an
     * <code>AuthenticationManager</code> must <B>always</B> test credentials.</li>
     * </ul>*/
     /** Exceptions should be tested for and if applicable thrown in the order expressed
     * above (i.e. if an account is disabled or locked, the authentication request is
     * immediately rejected and the credentials testing process is not performed). This
     * prevents credentials being tested against disabled or locked accounts.
     *
     * @param authentication the authentication request object
     * @return a fully authenticated object including credentials
     * @throws AuthenticationException if authentication fails
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try{
            if (authentication instanceof TokenAuth){
                return processAuth((TokenAuth) authentication);
            }else {
                authentication.setAuthenticated(false);
            }
        }catch (Exception ex){
            if (ex instanceof AuthenticationServiceException){
                throw ex;
            }
        }
        return authentication;
    }

    private TokenAuth buildFullTokenAuth(TokenAuth auth, DefaultClaims claims){
        User user = (User) userDetailsService.loadUserByUsername(claims.get("EMAIL",String.class));
        if (user.isEnabled()){
            Collection<GrantedAuthority> authorities = user.getAuthorities();
            return new TokenAuth(auth.getToken(),authorities,true,user);
        }else {
            throw new AuthenticationServiceException("User disabled");
        }
    }

    private TokenAuth processAuth(TokenAuth auth) throws AuthenticationException{
        String token = auth.getToken();
        String key = "creffer2018";
        DefaultClaims claims;
        try{
            claims = (DefaultClaims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        }catch (Exception ex){
            throw new AuthenticationServiceException("Token corrupted");
        }
        if (claims.get("TOKEN_EXPIRATION_DATE",Long.class)==null){
            throw new AuthenticationServiceException("Invalid token");
        }
        Date expiredDate = new Date(claims.get("TOKEN_EXPIRATION_DATE",Long.class));
        if (expiredDate.after(new Date())){
            return buildFullTokenAuth(auth,claims);
        }else {
            throw new AuthenticationServiceException("Token expired date error");
        }
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
