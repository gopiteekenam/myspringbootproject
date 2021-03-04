package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.UserAddress;
import com.biz2tech.app.service.dto.UserAddressDTO;

/**
 * Mapper for the entity UserAddress and its DTO UserAddressDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserAddressMapper extends EntityMapper<UserAddressDTO, UserAddress> {

  @Override
  @Mapping(source = "user.id", target = "userId")
  UserAddressDTO toDto(UserAddress userAddress);

  @Override
  @Mapping(source = "userId", target = "user")
  UserAddress toEntity(UserAddressDTO userAddressDTO);

  default UserAddress fromId(Long id) {
    if (id == null) {
      return null;
    }
    UserAddress userAddress = new UserAddress();
    userAddress.setId(id);
    return userAddress;
  }
}
