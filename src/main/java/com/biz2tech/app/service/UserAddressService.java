package com.biz2tech.app.service;

import java.util.List;
import java.util.Optional;

import com.biz2tech.app.service.dto.UserAddressDTO;

/**
 * Service Interface for managing UserAddress.
 */
public interface UserAddressService {

  /**
   * Save a userAddress.
   *
   * @param userAddressDTO the entity to save
   * @return the persisted entity
   */
  UserAddressDTO save(UserAddressDTO userAddressDTO, String userLoginId);

  /**
   * Get all the userAddresses.
   *
   * @return the list of entities
   */
  List<UserAddressDTO> findAll();


  /**
   * Get the "id" userAddress.
   *
   * @param id the id of the entity
   * @return the entity
   */
  Optional<UserAddressDTO> findOne(Long id);

  /**
   * Delete the "id" userAddress.
   *
   * @param id the id of the entity
   */
  void delete(Long id);

  List<UserAddressDTO> findByUserId(String userId);


}
