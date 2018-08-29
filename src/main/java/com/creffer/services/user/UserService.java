package com.creffer.services.user;

import com.creffer.models.system.SuccessModel;
import com.creffer.models.users.UserModel;

import java.util.List;
import java.util.Map;

public interface UserService {
    UserModel findUserByEmail(String email);
    //  Map<String, UserModel> userlist(int active);
    List<UserModel> userlist();

    void savePublisher(UserModel user);

    SuccessModel validateUser(String email, String password) throws Exception;


}
