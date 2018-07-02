package com.creffer.services.users.user;

import com.creffer.models.LoginModel;
import com.creffer.models.SuccessModel;
import com.creffer.models.users.UserModel;
import com.creffer.models.users.RoleModel;
import com.creffer.repository.users.UserRepo;
import com.creffer.repository.users.RoleRepo;
import com.creffer.security.TokenAuth;
import com.creffer.services.security.GetTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userServise")
public class UserServiceImpl implements UserService {
    @Autowired
    private GetTokenServiceImpl getTokenService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public UserModel findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

    @Override
    public void savePublisher(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleModel pubRole = roleRepo.findByRole("PUBLISHER");
        ArrayList<RoleModel> roles = new ArrayList<>();
        roles.add(pubRole);
        user.setRoles(roles);
        userRepo.save(user);
    }

    @Override
    public void saveManager(UserModel user) {
        //TO DO
    }

    @Override
    public void saveAdmin(UserModel user) {
        //TO DO
    }

    @Override
    public void saveAdvertiser(UserModel user) {
        //TO DO
    }

    @Override
    public SuccessModel validateUser(String email, String password) {
        String token = "";
        UserModel user = userRepo.findByEmail(email);
        String correctPassword = user.getPassword();
        SuccessModel success = new SuccessModel();
        try {
           token = getTokenService.getToken(email,password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean checkPass = bCryptPasswordEncoder.matches(password,correctPassword);
        TokenAuth tokenAuth = new TokenAuth(token,user.getRoles(),checkPass,user,correctPassword);
        SecurityContextHolder.getContext().setAuthentication(tokenAuth);
        success.setEmail(user.getEmail());
        success.setRoles(user.getRoles());
        success.setStatus(user.getActive());
        success.setCorrectPassword(checkPass);
        return success;
    }
}
