package com.biz2tech.app.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.biz2tech.app.domain.SellerDetails;
import com.biz2tech.app.service.dto.SellerDetailsDTO;

/**
 * Mapper for the entity SellerDetails and its DTO SellerDetailsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SellerDetailsMapper extends EntityMapper<SellerDetailsDTO, SellerDetails> {


    @Mapping(target = "productDtls", ignore = true)
    SellerDetails toEntity(SellerDetailsDTO sellerDetailsDTO);

    default SellerDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        SellerDetails sellerDetails = new SellerDetails();
        sellerDetails.setId(id);
        return sellerDetails;
    }
}
