package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;

import com.biz2tech.app.domain.News;
import com.biz2tech.app.service.dto.NewsDTO;

/**
 * Mapper for the entity User and its DTO called UserDTO.
 *
 * Normal mappers are generated using MapStruct, this one is hand-coded as
 * MapStruct support is still in beta, and requires a manual step with an IDE.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NewsMapper extends EntityMapper<NewsDTO, News> {

	@Override
	News toEntity(NewsDTO newsDTO);

	default News fromId(Long id) {
		if (id == null) {
			return null;
		}
		News news = new News();
		news.setId(id);
		return news;
	}
}
