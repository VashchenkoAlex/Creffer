package com.creffer.services.users.user;

import com.creffer.models.LoginModel;
import com.creffer.models.SuccessModel;
import com.creffer.models.users.UserModel;

public interface UserService {
    UserModel findUserByEmail(String email);
    void savePublisher(UserModel user);
    void saveManager(UserModel user);
    void saveAdmin(UserModel user);
    void saveAdvertiser(UserModel user);
    SuccessModel validateUser(String email, String password);
}
