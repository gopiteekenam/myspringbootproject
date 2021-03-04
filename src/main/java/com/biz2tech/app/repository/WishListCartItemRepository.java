package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.WishListCartItem;


/**
 * Spring Data repository for the WishListCartItem entity.
 */
@Repository
public interface WishListCartItemRepository extends JpaRepository<WishListCartItem, Long> {
  public List<WishListCartItem> findByWishListCartId(Long wishListCartId);
}
