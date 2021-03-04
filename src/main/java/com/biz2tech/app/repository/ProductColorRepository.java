package com.biz2tech.app.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.ProductColor;


/**
 * Spring Data repository for the ProductColor entity.
 */
@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {
  public List<ProductColor> findByPrdtId(BigDecimal prdtId);
}
