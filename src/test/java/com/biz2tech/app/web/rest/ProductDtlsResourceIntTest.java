package com.biz2tech.app.web.rest;

import static com.biz2tech.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.biz2tech.app.FragrancenetserviceApp;
import com.biz2tech.app.domain.ProductDtls;
import com.biz2tech.app.domain.enumeration.SizeCapacityUnit;
import com.biz2tech.app.domain.enumeration.SizeMeasurementUnit;
import com.biz2tech.app.repository.ProductDtlsRepository;
import com.biz2tech.app.service.ProductDtlsService;
import com.biz2tech.app.service.dto.ProductDtlsDTO;
import com.biz2tech.app.service.mapper.ProductDtlsMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the ProductDtlsResource REST controller.
 *
 * @see ProductDtlsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class ProductDtlsResourceIntTest {

  private static final String DEFAULT_PRDT_TITLE = "AAAAAAAAAA";
  private static final String UPDATED_PRDT_TITLE = "BBBBBBBBBB";

  private static final String DEFAULT_PRDT_DESC = "AAAAAAAAAA";
  private static final String UPDATED_PRDT_DESC = "BBBBBBBBBB";

  private static final String DEFAULT_DETAILED_SPEC = "AAAAAAAAAA";
  private static final String UPDATED_DETAILED_SPEC = "BBBBBBBBBB";

  private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
  private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

  private static final String DEFAULT_BRAND_NAME = "AAAAAAAAAA";
  private static final String UPDATED_BRAND_NAME = "BBBBBBBBBB";

  private static final BigDecimal DEFAULT_PRDT_TAG_ID = new BigDecimal(1);
  private static final BigDecimal UPDATED_PRDT_TAG_ID = new BigDecimal(2);

  private static final Double DEFAULT_BASE_PRICE = 1D;
  private static final Double UPDATED_BASE_PRICE = 2D;

  private static final Double DEFAULT_MARKED_PERCENTAGE = 1D;
  private static final Double UPDATED_MARKED_PERCENTAGE = 2D;

  private static final Double DEFAULT_SELL_PRICE = 1D;
  private static final Double UPDATED_SELL_PRICE = 2D;

  private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
  private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

  private static final SizeCapacityUnit DEFAULT_SIZE = SizeCapacityUnit.SMALL;
  private static final SizeCapacityUnit UPDATED_SIZE = SizeCapacityUnit.MEDIUM;

  private static final SizeMeasurementUnit DEFAULT_SIZE_UNIT = SizeMeasurementUnit.ML;
  private static final SizeMeasurementUnit UPDATED_SIZE_UNIT = SizeMeasurementUnit.GMS;

  private static final String DEFAULT_SIZE_VARIENT_CODE = "AAAAAAAAAA";
  private static final String UPDATED_SIZE_VARIENT_CODE = "BBBBBBBBBB";

  private static final String DEFAULT_COLOR = "AAAAAAAAAA";
  private static final String UPDATED_COLOR = "BBBBBBBBBB";

  private static final Boolean DEFAULT_IS_DROP_SHIP = false;
  private static final Boolean UPDATED_IS_DROP_SHIP = true;

  private static final Boolean DEFAULT_IS_CLEARENCE = false;
  private static final Boolean UPDATED_IS_CLEARENCE = true;

  private static final Boolean DEFAULT_IS_DISCONTINUED = false;
  private static final Boolean UPDATED_IS_DISCONTINUED = true;

  private static final Boolean DEFAULT_IS_SALE = false;
  private static final Boolean UPDATED_IS_SALE = true;

  private static final Boolean DEFAULT_IS_PREMIUM = false;
  private static final Boolean UPDATED_IS_PREMIUM = true;

  private static final String DEFAULT_PRDT_TYPE = "AAAAAAAAAA";
  private static final String UPDATED_PRDT_TYPE = "BBBBBBBBBB";

  private static final String DEFAULT_PRDT_CATEGORY = "AAAAAAAAAA";
  private static final String UPDATED_PRDT_CATEGORY = "BBBBBBBBBB";

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";


  private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_CREATED_ON = Instant.now();

  private static final Instant DEFAULT_LAST_UPDATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_LAST_UPDATED_ON = Instant.now();
  @Autowired
  private ProductDtlsRepository productDtlsRepository;

  @Autowired
  private ProductDtlsMapper productDtlsMapper;

  @Autowired
  private ProductDtlsService productDtlsService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restProductDtlsMockMvc;

  private ProductDtls productDtls;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final ProductDtlsResource productDtlsResource = new ProductDtlsResource(productDtlsService);
    this.restProductDtlsMockMvc = MockMvcBuilders.standaloneSetup(productDtlsResource)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();
  }

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it, if they test an entity
   * which requires the current entity.
   */
  public static ProductDtls createEntity(EntityManager em) {
    ProductDtls productDtls = new ProductDtls().prdtTitle(DEFAULT_PRDT_TITLE)
        .prdtDesc(DEFAULT_PRDT_DESC).detailedSpec(DEFAULT_DETAILED_SPEC).imageURL(DEFAULT_IMAGE_URL)
        .brandName(DEFAULT_BRAND_NAME).basePrice(DEFAULT_BASE_PRICE)
        .markedPercentage(DEFAULT_MARKED_PERCENTAGE).sellPrice(DEFAULT_SELL_PRICE)
        .currencyCode(DEFAULT_CURRENCY_CODE).size(DEFAULT_SIZE).sizeUnit(DEFAULT_SIZE_UNIT)
        .sizeVarientCode(DEFAULT_SIZE_VARIENT_CODE).colorRating(DEFAULT_COLOR)
        .isDropShip(DEFAULT_IS_DROP_SHIP).isClearence(DEFAULT_IS_CLEARENCE)
        .isDiscontinued(DEFAULT_IS_DISCONTINUED).isSale(DEFAULT_IS_SALE)
        .isPremium(DEFAULT_IS_PREMIUM).prdtType(DEFAULT_PRDT_TYPE)
        .prdtCategory(DEFAULT_PRDT_CATEGORY);

    productDtls.setCreatedBy(DEFAULT_CREATED_BY);
    productDtls.setCreatedOn(DEFAULT_CREATED_ON);
    productDtls.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    productDtls.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);


    return productDtls;
  }

  @Before
  public void initTest() {
    productDtls = createEntity(em);
  }

  @Test
  @Transactional
  public void createProductDtls() throws Exception {
    int databaseSizeBeforeCreate = productDtlsRepository.findAll().size();

    // Create the ProductDtls
    ProductDtlsDTO productDtlsDTO = productDtlsMapper.toDto(productDtls);
    restProductDtlsMockMvc
        .perform(post("/api/product-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDtlsDTO)))
        .andExpect(status().isCreated());

    // Validate the ProductDtls in the database
    List<ProductDtls> productDtlsList = productDtlsRepository.findAll();
    assertThat(productDtlsList).hasSize(databaseSizeBeforeCreate + 1);
    ProductDtls testProductDtls = productDtlsList.get(productDtlsList.size() - 1);
    assertThat(testProductDtls.getPrdtTitle()).isEqualTo(DEFAULT_PRDT_TITLE);
    assertThat(testProductDtls.getPrdtDesc()).isEqualTo(DEFAULT_PRDT_DESC);
    assertThat(testProductDtls.getDetailedSpec()).isEqualTo(DEFAULT_DETAILED_SPEC);
    assertThat(testProductDtls.getImageURL()).isEqualTo(DEFAULT_IMAGE_URL);
    assertThat(testProductDtls.getBrandName()).isEqualTo(DEFAULT_BRAND_NAME);
    assertThat(testProductDtls.getBasePrice()).isEqualTo(DEFAULT_BASE_PRICE);
    assertThat(testProductDtls.getMarkedPercentage()).isEqualTo(DEFAULT_MARKED_PERCENTAGE);
    assertThat(testProductDtls.getSellPrice()).isEqualTo(DEFAULT_SELL_PRICE);
    assertThat(testProductDtls.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
    assertThat(testProductDtls.getSize()).isEqualTo(DEFAULT_SIZE);
    assertThat(testProductDtls.getSizeUnit()).isEqualTo(DEFAULT_SIZE_UNIT);
    assertThat(testProductDtls.getSizeVarientCode()).isEqualTo(DEFAULT_SIZE_VARIENT_CODE);
    assertThat(testProductDtls.getColorRating()).isEqualTo(DEFAULT_COLOR);
    assertThat(testProductDtls.isIsDropShip()).isEqualTo(DEFAULT_IS_DROP_SHIP);
    assertThat(testProductDtls.isIsClearence()).isEqualTo(DEFAULT_IS_CLEARENCE);
    assertThat(testProductDtls.isIsDiscontinued()).isEqualTo(DEFAULT_IS_DISCONTINUED);
    assertThat(testProductDtls.isIsSale()).isEqualTo(DEFAULT_IS_SALE);
    assertThat(testProductDtls.isIsPremium()).isEqualTo(DEFAULT_IS_PREMIUM);
    assertThat(testProductDtls.getPrdtType()).isEqualTo(DEFAULT_PRDT_TYPE);
    assertThat(testProductDtls.getPrdtCategory()).isEqualTo(DEFAULT_PRDT_CATEGORY);
    assertThat(testProductDtls.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testProductDtls.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    assertThat(testProductDtls.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    assertThat(testProductDtls.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void createProductDtlsWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = productDtlsRepository.findAll().size();

    // Create the ProductDtls with an existing ID
    productDtls.setId(1L);
    ProductDtlsDTO productDtlsDTO = productDtlsMapper.toDto(productDtls);

    // An entity with an existing ID cannot be created, so this API call must fail
    restProductDtlsMockMvc
        .perform(post("/api/product-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDtlsDTO)))
        .andExpect(status().isBadRequest());

    // Validate the ProductDtls in the database
    List<ProductDtls> productDtlsList = productDtlsRepository.findAll();
    assertThat(productDtlsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllProductDtls() throws Exception {
    // Initialize the database
    productDtlsRepository.saveAndFlush(productDtls);

    // Get all the productDtlsList
    restProductDtlsMockMvc.perform(get("/api/product-dtls?sort=id,desc")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(productDtls.getId().intValue())))
        .andExpect(jsonPath("$.[*].prdtTitle").value(hasItem(DEFAULT_PRDT_TITLE.toString())))
        .andExpect(jsonPath("$.[*].prdtDesc").value(hasItem(DEFAULT_PRDT_DESC.toString())))
        .andExpect(jsonPath("$.[*].detailedSpec").value(hasItem(DEFAULT_DETAILED_SPEC.toString())))
        .andExpect(jsonPath("$.[*].imageURL").value(hasItem(DEFAULT_IMAGE_URL.toString())))
        .andExpect(jsonPath("$.[*].brandName").value(hasItem(DEFAULT_BRAND_NAME.toString())))
        .andExpect(jsonPath("$.[*].prdtTagId").value(hasItem(DEFAULT_PRDT_TAG_ID.intValue())))
        .andExpect(jsonPath("$.[*].basePrice").value(hasItem(DEFAULT_BASE_PRICE.doubleValue())))
        .andExpect(jsonPath("$.[*].markedPercentage")
            .value(hasItem(DEFAULT_MARKED_PERCENTAGE.doubleValue())))
        .andExpect(jsonPath("$.[*].sellPrice").value(hasItem(DEFAULT_SELL_PRICE.doubleValue())))
        .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE.toString())))
        .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())))
        .andExpect(jsonPath("$.[*].sizeUnit").value(hasItem(DEFAULT_SIZE_UNIT.toString())))
        .andExpect(
            jsonPath("$.[*].sizeVarientCode").value(hasItem(DEFAULT_SIZE_VARIENT_CODE.toString())))
        .andExpect(jsonPath("$.[*].color").value(hasItem(DEFAULT_COLOR.toString())))
        .andExpect(jsonPath("$.[*].isDropShip").value(hasItem(DEFAULT_IS_DROP_SHIP.booleanValue())))
        .andExpect(
            jsonPath("$.[*].isClearence").value(hasItem(DEFAULT_IS_CLEARENCE.booleanValue())))
        .andExpect(
            jsonPath("$.[*].isDiscontinued").value(hasItem(DEFAULT_IS_DISCONTINUED.booleanValue())))
        .andExpect(jsonPath("$.[*].isSale").value(hasItem(DEFAULT_IS_SALE.booleanValue())))
        .andExpect(jsonPath("$.[*].isPremium").value(hasItem(DEFAULT_IS_PREMIUM.booleanValue())))
        .andExpect(jsonPath("$.[*].prdtType").value(hasItem(DEFAULT_PRDT_TYPE.toString())))
        .andExpect(jsonPath("$.[*].prdtCategory").value(hasItem(DEFAULT_PRDT_CATEGORY.toString())))
        .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
        .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
  }

  @Test
  @Transactional
  public void getProductDtls() throws Exception {
    // Initialize the database
    productDtlsRepository.saveAndFlush(productDtls);

    // Get the productDtls
    restProductDtlsMockMvc.perform(get("/api/product-dtls/{id}", productDtls.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(productDtls.getId().intValue()))
        .andExpect(jsonPath("$.prdtTitle").value(DEFAULT_PRDT_TITLE.toString()))
        .andExpect(jsonPath("$.prdtDesc").value(DEFAULT_PRDT_DESC.toString()))
        .andExpect(jsonPath("$.detailedSpec").value(DEFAULT_DETAILED_SPEC.toString()))
        .andExpect(jsonPath("$.imageURL").value(DEFAULT_IMAGE_URL.toString()))
        .andExpect(jsonPath("$.brandName").value(DEFAULT_BRAND_NAME.toString()))
        .andExpect(jsonPath("$.prdtTagId").value(DEFAULT_PRDT_TAG_ID.intValue()))
        .andExpect(jsonPath("$.basePrice").value(DEFAULT_BASE_PRICE.doubleValue()))
        .andExpect(jsonPath("$.markedPercentage").value(DEFAULT_MARKED_PERCENTAGE.doubleValue()))
        .andExpect(jsonPath("$.sellPrice").value(DEFAULT_SELL_PRICE.doubleValue()))
        .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE.toString()))
        .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()))
        .andExpect(jsonPath("$.sizeUnit").value(DEFAULT_SIZE_UNIT.toString()))
        .andExpect(jsonPath("$.sizeVarientCode").value(DEFAULT_SIZE_VARIENT_CODE.toString()))
        .andExpect(jsonPath("$.color").value(DEFAULT_COLOR.toString()))
        .andExpect(jsonPath("$.isDropShip").value(DEFAULT_IS_DROP_SHIP.booleanValue()))
        .andExpect(jsonPath("$.isClearence").value(DEFAULT_IS_CLEARENCE.booleanValue()))
        .andExpect(jsonPath("$.isDiscontinued").value(DEFAULT_IS_DISCONTINUED.booleanValue()))
        .andExpect(jsonPath("$.isSale").value(DEFAULT_IS_SALE.booleanValue()))
        .andExpect(jsonPath("$.isPremium").value(DEFAULT_IS_PREMIUM.booleanValue()))
        .andExpect(jsonPath("$.prdtType").value(DEFAULT_PRDT_TYPE.toString()))
        .andExpect(jsonPath("$.prdtCategory").value(DEFAULT_PRDT_CATEGORY.toString()))
        .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
        .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
        .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
        .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingProductDtls() throws Exception {
    // Get the productDtls
    restProductDtlsMockMvc.perform(get("/api/product-dtls/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateProductDtls() throws Exception {
    // Initialize the database
    productDtlsRepository.saveAndFlush(productDtls);
    int databaseSizeBeforeUpdate = productDtlsRepository.findAll().size();

    // Update the productDtls
    ProductDtls updatedProductDtls = productDtlsRepository.findOne(productDtls.getId());
    // Disconnect from session so that the updates on updatedProductDtls are not directly saved in
    // db
    em.detach(updatedProductDtls);
    updatedProductDtls.prdtTitle(UPDATED_PRDT_TITLE).prdtDesc(UPDATED_PRDT_DESC)
        .detailedSpec(UPDATED_DETAILED_SPEC).imageURL(UPDATED_IMAGE_URL)
        .brandName(UPDATED_BRAND_NAME).basePrice(UPDATED_BASE_PRICE)
        .markedPercentage(UPDATED_MARKED_PERCENTAGE).sellPrice(UPDATED_SELL_PRICE)
        .currencyCode(UPDATED_CURRENCY_CODE).size(UPDATED_SIZE).sizeUnit(UPDATED_SIZE_UNIT)
        .sizeVarientCode(UPDATED_SIZE_VARIENT_CODE).colorRating(UPDATED_COLOR)
        .isDropShip(UPDATED_IS_DROP_SHIP).isClearence(UPDATED_IS_CLEARENCE)
        .isDiscontinued(UPDATED_IS_DISCONTINUED).isSale(UPDATED_IS_SALE)
        .isPremium(UPDATED_IS_PREMIUM).prdtType(UPDATED_PRDT_TYPE)
        .prdtCategory(UPDATED_PRDT_CATEGORY);

    updatedProductDtls.setCreatedBy(DEFAULT_CREATED_BY);
    updatedProductDtls.setCreatedOn(DEFAULT_CREATED_ON);
    updatedProductDtls.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    updatedProductDtls.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);

    ProductDtlsDTO productDtlsDTO = productDtlsMapper.toDto(updatedProductDtls);

    restProductDtlsMockMvc
        .perform(put("/api/product-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDtlsDTO)))
        .andExpect(status().isOk());

    // Validate the ProductDtls in the database
    List<ProductDtls> productDtlsList = productDtlsRepository.findAll();
    assertThat(productDtlsList).hasSize(databaseSizeBeforeUpdate);
    ProductDtls testProductDtls = productDtlsList.get(productDtlsList.size() - 1);
    assertThat(testProductDtls.getPrdtTitle()).isEqualTo(UPDATED_PRDT_TITLE);
    assertThat(testProductDtls.getPrdtDesc()).isEqualTo(UPDATED_PRDT_DESC);
    assertThat(testProductDtls.getDetailedSpec()).isEqualTo(UPDATED_DETAILED_SPEC);
    assertThat(testProductDtls.getImageURL()).isEqualTo(UPDATED_IMAGE_URL);
    assertThat(testProductDtls.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
    assertThat(testProductDtls.getBasePrice()).isEqualTo(UPDATED_BASE_PRICE);
    assertThat(testProductDtls.getMarkedPercentage()).isEqualTo(UPDATED_MARKED_PERCENTAGE);
    assertThat(testProductDtls.getSellPrice()).isEqualTo(UPDATED_SELL_PRICE);
    assertThat(testProductDtls.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
    assertThat(testProductDtls.getSize()).isEqualTo(UPDATED_SIZE);
    assertThat(testProductDtls.getSizeUnit()).isEqualTo(UPDATED_SIZE_UNIT);
    assertThat(testProductDtls.getSizeVarientCode()).isEqualTo(UPDATED_SIZE_VARIENT_CODE);
    assertThat(testProductDtls.getColorRating()).isEqualTo(UPDATED_COLOR);
    assertThat(testProductDtls.isIsDropShip()).isEqualTo(UPDATED_IS_DROP_SHIP);
    assertThat(testProductDtls.isIsClearence()).isEqualTo(UPDATED_IS_CLEARENCE);
    assertThat(testProductDtls.isIsDiscontinued()).isEqualTo(UPDATED_IS_DISCONTINUED);
    assertThat(testProductDtls.isIsSale()).isEqualTo(UPDATED_IS_SALE);
    assertThat(testProductDtls.isIsPremium()).isEqualTo(UPDATED_IS_PREMIUM);
    assertThat(testProductDtls.getPrdtType()).isEqualTo(UPDATED_PRDT_TYPE);
    assertThat(testProductDtls.getPrdtCategory()).isEqualTo(UPDATED_PRDT_CATEGORY);
    assertThat(testProductDtls.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testProductDtls.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    assertThat(testProductDtls.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    assertThat(testProductDtls.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void updateNonExistingProductDtls() throws Exception {
    int databaseSizeBeforeUpdate = productDtlsRepository.findAll().size();

    // Create the ProductDtls
    ProductDtlsDTO productDtlsDTO = productDtlsMapper.toDto(productDtls);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restProductDtlsMockMvc
        .perform(put("/api/product-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productDtlsDTO)))
        .andExpect(status().isCreated());

    // Validate the ProductDtls in the database
    List<ProductDtls> productDtlsList = productDtlsRepository.findAll();
    assertThat(productDtlsList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteProductDtls() throws Exception {
    // Initialize the database
    productDtlsRepository.saveAndFlush(productDtls);
    int databaseSizeBeforeDelete = productDtlsRepository.findAll().size();

    // Get the productDtls
    restProductDtlsMockMvc.perform(delete("/api/product-dtls/{id}", productDtls.getId())
        .accept(TestUtil.APPLICATION_JSON_UTF8)).andExpect(status().isOk());

    // Validate the database is empty
    List<ProductDtls> productDtlsList = productDtlsRepository.findAll();
    assertThat(productDtlsList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(ProductDtls.class);
    ProductDtls productDtls1 = new ProductDtls();
    productDtls1.setId(1L);
    ProductDtls productDtls2 = new ProductDtls();
    productDtls2.setId(productDtls1.getId());
    assertThat(productDtls1).isEqualTo(productDtls2);
    productDtls2.setId(2L);
    assertThat(productDtls1).isNotEqualTo(productDtls2);
    productDtls1.setId(null);
    assertThat(productDtls1).isNotEqualTo(productDtls2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(ProductDtlsDTO.class);
    ProductDtlsDTO productDtlsDTO1 = new ProductDtlsDTO();
    productDtlsDTO1.setId(1L);
    ProductDtlsDTO productDtlsDTO2 = new ProductDtlsDTO();
    assertThat(productDtlsDTO1).isNotEqualTo(productDtlsDTO2);
    productDtlsDTO2.setId(productDtlsDTO1.getId());
    assertThat(productDtlsDTO1).isEqualTo(productDtlsDTO2);
    productDtlsDTO2.setId(2L);
    assertThat(productDtlsDTO1).isNotEqualTo(productDtlsDTO2);
    productDtlsDTO1.setId(null);
    assertThat(productDtlsDTO1).isNotEqualTo(productDtlsDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(productDtlsMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(productDtlsMapper.fromId(null)).isNull();
  }
}
