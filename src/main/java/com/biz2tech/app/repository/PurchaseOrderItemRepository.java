package com.biz2tech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.PurchaseOrderItem;


/**
 * Spring Data repository for the PurchaseOrderItem entity.
 */
@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {


}
