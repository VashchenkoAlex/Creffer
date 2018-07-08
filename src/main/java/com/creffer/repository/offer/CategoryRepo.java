package com.creffer.repository.offer;

import com.creffer.models.offer.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("categoryRepository")
public interface CategoryRepo extends JpaRepository<CategoryModel,Long>{
    CategoryModel save(CategoryModel categoryModel);
}
