package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;

import com.biz2tech.app.domain.Testimonials;
import com.biz2tech.app.service.dto.TestimonialsDTO;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as
 * MapStruct support is still in beta, and requires a manual step with an IDE.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TestimonialsMapper extends EntityMapper<TestimonialsDTO, Testimonials> {

	@Override
	Testimonials toEntity(TestimonialsDTO testimonialsDTO);

	default Testimonials fromId(Long id) {
		if (id == null) {
			return null;
		}
		Testimonials testimonials = new Testimonials();
		testimonials.setId(id);
		return testimonials;
	}
}
