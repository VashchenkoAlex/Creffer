package com.creffer.services.users.user;

import com.creffer.models.users.UserModel;

public interface UserService {
    UserModel findUserByEmail(String email);
    void saveUser(UserModel user);
}
