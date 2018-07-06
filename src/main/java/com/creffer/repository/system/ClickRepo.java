package com.creffer.repository.system;

import com.creffer.models.system.ClickModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClickRepo extends JpaRepository<ClickModel,String> {
}
