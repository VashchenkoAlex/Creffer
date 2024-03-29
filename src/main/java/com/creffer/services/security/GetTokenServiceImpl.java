package com.creffer.services.security;

import com.creffer.models.system.RoleModel;
import com.creffer.models.users.UserModel;
import com.creffer.services.user.UserDetailsServiceImpl;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetTokenServiceImpl implements GetTokenService {
    private static final String TOKEN_KEY = "creffer2018";
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    public String getToken(String email, String password) throws Exception {
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
            tokenData.put("username", email);
            long createDate = new Date().getTime();
            tokenData.put("token_create_date", createDate);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, 100);
            long expDate = createDate + 1000000;
            tokenData.put("token_expiration_date", expDate);

            JwtBuilder jwtBuilder = Jwts.builder();
            jwtBuilder.setExpiration(calendar.getTime());
            jwtBuilder.setClaims(tokenData);
            //  JWT key
            return jwtBuilder.signWith(SignatureAlgorithm.HS512, TOKEN_KEY).compact();
        }else {
            throw new Exception("Authentication error");
        }
    }
}
