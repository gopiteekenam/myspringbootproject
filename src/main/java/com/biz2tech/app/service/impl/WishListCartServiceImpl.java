package com.biz2tech.app.service.impl;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.WishListCart;
import com.biz2tech.app.repository.WishListCartRepository;
import com.biz2tech.app.service.WishListCartService;
import com.biz2tech.app.service.dto.WishListCartDTO;
import com.biz2tech.app.service.mapper.WishListCartMapper;

/**
 * Service Implementation for managing WishListCart.
 */
@Service
@Transactional
public class WishListCartServiceImpl implements WishListCartService {

	private final Logger log = LoggerFactory.getLogger(WishListCartServiceImpl.class);

	private final WishListCartRepository wishListCartRepository;

	private final WishListCartMapper wishListCartMapper;

	public WishListCartServiceImpl(WishListCartRepository wishListCartRepository,
			WishListCartMapper wishListCartMapper) {
		this.wishListCartRepository = wishListCartRepository;
		this.wishListCartMapper = wishListCartMapper;
	}

	/**
	 * Save a wishListCart.
	 *
	 * @param wishListCartDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public WishListCartDTO save(WishListCartDTO wishListCartDTO) {
		log.debug("Request to save WishListCart : {}", wishListCartDTO);
		WishListCart wishListCart = wishListCartMapper.toEntity(wishListCartDTO);
		wishListCart = wishListCartRepository.save(wishListCart);
		return wishListCartMapper.toDto(wishListCart);
	}

	/**
	 * Get all the wishListCarts.
	 *
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public List<WishListCartDTO> findAll() {
		log.debug("Request to get all WishListCarts");
		return wishListCartRepository.findAll().stream().map(wishListCartMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	/**
	 * Get one wishListCart by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public Optional<WishListCartDTO> findOne(Long id) {
		log.debug("Request to get WishListCart : {}", id);
		return Optional.ofNullable(wishListCartMapper.toDto(wishListCartRepository.findOne(id)));

	}

	/**
	 * Delete the wishListCart by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id, String userName) {
		log.debug("Request to delete WishListCart : {}", id);
		wishListCartRepository.deleteByIdAndCreatedBy(id, userName);
	}

	@Override
	@Transactional
	public WishListCartDTO findWishListCartByUserName(String userName) {
		log.debug("Request to get ShoppingCart : {}", userName);
		WishListCart wishListCart = wishListCartRepository.findByCreatedBy(userName);
		if (null == wishListCart) {
			WishListCartDTO WishListCartDTO = new WishListCartDTO();
			WishListCartDTO.setDescription("automatically created to get user");
			WishListCartDTO.setCreatedBy(userName);
			WishListCartDTO.setLastUpdatedBy(userName);
			WishListCartDTO.setCreatedOn(Instant.now());
			WishListCartDTO.setLastUpdatedOn(Instant.now());
			wishListCart = wishListCartRepository.save(wishListCartMapper.toEntity(WishListCartDTO));
		}
		return wishListCartMapper.toDto(wishListCart);
	}

	@Override
	public Optional<WishListCartDTO> findByIdAndUser(Long id, String userName) {
		return Optional.ofNullable(wishListCartMapper.toDto(wishListCartRepository.findByIdAndCreatedBy(id, userName)));
	}
}
