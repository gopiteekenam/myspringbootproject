package com.biz2tech.app.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "user_coupon_code")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class UserCouponCode extends AbstractAuditingEntity {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserCouponCodeId id;

	public UserCouponCodeId getId() {
		return id;
	}

	public void setId(UserCouponCodeId id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserCouponCode other = (UserCouponCode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserCouponCode [id=" + id + "]";
	}

}
