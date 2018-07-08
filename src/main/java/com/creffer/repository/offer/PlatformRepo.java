package com.creffer.repository.offer;

import com.creffer.models.offer.PlatformModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("platformRepository")
public interface PlatformRepo extends JpaRepository<PlatformModel,Long> {
    PlatformModel save(PlatformModel platformModel);
}
