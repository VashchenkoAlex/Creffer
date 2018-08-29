package com.creffer.services.user;

import com.creffer.models.system.SuccessModel;
import com.creffer.models.users.UserModel;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserModel findUserByEmail(String email);
    List<UserModel> userlist();
//    int remove_user(int id);

    void savePublisher(UserModel user);

    SuccessModel validateUser(String email, String password) throws Exception;



}
