package com.biz2tech.app.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.domain.InventoryDtls;
import com.biz2tech.app.domain.ProductColor;
import com.biz2tech.app.domain.ProductDtls;
import com.biz2tech.app.domain.ProductRating;
import com.biz2tech.app.domain.ProductTag;
import com.biz2tech.app.repository.InventoryDtlsRepository;
import com.biz2tech.app.repository.ProductColorRepository;
import com.biz2tech.app.repository.ProductDtlsRepository;
import com.biz2tech.app.repository.ProductRatingRepository;
import com.biz2tech.app.repository.ProductTagRepository;
import com.biz2tech.app.service.ImportService;
import com.biz2tech.app.service.dto.ImportInventoryDetailDto;
import com.biz2tech.app.service.dto.ProductTagDTO;
import com.biz2tech.app.service.mapper.ImportInventoryDtlsMapper;
import com.biz2tech.app.service.mapper.ImportProductDtlsMapper;
import com.biz2tech.app.service.mapper.ProductTagMapper;

@Service
@Transactional
public class ImportServiceImpl implements ImportService {


  private final ProductDtlsRepository productDtlsRepository;
  private final InventoryDtlsRepository inventoryDtlsRepository;
  private final ProductTagRepository productTagRepository;
  private final ImportProductDtlsMapper importProductDtlsMapper;
  private final ImportInventoryDtlsMapper importInventoryDtlsMapper;
  private final ProductTagMapper productTagMapper;
  private final ProductColorRepository productColorRepository;
  private final ProductRatingRepository productRatingRepository;



  public ImportServiceImpl(ProductDtlsRepository productDtlsRepository,
      InventoryDtlsRepository inventoryDtlsRepository,
      ImportProductDtlsMapper importProductDtlsMapper,
      ImportInventoryDtlsMapper importInventoryDtlsMapper,
      ProductTagRepository productTagRepository, ProductTagMapper productTagMapper,
      ProductColorRepository productColorRepository,
      ProductRatingRepository productRatingRepository) {
    this.productDtlsRepository = productDtlsRepository;
    this.inventoryDtlsRepository = inventoryDtlsRepository;
    this.importProductDtlsMapper = importProductDtlsMapper;
    this.importInventoryDtlsMapper = importInventoryDtlsMapper;
    this.productTagRepository = productTagRepository;
    this.productTagMapper = productTagMapper;
    this.productColorRepository = productColorRepository;
    this.productRatingRepository = productRatingRepository;
  }



  @Override
  public Long importData(List<ImportInventoryDetailDto> importInventoryDetailDtoList) {

    long counter = 0;
    if (CollectionUtils.isNotEmpty(importInventoryDetailDtoList)) {
      inventoryDtlsRepository.resetAllInventoryCount();

      for (ImportInventoryDetailDto importInventoryDetailDto : importInventoryDetailDtoList) {
        Set<ProductTag> productTag = prepareProductTag(importInventoryDetailDto);
        InventoryDtls inventoryDtls = importInventoryDtlsMapper.toEntity(importInventoryDetailDto);
        InventoryDtls inventoryDtlsFromDB =
            inventoryDtlsRepository.findByInventoryIdentifier(importInventoryDetailDto.getItemId());
        // inventoryDtls.setId(Long.valueOf(importInventoryDetailDto.getItemId()));
        if (inventoryDtlsFromDB != null) {
          inventoryDtlsFromDB.merge(inventoryDtls);
          inventoryDtls = inventoryDtlsRepository.save(inventoryDtlsFromDB);
        } else {
          inventoryDtls = inventoryDtlsRepository.save(inventoryDtls);

        }

        ProductDtls productDtls = importProductDtlsMapper.toEntity(importInventoryDetailDto);
        ProductDtls productDtlsFromDB = productDtlsRepository
            .getProductByInventoryIdentifier(importInventoryDetailDto.getItemId());
        if (productDtlsFromDB != null) {
          productDtlsFromDB.merge(productDtls);
          productDtlsFromDB.setInventoryDtls(inventoryDtls);
          productDtlsFromDB.setProductTags(productTag);
          productDtlsRepository.save(productDtlsFromDB);
        } else {
          productDtls.setInventoryDtls(inventoryDtls);
          productDtls.setProductTags(productTag);
          productDtlsRepository.save(productDtls);
        }
        counter++;
        if (counter % 50 == 0) {
          inventoryDtlsRepository.flush();
          productDtlsRepository.flush();
        }
      }
    }
    return counter;
  }



