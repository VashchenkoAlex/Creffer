package com.creffer.repository.users;

import com.creffer.models.users.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<UserModel, Long>{
    UserModel findByEmail(String email);
}
