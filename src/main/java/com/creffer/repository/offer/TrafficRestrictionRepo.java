package com.creffer.repository.offer;

import com.creffer.models.offer.TrafficRestrictionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("trafficRestrictionRepository")
public interface TrafficRestrictionRepo extends JpaRepository<TrafficRestrictionModel,Long> {
    TrafficRestrictionModel save(TrafficRestrictionModel trafficRestrictionModel);
}
