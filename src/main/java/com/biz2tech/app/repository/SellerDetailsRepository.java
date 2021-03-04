package com.biz2tech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.SellerDetails;


/**
 * Spring Data JPA repository for the SellerDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SellerDetailsRepository extends JpaRepository<SellerDetails, Long> {

}
