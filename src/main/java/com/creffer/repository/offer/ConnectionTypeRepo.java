package com.creffer.repository.offer;

import com.creffer.models.offer.ConnectionTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface ConnectionTypeRepo extends JpaRepository<ConnectionTypeModel,Long>{
}
