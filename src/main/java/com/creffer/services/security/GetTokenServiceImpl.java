package com.creffer.services.security;

import com.creffer.models.LoginModel;
import com.creffer.models.system.TokenModel;
import com.creffer.models.users.RoleModel;
import com.creffer.models.users.UserModel;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Service
public class GetTokenServiceImpl implements GetTokenService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public String getToken(String email, String password) throws Exception {
        //String ip = loginModel.getIp();
        if (email==null||password==null){
            return null;
        }
        UserModel user = (UserModel) userDetailsService.loadUserByUsername(email);
        String thePassword = user.getPassword();

        Map<String,Object> tokenData = new HashMap<>();
        List<RoleModel> roles = user.getRoles();
        String role = roles.get(0).getAuthority();
        if (bCryptPasswordEncoder.matches(password,thePassword)){

            tokenData.put("clientType", role);
            //tokenData.put("userIP", ip);
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
            return token;
        }else {
            throw new Exception("Authentication error");
        }
    }
}
