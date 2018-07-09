package com.creffer.repository.offer;

import com.creffer.models.offer.OfferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface OfferRepo extends JpaRepository<OfferModel,Long> {
}
