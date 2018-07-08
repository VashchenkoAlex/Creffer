package com.creffer.repository.offer;

import com.creffer.models.offer.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("countryRepository")
public interface CountryRepo extends JpaRepository<CountryModel,Long> {
    CountryModel save(CountryModel countryModel);
}
