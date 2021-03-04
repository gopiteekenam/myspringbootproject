package com.biz2tech.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.config.Constants;
import com.biz2tech.app.domain.ProductDtls;
import com.biz2tech.app.domain.ProductRating;
import com.biz2tech.app.domain.ProductTag;
import com.biz2tech.app.repository.InventoryDtlsRepository;
import com.biz2tech.app.repository.ProductDtlsRepository;
import com.biz2tech.app.repository.ProductRatingRepository;
import com.biz2tech.app.repository.ProductTagRepository;
import com.biz2tech.app.service.ProductDtlsService;
import com.biz2tech.app.service.dto.ColorMood;
import com.biz2tech.app.service.dto.ColorRating;
import com.biz2tech.app.service.dto.ProductDtlsDTO;
import com.biz2tech.app.service.dto.ProductSearchDTO;
import com.biz2tech.app.service.dto.ProductSearchPossibilitiesDTO;
import com.biz2tech.app.service.dto.ProductSearchResultsDTO;
import com.biz2tech.app.service.dto.SortBy;
import com.biz2tech.app.service.dto.UserReview;
import com.biz2tech.app.service.mapper.ProductDtlsMapper;

/**
 * Service Implementation for managing ProductDtls.
 */
@Service
@Transactional
public class ProductDtlsServiceImpl implements ProductDtlsService {

  private static final Logger log = LoggerFactory.getLogger(ProductDtlsServiceImpl.class);

  private final InventoryDtlsRepository inventoryDtlsRepository;
  private final ProductDtlsRepository productDtlsRepository;
  private final ProductDtlsMapper productDtlsMapper;
  private final ProductTagRepository productTagRepository;
  private final ProductRatingRepository productRatingRepository;
  

  public ProductDtlsServiceImpl(ProductDtlsRepository productDtlsRepository,
      ProductDtlsMapper productDtlsMapper, ProductTagRepository productTagRepository,
      ProductRatingRepository productRatingRepository,InventoryDtlsRepository inventoryDtlsRepository) {
    this.inventoryDtlsRepository = inventoryDtlsRepository;
	this.productDtlsRepository = productDtlsRepository;
    this.productDtlsMapper = productDtlsMapper;
    this.productTagRepository = productTagRepository;
    this.productRatingRepository = productRatingRepository;
  }

  /**
   * Save a productDtls.
   *
   * @param productDtlsDTO the entity to save
   * @return the persisted entity
   */
  @Override
  public ProductDtlsDTO save(ProductDtlsDTO productDtlsDTO) {
    log.debug("Request to save ProductDtls : {}", productDtlsDTO);
    ProductDtls productDtls = productDtlsMapper.toEntity(productDtlsDTO);
    fillProductTagDetails(productDtls, productDtlsDTO.getPrdtTagNames());
    inventoryDtlsRepository.save(productDtls.getInventoryDtls());
    productDtls = productDtlsRepository.save(productDtls);
    return productDtlsMapper.toDto(productDtls);
  }



  /**
   * Get all the productDtls.
   *
   * @return the list of entities
   */
  @Override
  @Transactional(readOnly = true)
  public List<ProductDtlsDTO> findAll() {
    log.debug("Request to get all ProductDtls");
    return productDtlsRepository.findAll().stream().map(productDtlsMapper::toDto)
        .collect(Collectors.toCollection(LinkedList::new));
  }

  /**
   * Get one productDtls by id.
   *
   * @param id the id of the entity
   * @return the entity
   */
  @Override
  @Transactional(readOnly = true)
  public ProductDtlsDTO findOne(Long id) {
    log.debug("Request to get ProductDtls : {}", id);
    ProductDtls productDtls = productDtlsRepository.findOne(id);
    ProductDtlsDTO productDtlsDTO = productDtlsMapper.toDto(productDtls);
    fillProductUserAndColorReviewDetails(Arrays.asList(productDtlsDTO));
    return productDtlsDTO;
  }


