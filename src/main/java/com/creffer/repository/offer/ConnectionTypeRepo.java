package com.creffer.repository.offer;

import com.creffer.models.offer.ConnectionTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("connectionTypeRepository")
public interface ConnectionTypeRepo extends JpaRepository<ConnectionTypeModel,Long>{
    ConnectionTypeModel save(ConnectionTypeModel connectionTypeModel);
}
