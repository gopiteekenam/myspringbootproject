package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.WishListCart;
import com.biz2tech.app.domain.WishListCartItem;
import com.biz2tech.app.repository.WishListCartItemRepository;
import com.biz2tech.app.repository.WishListCartRepository;
import com.biz2tech.app.service.WishListCartItemService;
import com.biz2tech.app.service.dto.WishListCartItemDTO;
import com.biz2tech.app.service.mapper.WishListCartItemMapper;

/**
 * Service Implementation for managing WishListCartItem.
 */
@Service
@Transactional
public class WishListCartItemServiceImpl implements WishListCartItemService {

  private final Logger log = LoggerFactory.getLogger(WishListCartItemServiceImpl.class);

  private final WishListCartItemRepository wishListCartItemRepository;
  private final WishListCartRepository wishListCartRepository;

  private final WishListCartItemMapper wishListCartItemMapper;

  public WishListCartItemServiceImpl(WishListCartItemRepository wishListCartItemRepository,
      WishListCartItemMapper wishListCartItemMapper,
      WishListCartRepository wishListCartRepository) {
    this.wishListCartItemRepository = wishListCartItemRepository;
    this.wishListCartItemMapper = wishListCartItemMapper;
    this.wishListCartRepository = wishListCartRepository;
  }

  /**
   * Save a wishListCartItem.
   *
   * @param wishListCartItemDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public WishListCartItemDTO save(WishListCartItemDTO wishListCartItemDTO) {
    log.debug("Request to save WishListCartItem : {}", wishListCartItemDTO);
    WishListCartItem wishListCartItem = wishListCartItemMapper.toEntity(wishListCartItemDTO);
    wishListCartItem = wishListCartItemRepository.save(wishListCartItem);
    return wishListCartItemMapper.toDto(wishListCartItem);
  }

  /**
   * Get all the wishListCartItems.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<WishListCartItemDTO> findAll() {
    log.debug("Request to get all WishListCartItems");
    return wishListCartItemRepository.findAll().stream().map(wishListCartItemMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public List<WishListCartItemDTO> findByUserName(String userName) {
    log.debug("Request to get all logged in user WishListCartItems");

    WishListCart wishListCart = wishListCartRepository.findByCreatedBy(userName);

    return wishListCartItemRepository.findByWishListCartId((Long) wishListCart.getId()).stream()
        .map(wishListCartItemMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one wishListCartItem by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<WishListCartItemDTO> findOne(Long id) {
    log.debug("Request to get WishListCartItem : {}", id);
    return Optional
        .ofNullable(wishListCartItemMapper.toDto(wishListCartItemRepository.findOne(id)));
  }

  /**
   * Delete the wishListCartItem by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete WishListCartItem : {}", id);
    wishListCartItemRepository.delete(id);
  }
  
  /**
   * <p>To get the wishlist cart items by wishlistcartid</p>
   * @param Long wishListCartId
   * @return List<WishListCartItemDTO>
   */

@Override
public List<WishListCartItemDTO> findByWishListCartId(Long wishListCartId) {
	return wishListCartItemRepository.findByWishListCartId(wishListCartId).stream()
	        .map(wishListCartItemMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
}
}
