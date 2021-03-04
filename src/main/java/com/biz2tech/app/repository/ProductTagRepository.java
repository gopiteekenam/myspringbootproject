package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.ProductTag;


/**
 * Spring Data JPA repository for the ProductTag entity.
 */
@Repository
public interface ProductTagRepository extends JpaRepository<ProductTag, Long> {
  public ProductTag findByTagName(String tagName);

  public List<ProductTag> findByTagNameIn(List<String> tagNames);
}
