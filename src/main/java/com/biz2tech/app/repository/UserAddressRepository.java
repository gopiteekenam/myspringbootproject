package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.UserAddress;


/**
 * Spring Data repository for the UserAddress entity.
 */
@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

  @Modifying
  @Query("update UserAddress ua set ua.isDefault=false where ua.createdBy=:userLoginId")
  public void resetDefaultFlagInUserAddresses(@Param("userLoginId") String userLoginId);

  public List<UserAddress> findByCreatedBy(String userId);
}
