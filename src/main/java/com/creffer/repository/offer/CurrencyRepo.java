package com.creffer.repository.offer;

import com.creffer.models.offer.CurrencyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("currencyRepository")
public interface CurrencyRepo extends JpaRepository<CurrencyModel,Long> {
    CurrencyModel save(CurrencyModel currencyModel);
}
