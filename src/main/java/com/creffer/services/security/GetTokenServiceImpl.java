package com.creffer.services.security;

import com.creffer.models.LoginModel;
import com.creffer.models.system.TokenModel;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Service
public class GetTokenServiceImpl implements GetTokenService {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public TokenModel getToken(LoginModel loginModel) throws Exception {
        String password = loginModel.getPassword();
        String email = loginModel.getEmail();
        String ip = loginModel.getIp();
        if (email==null||password==null||ip==null){
            return null;
        }
        User user = (User) userDetailsService.loadUserByUsername(email);
        String thePassword = user.getPassword();
        System.out.println("ThePassword is "+thePassword);
        Map<String,Object> tokenData = new HashMap<>();
        ArrayList<GrantedAuthority> roles = (ArrayList<GrantedAuthority>) user.getAuthorities();
        String role = roles.get(0).getAuthority();
        if (password.equals(thePassword)){

            tokenData.put("clientType", role);
            tokenData.put("userIP", ip);
            tokenData.put("username", email);
            tokenData.put("token_create_date", new Date().getTime());
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            tokenData.put("token_expiration_date", calendar.getTime());

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            //  JWT key
            String key = "creffer2018";
            String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
            return new TokenModel(token,email);
        }else {
            throw new Exception("Authentication error");
        }
    }
}
