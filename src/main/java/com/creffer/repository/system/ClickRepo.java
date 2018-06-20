package com.creffer.repository.system;

import com.creffer.models.system.ClickModel;
import org.springframework.data.repository.CrudRepository;

public interface ClickRepo extends CrudRepository<ClickModel,String> {
}
