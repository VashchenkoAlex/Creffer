package com.creffer.repository.offer;

import com.creffer.models.offer.OfferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("offerRepository")
public interface OfferRepo extends JpaRepository<OfferModel,Long> {
    OfferModel save(OfferModel offerModel);
}
