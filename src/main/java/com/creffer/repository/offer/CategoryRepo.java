package com.creffer.repository.offer;

import com.creffer.models.offer.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface CategoryRepo extends JpaRepository<CategoryModel,Long>{
}
