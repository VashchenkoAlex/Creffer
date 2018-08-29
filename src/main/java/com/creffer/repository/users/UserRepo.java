package com.creffer.repository.users;

import com.creffer.models.users.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@RepositoryRestResource
public interface UserRepo extends JpaRepository<UserModel, Long>{
    UserModel findByEmail(String email);

    //@Query("select usermodel from  UserModel usermodel")
    List<UserModel> findAll();

    //@Query("DELETE from UserModel usermodel WHERE usermodel.id")
    int deleteById(int id);
//     Map<String,UserModel> findUsers();
    // UserModel userlist();
}
