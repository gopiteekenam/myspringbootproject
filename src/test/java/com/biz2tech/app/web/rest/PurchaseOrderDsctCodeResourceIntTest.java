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
import com.biz2tech.app.domain.PurchaseOrderDsctCode;
import com.biz2tech.app.repository.PurchaseOrderDsctCodeRepository;
import com.biz2tech.app.service.PurchaseOrderDsctCodeService;
import com.biz2tech.app.service.dto.PurchaseOrderDsctCodeDTO;
import com.biz2tech.app.service.mapper.PurchaseOrderDsctCodeMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the PurchaseOrderDsctCodeResource REST controller.
 *
 * @see PurchaseOrderDsctCodeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class PurchaseOrderDsctCodeResourceIntTest {

  private static final String DEFAULT_CODE = "AAAAAAAAAA";
  private static final String UPDATED_CODE = "BBBBBBBBBB";

  private static final Double DEFAULT_DISCOUNT_PERCENTAGE = 1D;
  private static final Double UPDATED_DISCOUNT_PERCENTAGE = 2D;

  private static final Instant DEFAULT_VALID_FROM = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_VALID_FROM = Instant.now();

  private static final Instant DEFAULT_VALID_TO = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_VALID_TO = Instant.now();

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";


  private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_CREATED_ON = Instant.now();

  private static final Instant DEFAULT_LAST_UPDATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_LAST_UPDATED_ON = Instant.now();
  @Autowired
  private PurchaseOrderDsctCodeRepository purchaseOrderDsctCodeRepository;

  @Autowired
  private PurchaseOrderDsctCodeMapper purchaseOrderDsctCodeMapper;

  @Autowired
  private PurchaseOrderDsctCodeService purchaseOrderDsctCodeService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restPurchaseOrderDsctCodeMockMvc;

  private PurchaseOrderDsctCode purchaseOrderDsctCode;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final PurchaseOrderDsctCodeResource purchaseOrderDsctCodeResource =
        new PurchaseOrderDsctCodeResource(purchaseOrderDsctCodeService);
    this.restPurchaseOrderDsctCodeMockMvc =
        MockMvcBuilders.standaloneSetup(purchaseOrderDsctCodeResource)
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
  public static PurchaseOrderDsctCode createEntity(EntityManager em) {
    PurchaseOrderDsctCode purchaseOrderDsctCode = new PurchaseOrderDsctCode().code(DEFAULT_CODE)
        .discountPercentage(DEFAULT_DISCOUNT_PERCENTAGE).validFrom(DEFAULT_VALID_FROM)
        .validTo(DEFAULT_VALID_TO);

    purchaseOrderDsctCode.setCreatedBy(DEFAULT_CREATED_BY);
    purchaseOrderDsctCode.setCreatedOn(DEFAULT_CREATED_ON);
    purchaseOrderDsctCode.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    purchaseOrderDsctCode.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
    return purchaseOrderDsctCode;
  }

  @Before
  public void initTest() {
    purchaseOrderDsctCode = createEntity(em);
  }

  @Test
  @Transactional
  public void createPurchaseOrderDsctCode() throws Exception {
    int databaseSizeBeforeCreate = purchaseOrderDsctCodeRepository.findAll().size();

    // Create the PurchaseOrderDsctCode
    PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO =
        purchaseOrderDsctCodeMapper.toDto(purchaseOrderDsctCode);
    restPurchaseOrderDsctCodeMockMvc
        .perform(post("/api/purchase-order-dsct-codes").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDsctCodeDTO)))
        .andExpect(status().isCreated());

    // Validate the PurchaseOrderDsctCode in the database
    List<PurchaseOrderDsctCode> purchaseOrderDsctCodeList =
        purchaseOrderDsctCodeRepository.findAll();
    assertThat(purchaseOrderDsctCodeList).hasSize(databaseSizeBeforeCreate + 1);
    PurchaseOrderDsctCode testPurchaseOrderDsctCode =
        purchaseOrderDsctCodeList.get(purchaseOrderDsctCodeList.size() - 1);
    assertThat(testPurchaseOrderDsctCode.getCode()).isEqualTo(DEFAULT_CODE);
    assertThat(testPurchaseOrderDsctCode.getDiscountPercentage())
        .isEqualTo(DEFAULT_DISCOUNT_PERCENTAGE);
    assertThat(testPurchaseOrderDsctCode.getValidFrom()).isEqualTo(DEFAULT_VALID_FROM);
    assertThat(testPurchaseOrderDsctCode.getValidTo()).isEqualTo(DEFAULT_VALID_TO);
    assertThat(testPurchaseOrderDsctCode.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testPurchaseOrderDsctCode.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    assertThat(testPurchaseOrderDsctCode.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    assertThat(testPurchaseOrderDsctCode.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void createPurchaseOrderDsctCodeWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = purchaseOrderDsctCodeRepository.findAll().size();

    // Create the PurchaseOrderDsctCode with an existing ID
    purchaseOrderDsctCode.setId(1L);
    PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO =
        purchaseOrderDsctCodeMapper.toDto(purchaseOrderDsctCode);

    // An entity with an existing ID cannot be created, so this API call must fail
    restPurchaseOrderDsctCodeMockMvc
        .perform(post("/api/purchase-order-dsct-codes").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDsctCodeDTO)))
        .andExpect(status().isBadRequest());

    // Validate the PurchaseOrderDsctCode in the database
    List<PurchaseOrderDsctCode> purchaseOrderDsctCodeList =
        purchaseOrderDsctCodeRepository.findAll();
    assertThat(purchaseOrderDsctCodeList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllPurchaseOrderDsctCodes() throws Exception {
    // Initialize the database
    purchaseOrderDsctCodeRepository.saveAndFlush(purchaseOrderDsctCode);

    // Get all the purchaseOrderDsctCodeList
    restPurchaseOrderDsctCodeMockMvc.perform(get("/api/purchase-order-dsct-codes?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(purchaseOrderDsctCode.getId().intValue())))
        .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
        .andExpect(jsonPath("$.[*].discountPercentage")
            .value(hasItem(DEFAULT_DISCOUNT_PERCENTAGE.doubleValue())))
        .andExpect(jsonPath("$.[*].validFrom").value(hasItem(DEFAULT_VALID_FROM.toString())))
        .andExpect(jsonPath("$.[*].validTo").value(hasItem(DEFAULT_VALID_TO.toString())))
        .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
        .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
  }

  @Test
  @Transactional
  public void getPurchaseOrderDsctCode() throws Exception {
    // Initialize the database
    purchaseOrderDsctCodeRepository.saveAndFlush(purchaseOrderDsctCode);

    // Get the purchaseOrderDsctCode
    restPurchaseOrderDsctCodeMockMvc
        .perform(get("/api/purchase-order-dsct-codes/{id}", purchaseOrderDsctCode.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(purchaseOrderDsctCode.getId().intValue()))
        .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
        .andExpect(
            jsonPath("$.discountPercentage").value(DEFAULT_DISCOUNT_PERCENTAGE.doubleValue()))
        .andExpect(jsonPath("$.validFrom").value(DEFAULT_VALID_FROM.toString()))
        .andExpect(jsonPath("$.validTo").value(DEFAULT_VALID_TO.toString()))
        .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
        .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
        .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
        .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingPurchaseOrderDsctCode() throws Exception {
    // Get the purchaseOrderDsctCode
    restPurchaseOrderDsctCodeMockMvc
        .perform(get("/api/purchase-order-dsct-codes/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updatePurchaseOrderDsctCode() throws Exception {
    // Initialize the database
    purchaseOrderDsctCodeRepository.saveAndFlush(purchaseOrderDsctCode);
    int databaseSizeBeforeUpdate = purchaseOrderDsctCodeRepository.findAll().size();

    // Update the purchaseOrderDsctCode
    PurchaseOrderDsctCode updatedPurchaseOrderDsctCode =
        purchaseOrderDsctCodeRepository.findOne(purchaseOrderDsctCode.getId());
    // Disconnect from session so that the updates on updatedPurchaseOrderDsctCode are not directly
    // saved in db
    em.detach(updatedPurchaseOrderDsctCode);
    updatedPurchaseOrderDsctCode.code(UPDATED_CODE).discountPercentage(UPDATED_DISCOUNT_PERCENTAGE)
        .validFrom(UPDATED_VALID_FROM).validTo(UPDATED_VALID_TO);


    updatedPurchaseOrderDsctCode.setCreatedBy(DEFAULT_CREATED_BY);
    updatedPurchaseOrderDsctCode.setCreatedOn(DEFAULT_CREATED_ON);
    updatedPurchaseOrderDsctCode.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    updatedPurchaseOrderDsctCode.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
    PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO =
        purchaseOrderDsctCodeMapper.toDto(updatedPurchaseOrderDsctCode);

    restPurchaseOrderDsctCodeMockMvc
        .perform(put("/api/purchase-order-dsct-codes").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDsctCodeDTO)))
        .andExpect(status().isOk());

    // Validate the PurchaseOrderDsctCode in the database
    List<PurchaseOrderDsctCode> purchaseOrderDsctCodeList =
        purchaseOrderDsctCodeRepository.findAll();
    assertThat(purchaseOrderDsctCodeList).hasSize(databaseSizeBeforeUpdate);
    PurchaseOrderDsctCode testPurchaseOrderDsctCode =
        purchaseOrderDsctCodeList.get(purchaseOrderDsctCodeList.size() - 1);
    assertThat(testPurchaseOrderDsctCode.getCode()).isEqualTo(UPDATED_CODE);
    assertThat(testPurchaseOrderDsctCode.getDiscountPercentage())
        .isEqualTo(UPDATED_DISCOUNT_PERCENTAGE);
    assertThat(testPurchaseOrderDsctCode.getValidFrom()).isEqualTo(UPDATED_VALID_FROM);
    assertThat(testPurchaseOrderDsctCode.getValidTo()).isEqualTo(UPDATED_VALID_TO);
    assertThat(testPurchaseOrderDsctCode.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testPurchaseOrderDsctCode.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    assertThat(testPurchaseOrderDsctCode.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    assertThat(testPurchaseOrderDsctCode.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void updateNonExistingPurchaseOrderDsctCode() throws Exception {
    int databaseSizeBeforeUpdate = purchaseOrderDsctCodeRepository.findAll().size();

    // Create the PurchaseOrderDsctCode
    PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO =
        purchaseOrderDsctCodeMapper.toDto(purchaseOrderDsctCode);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restPurchaseOrderDsctCodeMockMvc
        .perform(put("/api/purchase-order-dsct-codes").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(purchaseOrderDsctCodeDTO)))
        .andExpect(status().isCreated());

    // Validate the PurchaseOrderDsctCode in the database
    List<PurchaseOrderDsctCode> purchaseOrderDsctCodeList =
        purchaseOrderDsctCodeRepository.findAll();
    assertThat(purchaseOrderDsctCodeList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deletePurchaseOrderDsctCode() throws Exception {
    // Initialize the database
    purchaseOrderDsctCodeRepository.saveAndFlush(purchaseOrderDsctCode);
    int databaseSizeBeforeDelete = purchaseOrderDsctCodeRepository.findAll().size();

    // Get the purchaseOrderDsctCode
    restPurchaseOrderDsctCodeMockMvc
        .perform(delete("/api/purchase-order-dsct-codes/{id}", purchaseOrderDsctCode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    // Validate the database is empty
    List<PurchaseOrderDsctCode> purchaseOrderDsctCodeList =
        purchaseOrderDsctCodeRepository.findAll();
    assertThat(purchaseOrderDsctCodeList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(PurchaseOrderDsctCode.class);
    PurchaseOrderDsctCode purchaseOrderDsctCode1 = new PurchaseOrderDsctCode();
    purchaseOrderDsctCode1.setId(1L);
    PurchaseOrderDsctCode purchaseOrderDsctCode2 = new PurchaseOrderDsctCode();
    purchaseOrderDsctCode2.setId(purchaseOrderDsctCode1.getId());
    assertThat(purchaseOrderDsctCode1).isEqualTo(purchaseOrderDsctCode2);
    purchaseOrderDsctCode2.setId(2L);
    assertThat(purchaseOrderDsctCode1).isNotEqualTo(purchaseOrderDsctCode2);
    purchaseOrderDsctCode1.setId(null);
    assertThat(purchaseOrderDsctCode1).isNotEqualTo(purchaseOrderDsctCode2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(PurchaseOrderDsctCodeDTO.class);
    PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO1 = new PurchaseOrderDsctCodeDTO();
    purchaseOrderDsctCodeDTO1.setId(1L);
    PurchaseOrderDsctCodeDTO purchaseOrderDsctCodeDTO2 = new PurchaseOrderDsctCodeDTO();
    assertThat(purchaseOrderDsctCodeDTO1).isNotEqualTo(purchaseOrderDsctCodeDTO2);
    purchaseOrderDsctCodeDTO2.setId(purchaseOrderDsctCodeDTO1.getId());
    assertThat(purchaseOrderDsctCodeDTO1).isEqualTo(purchaseOrderDsctCodeDTO2);
    purchaseOrderDsctCodeDTO2.setId(2L);
    assertThat(purchaseOrderDsctCodeDTO1).isNotEqualTo(purchaseOrderDsctCodeDTO2);
    purchaseOrderDsctCodeDTO1.setId(null);
    assertThat(purchaseOrderDsctCodeDTO1).isNotEqualTo(purchaseOrderDsctCodeDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(purchaseOrderDsctCodeMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(purchaseOrderDsctCodeMapper.fromId(null)).isNull();
  }
}
