package com.creffer.services.user;

import com.creffer.models.system.SuccessModel;
import com.creffer.models.users.UserModel;

import java.util.List;

public interface UserService {
    UserModel findUserByEmail(String email);
    void savePublisher(UserModel user);

    SuccessModel validateUser(String email, String password) throws Exception;


}