  private Set<ProductTag> prepareProductTag(ImportInventoryDetailDto importInventoryDetailDto) {
    Set<ProductTag> tags = new HashSet<ProductTag>();
    ProductTag productTag = null;
    if (StringUtils.isNotBlank(importInventoryDetailDto.getPrdtTagId())) {
      List<String> tagNames = Arrays.asList(importInventoryDetailDto.getPrdtTagId().split(","));
      for (String name : tagNames) {
        ProductTagDTO productTagDto = new ProductTagDTO();
        productTagDto.setTagName(name.trim().toLowerCase());
        productTagDto.setTagDescription(name.trim().toLowerCase());
        productTag = productTagMapper.toEntity(productTagDto);
        ProductTag localProductTag = productTagRepository.findByTagName(name.trim().toLowerCase());

        if (null == localProductTag) {
          productTag = productTagRepository.save(productTagMapper.toEntity(productTagDto));
        } else {
          productTag = localProductTag;
        }
        tags.add(productTag);
      }

      importInventoryDetailDto.setPrdtTagId(productTag.getId().toString());
    }
    return tags;
  }

  @Override
  @Transactional
  public void prepareProductUserColorRating(Long prdtId) {

    ProductDtls productDtls = productDtlsRepository.findOne(prdtId);
    List<ProductColor> productColors = getProductColorDetailsList(productDtls);
    productColorRepository.save(productColors);
    productRatingRepository.save(getProductRatingDetailsList(productDtls, productColors));
  }

  @Override
  @Scheduled(cron = "0 00 22 * * ?") // every data at 10 PM
  public void prepareAllProductUserColorRating() {

    List<Long> productIdsList = productDtlsRepository.findAll().stream().map(item -> item.getId())
        .collect(Collectors.toList());
    for (Long prdtId : productIdsList) {
      prepareProductUserColorRating(prdtId);
    }
  }

  /**
   * prepares dummy product color review if no data found for any color position
   * 
   * @param productDtls
   * @return
   */
  private List<ProductColor> getProductColorDetailsList(ProductDtls productDtls) {
    List<Integer> possibleColorPositions = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    List<Integer> possibleColorMoodLevel = Arrays.asList(1, 2, 3, 4);
    List<ProductColor> productColors =
        productColorRepository.findByPrdtId(BigDecimal.valueOf(productDtls.getId()));

    possibleColorPositions.forEach(position -> {
      possibleColorMoodLevel.forEach(moodLevel -> {
        if (productColors.stream().noneMatch(
            item -> Integer.valueOf(item.getColorMoodLevel().toString()).equals(moodLevel)
                && Integer.valueOf(item.getColorPosition().toString()).equals(position))) {
          // prepare ProductColor
          ProductColor productColor = new ProductColor();
          productColor.prdtId(BigDecimal.valueOf(productDtls.getId()))
              .colorPosition(BigDecimal.valueOf(position)).userRating(0.0)
              .colorMoodLevel(BigDecimal.valueOf(moodLevel));
          productColors.add(productColor);
        }
      });
    });
    return productColors;
  }


  /**
   * This method called after product color rating is filled with valid details
   * 
   * @param productDtls
   * @param productColors
   * @return
   */
  private List<ProductRating> getProductRatingDetailsList(ProductDtls productDtls,
      List<ProductColor> productColors) {
    List<ProductRating> productRatingsFromDB =
        productRatingRepository.findByPrdtId(BigDecimal.valueOf(productDtls.getId()));

    List<ProductRating> productRatings = new ArrayList<ProductRating>();

    // group by product color position
    productColors.stream().collect(Collectors.groupingBy(item -> item.getColorPosition()))
        .forEach((colorPosition, prdtColorsForPosition) -> {
          // further group by product mood level
          prdtColorsForPosition.stream()
              .collect(Collectors.groupingBy(item -> item.getColorMoodLevel()))
              .forEach((moodLevel, prdtColorsForMoodLevel) -> {
                ProductRating productRating = new ProductRating();
                productRating.prdtId(BigDecimal.valueOf(productDtls.getId()))
                    .userRating(prdtColorsForMoodLevel.stream()
                        .mapToDouble(item -> item.getUserRating()).average().getAsDouble())
                    .colorPosition(colorPosition).colorMoodLevel(moodLevel)
                    .userReviewCount(Double.valueOf(prdtColorsForMoodLevel.size()))
                    // find id from the list of DB records
                    .id(productRatingsFromDB.stream()
                        .filter(item -> item.getColorMoodLevel().equals(moodLevel)
                            && item.getColorPosition().equals(colorPosition))
                        .map(item -> item.getId()).findFirst().orElse(null));
                productRatings.add(productRating);
              });
        });


    return productRatings;

  }

}
