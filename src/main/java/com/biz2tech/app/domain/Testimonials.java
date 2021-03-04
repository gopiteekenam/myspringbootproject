package com.biz2tech.app.domain;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Entity for Testimonials
 */
@Entity
@Table(name = "testimonials")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Testimonials extends AbstractAuditingEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "city")
	private String city;
	
	@Column(name = "commentor_name")
	private String commentorName;

	@Column(name = "commented_date")
	private Instant commentedDate;

	@Column(name = "comments")
	private String comments;

	@Column(name = "reviewed_by")
	private String reviewedBy;

	@Column(name = "reviewer_comments")
	private String reviewerComments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommenterName() {
		return commentorName;
	}

	public void setCommenterName(String commenterName) {
		this.commentorName = commenterName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Instant getCommentedDate() {
		return commentedDate;
	}

	public void setCommentedDate(Instant commentedDate) {
		this.commentedDate = commentedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getReviewedBy() {
		return reviewedBy;
	}

	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	public String getReviewerComments() {
		return reviewerComments;
	}

	public void setReviewerComments(String reviewerComments) {
		this.reviewerComments = reviewerComments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((commentedDate == null) ? 0 : commentedDate.hashCode());
		result = prime * result + ((commentorName == null) ? 0 : commentorName.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reviewedBy == null) ? 0 : reviewedBy.hashCode());
		result = prime * result + ((reviewerComments == null) ? 0 : reviewerComments.hashCode());
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
		Testimonials other = (Testimonials) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (commentedDate == null) {
			if (other.commentedDate != null)
				return false;
		} else if (!commentedDate.equals(other.commentedDate))
			return false;
		if (commentorName == null) {
			if (other.commentorName != null)
				return false;
		} else if (!commentorName.equals(other.commentorName))
			return false;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reviewedBy == null) {
			if (other.reviewedBy != null)
				return false;
		} else if (!reviewedBy.equals(other.reviewedBy))
			return false;
		if (reviewerComments == null) {
			if (other.reviewerComments != null)
				return false;
		} else if (!reviewerComments.equals(other.reviewerComments))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Testimonials [id=" + id + ", commenterName=" + commentorName + ", city=" + city + ", commentedDate="
				+ commentedDate + ", comments=" + comments + ", reviewedBy=" + reviewedBy + ", reviewerComments="
				+ reviewerComments + "]";
	}

}
