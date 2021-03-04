package com.biz2tech.app.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.ProductRating;


/**
 * Spring Data repository for the ProductRating entity.
 */
@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {
  public List<ProductRating> findByPrdtId(BigDecimal prdtId);

  public List<ProductRating> findByPrdtIdIn(List<BigDecimal> prdtIds);
}
