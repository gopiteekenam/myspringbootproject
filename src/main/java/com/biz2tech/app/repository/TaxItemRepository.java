package com.biz2tech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.TaxItem;


/**
 * Spring Data JPA repository for the TaxItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxItemRepository extends JpaRepository<TaxItem, Long> {

}