  @Override
  @Transactional(readOnly = true)
  public List<ProductDtlsDTO> findByProductIds(List<Long> productIds) {
    log.debug("Request to get ProductDtls : {}", productIds);
    List<ProductDtls> productDtlList = productDtlsRepository.findAll(productIds);
    List<ProductDtlsDTO> productDtlsDTOList = productDtlsMapper.toDto(productDtlList);
    fillProductUserAndColorReviewDetails(productDtlsDTOList);
    return productDtlsDTOList;
  }

  /**
   * Delete the productDtls by id.
   *
   * @param id the id of the entity
   */
  @Override
  public void delete(Long id) {
    log.debug("Request to delete ProductDtls : {}", id);
    productDtlsRepository.delete(id);
  }

  @Override
  public List<ProductDtlsDTO> findOneByInventoryIdentifier(List<String> inventoryIdentifiers) {
    List<ProductDtls> productDtlsList =
        productDtlsRepository.getProductByInventoryIdentifiers(inventoryIdentifiers);
    List<ProductDtlsDTO> productDtlsDTOList = productDtlsMapper.toDto(productDtlsList);
    fillProductUserAndColorReviewDetails(productDtlsDTOList);
    return productDtlsDTOList;
  }

  @Override
  public ProductSearchPossibilitiesDTO getProductSearchPossibilities() {
    ProductSearchPossibilitiesDTO possibilitiesDTO = new ProductSearchPossibilitiesDTO();
    List<ProductDtls> productDtlsList = productDtlsRepository.findAll();

    possibilitiesDTO
        .withPrdtTags(productTagRepository.findAll().stream().map(item -> item.getTagName())
            .distinct().collect(Collectors.joining(",")))
        .withPrdtBrands(productDtlsList.stream().map(item -> item.getBrandName()).distinct()
            .collect(Collectors.joining(",")))
        .withPrdtCatgs(productDtlsList.stream().map(item -> item.getPrdtCategory()).distinct()
            .collect(Collectors.joining(",")))
        .withPrdtTypes(productDtlsList.stream().map(item -> item.getPrdtType()).distinct()
            .collect(Collectors.joining(",")));

    return possibilitiesDTO;
  }
  
  @Override
  @Transactional(readOnly = true)
  public List<ProductDtlsDTO> getProductPriceRangeBySearchCrieteria(Double low, Double high) {
    log.debug("Request to get ProductDtls price rance by search crieteria" + low + "" + high);
    List<ProductDtlsDTO> productDtlsDTOs = null;
    log.debug("Price filter low is:"+low);
    log.debug("Price filter high is:"+high);
    List<ProductDtls> productDtlsList = productDtlsRepository.findByBasePriceBetween(low,high);
    log.debug("Product details list is :"+productDtlsList.size());
    productDtlsDTOs = productDtlsList.stream().map(productDtlsMapper::toDto).collect(Collectors.toList());

    return productDtlsDTOs;
  }

