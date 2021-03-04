package com.biz2tech.app.service.dto;

import java.io.Serializable;
import java.time.Instant;

/**
 * A DTO for Testimonials entity.
 */
public class TestimonialsDTO implements Serializable {

	private Long id;

	private String city;

	private String commentorName;

	private Instant commentedDate;

	private String comments;

	private String reviewedBy;

	private String reviewerComments;

	private String createdBy;

	private Instant createdOn;

	private String lastUpdatedBy;

	private Instant lastUpdatedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommentorName() {
		return commentorName;
	}

	public void setCommentorName(String commentorName) {
		this.commentorName = commentorName;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Instant getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Instant createdOn) {
		this.createdOn = createdOn;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Instant getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Instant lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((commentedDate == null) ? 0 : commentedDate.hashCode());
		result = prime * result + ((commentorName == null) ? 0 : commentorName.hashCode());
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastUpdatedBy == null) ? 0 : lastUpdatedBy.hashCode());
		result = prime * result + ((lastUpdatedOn == null) ? 0 : lastUpdatedOn.hashCode());
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
		TestimonialsDTO other = (TestimonialsDTO) obj;
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
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastUpdatedBy == null) {
			if (other.lastUpdatedBy != null)
				return false;
		} else if (!lastUpdatedBy.equals(other.lastUpdatedBy))
			return false;
		if (lastUpdatedOn == null) {
			if (other.lastUpdatedOn != null)
				return false;
		} else if (!lastUpdatedOn.equals(other.lastUpdatedOn))
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
		return "TestimonialsDTO [id=" + id + ", commentorName=" + commentorName + ", city=" + city + ", commentedDate="
				+ commentedDate + ", comments=" + comments + ", reviewedBy=" + reviewedBy + ", reviewerComments="
				+ reviewerComments + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", lastUpdatedBy="
				+ lastUpdatedBy + ", lastUpdatedOn=" + lastUpdatedOn + "]";
	}

}
