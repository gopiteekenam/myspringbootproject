package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.ColorCategory;


/**
 * Spring Data JPA repository for the Color Category entity.
 * This entity takes care of CRUD operations of Color Category
 */
@Repository
public interface ColorCategoryRepository extends JpaRepository<ColorCategory, Long> {
	
  public List<ColorCategory> findByCategoryName(String categoryName);

  
}
