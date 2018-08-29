package com.creffer.services.user;

import com.creffer.models.system.SuccessModel;
import com.creffer.models.users.UserModel;
import com.creffer.models.system.RoleModel;
import com.creffer.repository.users.UserRepo;
import com.creffer.repository.users.RoleRepo;
import com.creffer.security.TokenAuth;
import com.creffer.services.security.GetTokenServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userServise")
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
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
    public List<UserModel> userlist() {
        List<UserModel> userModels = userRepo.userList();
        if( userModels != null &&!userModels.isEmpty()){
            return userModels;
        }
        return Collections.EMPTY_LIST;
    }
    @Override
    public void savePublisher(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleModel pubRole = roleRepo.findByRole("ROLE_PUBLISHER");
        ArrayList<RoleModel> roles = new ArrayList<>();
        roles.add(pubRole);
        user.setRoles(roles);
        userRepo.save(user);
    }



    @Override
    public SuccessModel validateUser(String email, String password) throws Exception {
        String token;
        SuccessModel success = new SuccessModel();
        UserModel user = userRepo.findByEmail(email);
        if (user==null){
            return null;
        }else {
            String correctPassword = user.getPassword();
            token = getTokenService.getToken(email,password);
            boolean checkPass = bCryptPasswordEncoder.matches(password,correctPassword);
            TokenAuth tokenAuth = new TokenAuth(token,user.getRoles(),checkPass,user,correctPassword);
            SecurityContextHolder.getContext().setAuthentication(tokenAuth);
            success.setEmail(email);
            success.setRoles(user.getRoles());
            success.setAccessed(checkPass&&user.getActive()==1);
            return success;

        }
    }




}
