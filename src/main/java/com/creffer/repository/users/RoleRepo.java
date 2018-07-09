package com.creffer.repository.users;

import com.creffer.models.system.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface RoleRepo extends JpaRepository<RoleModel, Integer>{
    RoleModel findByRole(String role);
}
