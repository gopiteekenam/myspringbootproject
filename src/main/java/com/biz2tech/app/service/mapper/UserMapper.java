package com.biz2tech.app.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.biz2tech.app.domain.Authority;
import com.biz2tech.app.domain.User;
import com.biz2tech.app.service.dto.UserDTO;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as MapStruct support is
 * still in beta, and requires a manual step with an IDE.
 */
@Service
public class UserMapper {

  public UserDTO userToUserDTO(User user) {
    return new UserDTO(user);
  }

  public List<UserDTO> usersToUserDTOs(List<User> users) {
    return users.stream().filter(Objects::nonNull).map(this::userToUserDTO)
        .collect(Collectors.toList());
  }

  public User userDTOToUser(UserDTO userDTO) {
    if (userDTO == null) {
      return null;
    } else {
      User user = new User();
      user.setId(userDTO.getId());
      user.setLogin(userDTO.getLogin());
      user.setFirstName(userDTO.getFirstName());
      user.setLastName(userDTO.getLastName());
      user.setEmail(userDTO.getEmail());
      user.setImageUrl(userDTO.getImageUrl());
      user.setActivated(userDTO.isActivated());
      user.setLangKey(userDTO.getLangKey());
      user.setUserPhone(userDTO.getUserPhone());
      Set<Authority> authorities = this.authoritiesFromStrings(userDTO.getAuthorities());
      if (authorities != null) {
        user.setAuthorities(authorities);
      }
      return user;
    }
  }

  public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
    return userDTOs.stream().filter(Objects::nonNull).map(this::userDTOToUser)
        .collect(Collectors.toList());
  }

  public User userFromId(Long id) {
    if (id == null) {
      return null;
    }
    User user = new User();
    user.setId(id);
    return user;
  }

  public Set<Authority> authoritiesFromStrings(Set<String> strings) {
    return strings.stream().map(string -> {
      Authority auth = new Authority();
      auth.setName(string);
      return auth;
    }).collect(Collectors.toSet());
  }
}
