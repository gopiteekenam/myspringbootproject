package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.ShoppingCartItem;


/**
 * Spring Data JPA repository for the ShoppingCartItem entity.
 */
@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
  public List<ShoppingCartItem> findByShoppingCartId(Long shoppingCartId);

}
