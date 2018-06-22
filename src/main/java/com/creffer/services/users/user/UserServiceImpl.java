package com.creffer.services.users.user;

import com.creffer.models.users.UserModel;
import com.creffer.models.users.RoleModel;
import com.creffer.repository.users.UserRepo;
import com.creffer.repository.users.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
@Service("userServise")
public class UserServiceImpl implements UserService {
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
    public void saveUser(UserModel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        RoleModel adminRole = roleRepo.findByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(adminRole)));
        userRepo.save(user);
    }
}
