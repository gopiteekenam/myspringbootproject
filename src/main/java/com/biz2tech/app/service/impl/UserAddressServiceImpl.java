package com.biz2tech.app.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.User;
import com.biz2tech.app.domain.UserAddress;
import com.biz2tech.app.repository.UserAddressRepository;
import com.biz2tech.app.repository.UserRepository;
import com.biz2tech.app.service.UserAddressService;
import com.biz2tech.app.service.dto.UserAddressDTO;
import com.biz2tech.app.service.mapper.UserAddressMapper;

/**
 * Service Implementation for managing UserAddress.
 */
@Service
@Transactional
public class UserAddressServiceImpl implements UserAddressService {

  private final Logger log = LoggerFactory.getLogger(UserAddressServiceImpl.class);

  private final UserAddressRepository userAddressRepository;

  private final UserAddressMapper userAddressMapper;
  private final UserRepository userRepository;

  public UserAddressServiceImpl(UserAddressRepository userAddressRepository,
      UserAddressMapper userAddressMapper, UserRepository userRepository) {
    this.userAddressRepository = userAddressRepository;
    this.userAddressMapper = userAddressMapper;
    this.userRepository = userRepository;
  }

  /**
   * Save a userAddress.
   *
   * @param userAddressDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public UserAddressDTO save(UserAddressDTO userAddressDTO, String userLoginId) {
    log.debug("Request to save UserAddress : {}", userAddressDTO);

    Optional<User> userByEmailFromDatabase =
        userRepository.findOneWithAuthoritiesByEmail(userLoginId);
    Optional<User> userByLoginFromDatabase =
        userRepository.findOneWithAuthoritiesByLogin(userLoginId);
    userAddressDTO.setUserId(userByEmailFromDatabase.orElse(userByLoginFromDatabase.get()).getId());
    UserAddress userAddress = userAddressMapper.toEntity(userAddressDTO);

    // nullify default_flag all addresses linked with user only is user updating default flag
    if (userAddress.isDefault()) {
      userAddressRepository.resetDefaultFlagInUserAddresses(userLoginId);
    }
    userAddress = userAddressRepository.save(userAddress);
    return userAddressMapper.toDto(userAddress);
  }

  /**
   * Get all the userAddresses.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<UserAddressDTO> findAll() {
    log.debug("Request to get all UserAddresses");
    return userAddressRepository.findAll().stream().map(userAddressMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserAddressDTO> findByUserId(String userId) {
    log.debug("Request to get all UserAddresses");
    return userAddressRepository.findByCreatedBy(userId).stream().map(userAddressMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }


  /**
   * Get one userAddress by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<UserAddressDTO> findOne(Long id) {
    log.debug("Request to get UserAddress : {}", id);
    return Optional.ofNullable(userAddressRepository.findOne(id)).map(userAddressMapper::toDto);
  }

  /**
   * Delete the userAddress by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete UserAddress : {}", id);
    userAddressRepository.delete(id);
  }
}
