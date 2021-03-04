package com.biz2tech.app.service;

import java.util.List;

import com.biz2tech.app.service.dto.ImportInventoryDetailDto;

public interface ImportService {


  public Long importData(List<ImportInventoryDetailDto> importInventoryDetailDtoList);

  void prepareProductUserColorRating(Long prdtId);

  void prepareAllProductUserColorRating();
}
