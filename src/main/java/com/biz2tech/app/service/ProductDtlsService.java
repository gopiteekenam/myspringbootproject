package com.biz2tech.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.repository.query.Param;

import com.biz2tech.app.domain.ProductDtls;
import com.biz2tech.app.service.dto.ProductDtlsDTO;
import com.biz2tech.app.service.dto.ProductSearchDTO;
import com.biz2tech.app.service.dto.ProductSearchPossibilitiesDTO;
import com.biz2tech.app.service.dto.ProductSearchResultsDTO;

/**
 * Service Interface for managing ProductDtls.
 */
public interface ProductDtlsService {

  /**
   * Save a productDtls.
   *
   * @param productDtlsDTO the entity to save
   * @return the persisted entity
   */
  ProductDtlsDTO save(ProductDtlsDTO productDtlsDTO);

  /**
   * Get all the productDtls.
   *
   * @return the list of entities
   */
  List<ProductDtlsDTO> findAll();

  /**
   * Get the "id" productDtls.
   *
   * @param id the id of the entity
   * @return the entity
   */
  ProductDtlsDTO findOne(Long id);

  List<ProductDtlsDTO> findOneByInventoryIdentifier(List<String> inventoryIdentifiers);

  /**
   * Delete the "id" productDtls.
   *
   * @param id the id of the entity
   */
  void delete(Long id);


  List<ProductDtlsDTO> findByProductIds(List<Long> productIds);
  
  List<ProductDtlsDTO> findByProductTagNames(List<String> tagname);

  ProductSearchResultsDTO findAllByProductSearchCrieteria(ProductSearchDTO productSearchDTO);

  ProductSearchPossibilitiesDTO getProductSearchPossibilities();

  
  List<ProductDtlsDTO> findByPrdtTitle(String prdtTitle);

  List<ProductDtlsDTO> getProductPriceRangeBySearchCrieteria(Double low, Double high);
  
  Map<String,Long> getbrandsWithCount();
  
  List<ProductDtlsDTO> getAllProductsByAdvanceSearch(String search);
  
  List<ProductDtlsDTO> findByColorCategory_CategoryNameIn(String categoryName);

  Map<Double, Double> getProductPriceMinandMax();

  Map<Double, Double> getProductDiscountMinandMax();

  List<ProductDtlsDTO> getProductDiscountRangeBySearchCrieteria(Double low, Double high);
  
}
