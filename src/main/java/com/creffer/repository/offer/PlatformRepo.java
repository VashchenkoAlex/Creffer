package com.creffer.repository.offer;

import com.creffer.models.offer.PlatformModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface PlatformRepo extends JpaRepository<PlatformModel,Long> {
}
