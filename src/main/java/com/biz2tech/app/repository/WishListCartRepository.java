package com.biz2tech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.WishListCart;

/**
 * Spring Data repository for the WishListCart entity.
 */
@Repository
public interface WishListCartRepository extends JpaRepository<WishListCart, Long> {

	public WishListCart findByIdAndCreatedBy(Long id, String userName);

	public WishListCart findByCreatedBy(String userName);

	public void deleteByIdAndCreatedBy(Long id, String userName);

}
