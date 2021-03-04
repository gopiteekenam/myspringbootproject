package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.Testimonials;

/**
 * Spring Data JPA repository for the Testimonials.
 */
@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonials, Long> {
	public List<Testimonials> findByCity(String city);

	public List<Testimonials> findByCommentorName(String commentorName);
}
