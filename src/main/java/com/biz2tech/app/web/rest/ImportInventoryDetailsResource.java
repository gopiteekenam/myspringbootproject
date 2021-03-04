package com.biz2tech.app.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biz2tech.app.security.AuthoritiesConstants;
import com.biz2tech.app.service.ImportService;
import com.biz2tech.app.service.dto.ImportInventoryDetailDto;
import com.biz2tech.app.web.rest.errors.CustomParameterizedException;
import com.biz2tech.app.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;


/**
 * REST controller for managing DropShipDtls.
 */
@RestController
@RequestMapping("/api")
public class ImportInventoryDetailsResource {

  private final Logger log = LoggerFactory.getLogger(ImportInventoryDetailsResource.class);


  private final ImportService importService;

  public ImportInventoryDetailsResource(ImportService dropShipDtlsService) {
    this.importService = dropShipDtlsService;
  }

  @PostMapping("/import-data")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<String> importInventoryData(
      @RequestBody final List<ImportInventoryDetailDto> importInventoryDetailDtoList,
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {
    log.debug("REST request to import  importInventoryDetailsDto : {}",
        importInventoryDetailDtoList.toString());
    if (CollectionUtils.isEmpty(importInventoryDetailDtoList)) {
      throw new CustomParameterizedException("Nothing to import");
    }
    Long result = importService.importData(importInventoryDetailDtoList);
    return ResponseEntity.created(new URI("/api/import-data/" + result))
        .headers(HeaderUtil.createAlert("Imported {} records successfully", result.toString()))
        .body(result.toString());
  }

  @GetMapping("/prepare-product-user-review-by-id")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<String> prepareProductUserColorRatingByProductId(
      @RequestParam("prdtId") Long prdtId, @RequestHeader("Authorization") String authorization)
      throws URISyntaxException {

    importService.prepareProductUserColorRating(prdtId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/prepare-product-user-review")
  @Secured(value = {AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN})
  @Timed
  public ResponseEntity<String> prepareProductUserColorRating(
      @RequestHeader("Authorization") String authorization) throws URISyntaxException {

    importService.prepareAllProductUserColorRating();
    return ResponseEntity.ok().build();
  }

}
