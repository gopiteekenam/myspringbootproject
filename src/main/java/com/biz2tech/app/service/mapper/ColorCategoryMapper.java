package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;

import com.biz2tech.app.domain.ColorCategory;
import com.biz2tech.app.service.dto.ColorCategoryDTO;

/**
 * Mapper for the entity ColorCategory and its DTO ColorCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ColorCategoryMapper extends EntityMapper<ColorCategoryDTO, ColorCategory> {

	@Override
	ColorCategory toEntity(ColorCategoryDTO colorCategoryDTO);

	default ColorCategory fromId(Long id) {
		if (id == null) {
			return null;
		}
		ColorCategory colorCategory = new ColorCategory();
		colorCategory.setId(id);
		return colorCategory;
	}
}
