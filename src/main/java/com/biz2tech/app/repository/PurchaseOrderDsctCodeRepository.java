package com.biz2tech.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.PurchaseOrderDsctCode;


/**
 * Spring Data JPA repository for the PurchaseOrderDsctCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PurchaseOrderDsctCodeRepository extends JpaRepository<PurchaseOrderDsctCode, Long> {

	Optional<PurchaseOrderDsctCode> findOneByCode(String code);

}
