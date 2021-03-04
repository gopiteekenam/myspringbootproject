package com.biz2tech.app.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserCouponCodeId implements Serializable {

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "coupon_code")
	private String couponCode;

	public UserCouponCodeId() {
		super();
	}

	public UserCouponCodeId(Long userId, String couponCode) {
		super();
		this.userId = userId;
		this.couponCode = couponCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((couponCode == null) ? 0 : couponCode.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserCouponCodeId other = (UserCouponCodeId) obj;
		if (couponCode == null) {
			if (other.couponCode != null)
				return false;
		} else if (!couponCode.equals(other.couponCode))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserCouponCodeId [userId=" + userId + ", couponCode=" + couponCode + "]";
	}

}
