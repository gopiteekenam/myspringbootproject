package com.biz2tech.app.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.News;

/**
 * Spring Data JPA repository for the News.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
	public List<News> findByNewsDate(Instant newsDate);
	
}
