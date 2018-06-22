package com.creffer.repository.users;

import com.creffer.models.users.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<UserModel, Long>{
    UserModel findByEmail(String email);
}
