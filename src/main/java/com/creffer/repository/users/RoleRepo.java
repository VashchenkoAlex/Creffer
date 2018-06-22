package com.creffer.repository.users;

import com.creffer.models.users.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepo")
public interface RoleRepo extends JpaRepository<RoleModel, Integer>{
    RoleModel findByRole(String role);
}
