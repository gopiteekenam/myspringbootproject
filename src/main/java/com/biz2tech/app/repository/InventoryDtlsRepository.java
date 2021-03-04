package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.InventoryDtls;


/**
 * Spring Data JPA repository for the InventoryDtls entity.
 */
@Repository
public interface InventoryDtlsRepository extends JpaRepository<InventoryDtls, Long> {

  @Query("select pd.inventoryDtls.id from ProductDtls pd where pd.id in (:prdtIds)")
  public List<Long> getInventoryIds(@Param("prdtIds") List<Long> prdtIds);

  public InventoryDtls findByInventoryIdentifier(String inventoryIdentifier);

  @Modifying
  @Query("update InventoryDtls it set it.avblCnt=0,it.totalCnt=0,it.sellCnt=0")
  public void resetAllInventoryCount();

}