  @Override
  @Transactional(readOnly = true)
  public ProductSearchResultsDTO findAllByProductSearchCrieteria(ProductSearchDTO productSearchDTO) {
    log.debug("Request to get all ProductDtls by search crieteria" + productSearchDTO);

    if(productSearchDTO.getPrdtBrand()!=null && productSearchDTO.getPrdtBrand().equalsIgnoreCase("undefined"))
    {
    	productSearchDTO.setPrdtBrand(null);
    }
    if(productSearchDTO.getPrdtType()!=null && productSearchDTO.getPrdtType().equalsIgnoreCase("undefined"))
    {
    	productSearchDTO.setPrdtType(null);
    }
//    if(productSearchDTO.getPageNumber() ==0 || productSearchDTO.getPageNumber() == null)
//    {
//    	productSearchDTO.setPageNumber(1);
//    }
    List<ProductDtlsDTO> productDtlsDTOs = null;
    long totalResultSize = 1;
    List<ProductDtls> productDtlsList = null;
    int pageSize = (productSearchDTO.getPageSize() != null) ? productSearchDTO.getPageSize()
        : Constants.DEFAULT_PAGE_SIZE;

    // this is to obtain max, min values based on search criteria, and the call should come from
    // getProductPriceRanceBySearchCrieteria() only
    if (productSearchDTO.getPageNumber().equals(Integer.MAX_VALUE)) {
      pageSize = Integer.MAX_VALUE;
    }

    Pageable page = createPageRequest(productSearchDTO.getPageNumber().intValue(), pageSize,
        createSort(productSearchDTO.getSortBy()));

    // optimizing query if only product tag is included
    if (isOnlyProductTagSearch(productSearchDTO)) {
      productDtlsList =
          productDtlsRepository.findByProductTags_TagNameIn(getProductTagsList(productSearchDTO));
      totalResultSize = productDtlsList.size();
      productDtlsDTOs = productDtlsList.stream().skip(page.getOffset()).limit(page.getPageSize())
          .map(productDtlsMapper::toDto).collect(Collectors.toList());
    } else {
      Predicate<ProductDtls> tagsPredicate = getProductTagBasedPredicate(productSearchDTO);
      Predicate<ProductDtls> pricePredicate = getProductPriceBasedPredicate(productSearchDTO);
      Predicate<ProductDtls> prdtCategoryPredicate = getProductCategoryPredicate(productSearchDTO);

      // preparing matcher with search DTO
      ProductDtls productDtls = new ProductDtls();
      productDtls.setPrdtType(productSearchDTO.getPrdtType());
      // productDtls.setPrdtCategory(productSearchDTO.getPrdtCatg());
      productDtls.setBrandName(productSearchDTO.getPrdtBrand());
      productDtls.setPrdtTitle(productSearchDTO.getPrdtTitle());
      productDtls.setCreatedOn(null);
      productDtls.setLastUpdatedOn(null);
      Example<ProductDtls> productExample =
          Example.of(productDtls, ExampleMatcher.matching().withMatcher("prdtTitle",
              GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING)));

      // query has filter
      if (isQueryHasFilter(productSearchDTO)) {
        productDtlsList =
            productDtlsRepository.findAll(productExample, createSort(productSearchDTO.getSortBy()));

        // check if product tag conditions also need to apply
        pricePredicate = (StringUtils.isNotBlank(productSearchDTO.getPrdtTag()))
            ? pricePredicate.and(tagsPredicate)
            : pricePredicate;

        totalResultSize =
            productDtlsList.stream().filter(pricePredicate).filter(prdtCategoryPredicate).count();
        productDtlsDTOs = productDtlsList.stream().filter(pricePredicate)
            .filter(prdtCategoryPredicate).skip(page.getOffset()).limit(page.getPageSize())
            .map(productDtlsMapper::toDto).collect(Collectors.toList());
      } else {
        // query does not have filter
        if (StringUtils.isBlank(productSearchDTO.getPrdtTag())) {
          productDtlsList = productDtlsRepository.findAll(productExample, page).getContent();
          totalResultSize = productDtlsList.stream()
              .filter(prdtCategoryPredicate).count();
          productDtlsDTOs =
              productDtlsList.stream().filter(prdtCategoryPredicate).filter(prdtCategoryPredicate)
                  .map(productDtlsMapper::toDto).collect(Collectors.toList());
        } else {
          productDtlsList = productDtlsRepository.findAll(productExample,
              createSort(productSearchDTO.getSortBy()));
          totalResultSize =
              productDtlsList.stream().filter(tagsPredicate).filter(prdtCategoryPredicate).count();
          productDtlsDTOs = productDtlsList.stream().filter(tagsPredicate)
              .filter(prdtCategoryPredicate).skip(page.getOffset()).limit(page.getPageSize())
              .map(productDtlsMapper::toDto).collect(Collectors.toList());
        }
      }
    }

		fillProductUserAndColorReviewDetails(productDtlsDTOs);
		ProductSearchResultsDTO productSearchResultsDTO = new ProductSearchResultsDTO(productSearchDTO.getPageNumber().longValue(), totalResultSize / pageSize,
				productDtlsDTOs);
		
