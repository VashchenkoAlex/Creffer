package com.creffer.repository.users;

import com.creffer.models.users.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("managerRepo")
public interface ManagerRepo extends JpaRepository<ManagerModel,Long>{
    ManagerModel findByEmail(String email);
}
