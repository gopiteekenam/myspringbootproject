package com.biz2tech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.DropShipDtls;


/**
 * Spring Data JPA repository for the DropShipDtls entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DropShipDtlsRepository extends JpaRepository<DropShipDtls, Long> {

}
