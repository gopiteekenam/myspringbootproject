package com.biz2tech.app.repository;

import com.biz2tech.app.domain.Placement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Placement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlacementRepository extends JpaRepository<Placement, Long> {

}
