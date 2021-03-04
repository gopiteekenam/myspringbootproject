package com.biz2tech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.biz2tech.app.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