		return productSearchResultsDTO;

	}

	/**
	 * Method implementation to get list of brands with the count of each brand
	 * @param productSearchResultsDTO
	 */
	public Map<String,Long> getbrandsWithCount() {

		Map<String, Long> brandWithCount = new HashMap<String, Long>();
		List<Object[]> list = productDtlsRepository.getAllBrandsWithCount();
		for (Object[] ob : list) {
			String key = (String) ob[0];
			if (key != null) {
				Long value = (Long) ob[1];
				brandWithCount.put(key, value);
			}
		}
		return brandWithCount;
	}

	private boolean isQueryHasFilter(ProductSearchDTO productSearchDTO) {
		return productSearchDTO.getPriceFilter() != null && (productSearchDTO.getPriceFilter().getLow() != null
				|| productSearchDTO.getPriceFilter().getHigh() != null);
	}

	private Predicate<ProductDtls> getProductPriceBasedPredicate(ProductSearchDTO productSearchDTO) {
	    Predicate<ProductDtls> pricePredicate = item -> item != null;
    if (isQueryHasFilter(productSearchDTO)) {

      if (productSearchDTO.getPriceFilter().getLow() != null) {
        pricePredicate = pricePredicate
            .and(item -> item.getBasePrice() >= productSearchDTO.getPriceFilter().getLow());
      }

      if (productSearchDTO.getPriceFilter().getHigh() != null) {
        pricePredicate = pricePredicate
            .and(item -> item.getBasePrice() <= productSearchDTO.getPriceFilter().getHigh());
      }
    }
    return pricePredicate;
  }

  private Predicate<ProductDtls> getProductCategoryPredicate(ProductSearchDTO productSearchDTO) {
    Predicate<ProductDtls> catgPredicate = item -> item != null;

    if (StringUtils.isNoneBlank(productSearchDTO.getPrdtCatg())) {
      Predicate<ProductDtls> catgInputPred =
          (item -> productSearchDTO.getPrdtCatg().equalsIgnoreCase(item.getPrdtCategory()));
      Predicate<ProductDtls> catgStdPred =
          (item -> "unisex".equalsIgnoreCase(item.getPrdtCategory()));

      catgInputPred = catgInputPred.or(catgStdPred);
      catgPredicate = catgPredicate.and(catgInputPred);
    }

    return catgPredicate;
  }

  private Predicate<ProductDtls> getProductTagBasedPredicate(ProductSearchDTO productSearchDTO) {
    Predicate<ProductDtls> tagsPredicate = item -> item != null;
    for (String productTag : getProductTagsList(productSearchDTO)) {
      tagsPredicate = tagsPredicate.and(item -> item.getProductTags().stream()
          .anyMatch(tagItem -> tagItem.getTagName().equalsIgnoreCase(productTag)));
    }
    return tagsPredicate;
  }

  private List<String> getProductTagsList(ProductSearchDTO productSearchDTO) {
    return (StringUtils.isNotBlank(productSearchDTO.getPrdtTag())) ? Constants.PATTERN_COMMA
        .splitAsStream(productSearchDTO.getPrdtTag()).collect(Collectors.toList())
        : new ArrayList<String>();
  }

  private boolean isOnlyProductTagSearch(ProductSearchDTO productSearchDTO) {
    return StringUtils.isBlank(productSearchDTO.getPrdtType())
        && StringUtils.isBlank(productSearchDTO.getPrdtCatg())
        && StringUtils.isBlank(productSearchDTO.getPrdtBrand())
        && StringUtils.isBlank(productSearchDTO.getPrdtTitle())
        && productSearchDTO.getPriceFilter() == null
        && StringUtils.isNotBlank(productSearchDTO.getPrdtTag());
  }


  /**
   * @param productDtls
   * @param tagNames -- comma separated tag names
   */
  private void fillProductTagDetails(ProductDtls productDtls, String tagNames) {
    if (StringUtils.isBlank(tagNames)) {
      return;
    }

    List<ProductTag> productTags = new ArrayList<ProductTag>();
    List<String> productTagNames = Arrays.asList(tagNames.split(","));

    productTags.addAll(productTagRepository.findByTagNameIn(productTagNames));

    if (productTagNames.size() != productTags.size()) {
      for (String tagName : productTagNames) {
        if (productTags.stream().map(item -> item.getTagName())
            .anyMatch(item -> item.equals(tagName))) {
          continue;
        }
        productTagRepository.save(buildProductTag(tagName));
        productTags.add(productTagRepository.findByTagName(tagName));
      }
    }

    productDtls.setProductTags(new HashSet<ProductTag>(productTags));

  }


  private ProductTag buildProductTag(String tagName) {
    ProductTag productTag = new ProductTag();
    productTag.tagName(tagName);
    return productTag;
  }

  private void fillProductUserAndColorReviewDetails(List<ProductDtlsDTO> productDtlsDTOList) {
    UserReview userReview = new UserReview();
    // prepare list of product IDs
    List<BigDecimal> prdtIds = productDtlsDTOList.stream()
        .map(item -> BigDecimal.valueOf(item.getId())).collect(Collectors.toList());
    // group product reviews by product IDs
    Map<BigDecimal, List<ProductRating>> productRatingsByPrdtIdMap = productRatingRepository
        .findByPrdtIdIn(prdtIds).stream().collect(Collectors.groupingBy(item -> item.getPrdtId()));

    if (MapUtils.isNotEmpty(productRatingsByPrdtIdMap)) {
      for (ProductDtlsDTO productDtlsDTO : productDtlsDTOList) {
        List<ProductRating> productRatings =
            productRatingsByPrdtIdMap.get(BigDecimal.valueOf(productDtlsDTO.getId()));
        if (CollectionUtils.isNotEmpty(productRatings)) {

          int totalVotesByProduct =
              productRatings.stream().mapToInt(item -> item.getUserReviewCount().intValue()).sum();

          // Grouped by color position
          Map<BigDecimal, List<ProductRating>> productRatingsByPosition = productRatings.stream()
              .collect(Collectors.groupingBy(item -> item.getColorPosition()));

          productRatingsByPosition.forEach((colorPosition, productRatingForPosition) -> {
            // Grouped by color mood level from the list of productRatings with unique color
            // position
            Map<BigDecimal, List<ProductRating>> productRatingsByMoodLevel =
                productRatingForPosition.stream()
                    .collect(Collectors.groupingBy(item -> item.getColorMoodLevel()));
            int totalVotesByPosition = productRatingForPosition.stream()
                .mapToInt(item -> item.getUserReviewCount().intValue()).sum();

            // creating color review for every position
            ColorRating colorRating = new ColorRating();
            colorRating.withColorPosition(colorPosition.intValue()).withUserRating(
                calculateUserReviewRating(totalVotesByPosition, totalVotesByProduct));

            productRatingsByMoodLevel.forEach((colorMoodLevel, productRatingsForMoodLevel) -> {
              int totalVotesByMoodLevel = productRatingsForMoodLevel.stream()
                  .mapToInt(item -> item.getUserReviewCount().intValue()).sum();

              ColorMood colorMood = new ColorMood();
              colorMood.withColorMoodLevel(colorMoodLevel.intValue())
                  .withUserReviewCount(totalVotesByMoodLevel).withUserRating(
                      calculateUserReviewRating(totalVotesByMoodLevel, totalVotesByPosition));
              colorRating.withColorMood(colorMood);
              productDtlsDTO.withColorRating(colorRating);
            });
          });

          productDtlsDTO
              .setUserReview(userReview.withReviewCount(String.valueOf(totalVotesByProduct))
                  .withRating(String.valueOf(productRatings.stream()
                      .mapToDouble(item -> item.getUserRating()).average().getAsDouble())));
        }

      }
    }

  }

  private int calculateUserReviewRating(long votes, long totalVotes) {
    if (votes == 0 || totalVotes == 0) {
      return 0;
    }
    return (int) ((votes * 100) / totalVotes);
  }

  private Pageable createPageRequest(int pageNumber, int pageSize, Sort sort) {
    Pageable pageable = null;
    if (sort == null) {
      pageable = new PageRequest(pageNumber, pageSize);
    } else {
      pageable = new PageRequest(pageNumber, pageSize, sort);
    }
    return pageable;
  }

  private Sort createSort(SortBy sortBy) {
    Sort sort = null;
    if (sortBy != null && StringUtils.isNotBlank(sortBy.getSortOn())) {
      sort = new Sort((StringUtils.isNotBlank(sortBy.getDirection()))
          ? Direction.fromString(sortBy.getDirection())
          : Direction.ASC, sortBy.getSortOn());
    }
    return sort;
  }

  /**
   *	To get the product details by product name.  This method is implemented to fetch the list of sub products for each product
   */
  
	@Override
	public List<ProductDtlsDTO> findByPrdtTitle(String prdtTitle) {
		return productDtlsRepository.findByPrdtTitleLike(prdtTitle).stream().map(productDtlsMapper::toDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

@Override
public List<ProductDtlsDTO> getAllProductsByAdvanceSearch(String search) {
	log.debug("find all products by advance search :::"+search);
	search = "%"+search+"%";
	log.debug("category name is "+search);
	return productDtlsRepository.getAllProductsByAdvanceSearchIn(search).stream().map(productDtlsMapper::toDto)
			.collect(Collectors.toCollection(LinkedList::new));
}

@Override
public List<ProductDtlsDTO> findByColorCategory_CategoryNameIn(String categoryName) {
	log.debug("find all products by color category :::"+categoryName);
	return productDtlsRepository.findByColorCategory_CategoryNameIn(categoryName).stream().map(productDtlsMapper::toDto)
			.collect(Collectors.toCollection(LinkedList::new));
}

@Override
public List<ProductDtlsDTO> findByProductTagNames(List<String> productTagNames) {
	log.debug("find all products by product category name in  :::"+productTagNames);
	List<ProductDtlsDTO> lstProductDtlsDTO = productDtlsRepository.findByProductTags_TagNameIn(productTagNames).stream().map(productDtlsMapper::toDto)
			.collect(Collectors.toCollection(LinkedList::new));
	return lstProductDtlsDTO;
}

	@Override
	public Map<Double, Double> getProductPriceMinandMax() {
		log.debug("find min and max price range");
		Map<Double, Double> minmaxpricerange = new HashMap<Double, Double>();
		List<Object[]> list = productDtlsRepository.getProductPriceMinandMax();
		for (Object[] ob : list) {
			Double key = (Double) ob[0];
			Double value = (Double) ob[1];
			minmaxpricerange.put(key, value);
		}
		return minmaxpricerange;
	}

	@Override
	public Map<Double, Double> getProductDiscountMinandMax() {
		log.debug("find min and max discount range");
		Map<Double, Double> minmaxdiscountrange = new HashMap<Double, Double>();
		List<Object[]> list = productDtlsRepository.getProductDiscountMinandMax();
		for (Object[] ob : list) {
			Double key = (Double) ob[0];
			Double value = (Double) ob[1];
			minmaxdiscountrange.put(key, value);
		}
		return minmaxdiscountrange;
	}

	@Override
	public List<ProductDtlsDTO> getProductDiscountRangeBySearchCrieteria(Double low, Double high) {
		log.debug("Request to get ProductDtls discount range by search crieteria" + low + "" + high);
	    List<ProductDtlsDTO> productDtlsDTOs = null;
	    log.debug("discount filter low is:"+low);
	    log.debug("discount filter high is:"+high);
	    List<ProductDtls> productDtlsList = productDtlsRepository.findByMarkedPercentageBetween(low,high);
	    log.debug("Product details list is :"+productDtlsList.size());
	    productDtlsDTOs = productDtlsList.stream().map(productDtlsMapper::toDto).collect(Collectors.toList());

	    return productDtlsDTOs;
	}


}
