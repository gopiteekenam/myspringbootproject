package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.UserCouponCode;

/**
 * Spring Data JPA repository for the UserCouponCode entity. This entity takes
 * care of CRUD operations of UserCouponCode
 */
@Repository
public interface UserCouponCodeRepository extends JpaRepository<UserCouponCode, Long> {

	@Query("select cc from UserCouponCode cc where cc.id.userId=:userId")
	public List<UserCouponCode> findByUserIdIn(@Param("userId") Long userId);

	@Query("select cc from UserCouponCode cc where cc.id.couponCode=:couponCode")
	public List<UserCouponCode> findByCouponCodeIn(@Param("couponCode") String couponCode);

	@Query("select cc from UserCouponCode cc where cc.id.userId=:userId and cc.id.couponCode=:couponCode")
	public UserCouponCode findByUserIdAndCouponCodeIn(@Param("userId") Long userId,
			@Param("couponCode") String couponCode);

}
