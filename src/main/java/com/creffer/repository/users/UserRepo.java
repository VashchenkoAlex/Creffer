package com.creffer.repository.users;

import com.creffer.models.users.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepo extends JpaRepository<UserModel, Long>
        //,JpaSpecificationExecutor<UserModel>
    {
    UserModel save(UserModel user);
    void deleteByEmail(UserModel user);
    //void updateByEmail(UserModel user);
    UserModel findByEmail(String email);
}
