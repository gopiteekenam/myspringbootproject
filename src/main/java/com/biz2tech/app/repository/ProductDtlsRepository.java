package com.biz2tech.app.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.biz2tech.app.domain.ProductDtls;


/**
 * Spring Data JPA repository for the ProductDtls entity.
 */
@Repository
public interface ProductDtlsRepository extends JpaRepository<ProductDtls, Long> {

  public List<ProductDtls> findByProductTags_TagNameIn(List<String> tagName);
  
  public List<ProductDtls> findByColorCategory_CategoryNameIn(String categoryName);

  public List<ProductDtls> findByPrdtType(String prdtType, Pageable pageable);

  public List<ProductDtls> findByPrdtTitleLike(String title);

  @Query("select pd from ProductDtls pd where pd.brandName=:prdtBrand or pd.prdtCategory=:prdtCatg or "
      + "pd.prdtTitle like :prdtTitle or pd.prdtType=:prdtType")
  public List<ProductDtls> findAllByProductSearchCrieteria(@Param("prdtBrand") String prdtBrand,
      @Param("prdtCatg") String prdtCatg, @Param("prdtTitle") String prdtTitle,
      @Param("prdtType") String prdtType, /* @Param("prdtTag") String prdtTag, */ Sort sort);

  @Query(
      value = "select pd from ProductDtls pd where pd.inventoryDtls.inventoryIdentifier in (:invIdentifier)")
  public ProductDtls getProductByInventoryIdentifier(@Param("invIdentifier") String invIdentifier);

  @Query(
      value = "select pd from ProductDtls pd where pd.inventoryDtls.inventoryIdentifier in (:invIdentifiers)")
	public List<ProductDtls> getProductByInventoryIdentifiers(@Param("invIdentifiers") List<String> invIdentifiers);

	public List<ProductDtls> findByPrdtTitle(String prdtTitle);

	public List<ProductDtls> findByBasePriceBetween(Double low, Double high);

	@Query(
		      value = "select brandName,count(brandName) from ProductDtls GROUP BY brandName")
	public List<Object[]> getAllBrandsWithCount();
	
	@Query(value = "select pd from ProductDtls pd where pd.prdtTitle in (:search) or pd.prdtDesc like (:search) or pd.brandName like (:search) or pd.prdtType like (:search) or pd.prdtCategory like (:search)")
	public List<ProductDtls> getAllProductsByAdvanceSearchIn(@Param("search") String search);

	@Query(
		      value = "select min(sellPrice),max(sellPrice) from ProductDtls")
	public List<Object[]> getProductPriceMinandMax();

	@Query(
		      value = "select min(markedPercentage),max(markedPercentage) from ProductDtls")
	public List<Object[]> getProductDiscountMinandMax();

	public List<ProductDtls> findByMarkedPercentageBetween(Double low, Double high);
	

}
