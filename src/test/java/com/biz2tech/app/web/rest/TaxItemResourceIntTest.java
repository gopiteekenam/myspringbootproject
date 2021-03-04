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
import com.biz2tech.app.domain.TaxItem;
import com.biz2tech.app.repository.TaxItemRepository;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.TaxItemService;
import com.biz2tech.app.service.dto.TaxItemDTO;
import com.biz2tech.app.service.mapper.TaxItemMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the TaxItemResource REST controller.
 *
 * @see TaxItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class TaxItemResourceIntTest {

  private static final BigDecimal DEFAULT_PINCODE = new BigDecimal(1);
  private static final BigDecimal UPDATED_PINCODE = new BigDecimal(2);

  private static final String DEFAULT_NAME = "AAAAAAAAAA";
  private static final String UPDATED_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  private static final Double DEFAULT_PERCENTAGE = 1D;
  private static final Double UPDATED_PERCENTAGE = 2D;

  private static final Instant DEFAULT_APPLICABLE_FROM = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_APPLICABLE_FROM = Instant.now();

  private static final Instant DEFAULT_APPLICABLE_TILL = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_APPLICABLE_TILL = Instant.now();

  @Autowired
  private TaxItemRepository taxItemRepository;

  @Autowired
  private TaxItemMapper taxItemMapper;

  @Autowired
  private TaxItemService taxItemService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;
  
  private TokenProvider tokenProvider;

  private MockMvc restTaxItemMockMvc;

  private TaxItem taxItem;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final TaxItemResource taxItemResource = new TaxItemResource();
    this.restTaxItemMockMvc = MockMvcBuilders.standaloneSetup(taxItemResource)
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
  public static TaxItem createEntity(EntityManager em) {
    TaxItem taxItem = new TaxItem().pincode(DEFAULT_PINCODE).name(DEFAULT_NAME)
        .description(DEFAULT_DESCRIPTION).percentage(DEFAULT_PERCENTAGE)
        .applicableFrom(DEFAULT_APPLICABLE_FROM).applicableTill(DEFAULT_APPLICABLE_TILL);
    return taxItem;
  }

  @Before
  public void initTest() {
    taxItem = createEntity(em);
  }

  @Test
  @Transactional
  public void createTaxItem() throws Exception {
    int databaseSizeBeforeCreate = taxItemRepository.findAll().size();

    // Create the TaxItem
    TaxItemDTO taxItemDTO = taxItemMapper.toDto(taxItem);
    restTaxItemMockMvc
        .perform(post("/api/tax-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxItemDTO)))
        .andExpect(status().isCreated());

    // Validate the TaxItem in the database
    List<TaxItem> taxItemList = taxItemRepository.findAll();
    assertThat(taxItemList).hasSize(databaseSizeBeforeCreate + 1);
    TaxItem testTaxItem = taxItemList.get(taxItemList.size() - 1);
    assertThat(testTaxItem.getPincode()).isEqualTo(DEFAULT_PINCODE);
    assertThat(testTaxItem.getName()).isEqualTo(DEFAULT_NAME);
    assertThat(testTaxItem.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testTaxItem.getPercentage()).isEqualTo(DEFAULT_PERCENTAGE);
    assertThat(testTaxItem.getApplicableFrom()).isEqualTo(DEFAULT_APPLICABLE_FROM);
    assertThat(testTaxItem.getApplicableTill()).isEqualTo(DEFAULT_APPLICABLE_TILL);
  }

  @Test
  @Transactional
  public void createTaxItemWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = taxItemRepository.findAll().size();

    // Create the TaxItem with an existing ID
    taxItem.setId(1L);
    TaxItemDTO taxItemDTO = taxItemMapper.toDto(taxItem);

    // An entity with an existing ID cannot be created, so this API call must fail
    restTaxItemMockMvc
        .perform(post("/api/tax-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxItemDTO)))
        .andExpect(status().isBadRequest());

    // Validate the TaxItem in the database
    List<TaxItem> taxItemList = taxItemRepository.findAll();
    assertThat(taxItemList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllTaxItems() throws Exception {
    // Initialize the database
    taxItemRepository.saveAndFlush(taxItem);

    // Get all the taxItemList
    restTaxItemMockMvc.perform(get("/api/tax-items?sort=id,desc")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(taxItem.getId().intValue())))
        .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
        .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
        .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
        .andExpect(jsonPath("$.[*].percentage").value(hasItem(DEFAULT_PERCENTAGE.doubleValue())))
        .andExpect(
            jsonPath("$.[*].applicableFrom").value(hasItem(DEFAULT_APPLICABLE_FROM.toString())))
        .andExpect(
            jsonPath("$.[*].applicableTill").value(hasItem(DEFAULT_APPLICABLE_TILL.toString())));
  }

  @Test
  @Transactional
  public void getTaxItem() throws Exception {
    // Initialize the database
    taxItemRepository.saveAndFlush(taxItem);

    // Get the taxItem
    restTaxItemMockMvc.perform(get("/api/tax-items/{id}", taxItem.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(taxItem.getId().intValue()))
        .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.intValue()))
        .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
        .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
        .andExpect(jsonPath("$.percentage").value(DEFAULT_PERCENTAGE.doubleValue()))
        .andExpect(jsonPath("$.applicableFrom").value(DEFAULT_APPLICABLE_FROM.toString()))
        .andExpect(jsonPath("$.applicableTill").value(DEFAULT_APPLICABLE_TILL.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingTaxItem() throws Exception {
    // Get the taxItem
    restTaxItemMockMvc.perform(get("/api/tax-items/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateTaxItem() throws Exception {
    // Initialize the database
    taxItemRepository.saveAndFlush(taxItem);
    int databaseSizeBeforeUpdate = taxItemRepository.findAll().size();

    // Update the taxItem
    TaxItem updatedTaxItem = taxItemRepository.findOne(taxItem.getId());
    // Disconnect from session so that the updates on updatedTaxItem are not directly saved in db
    em.detach(updatedTaxItem);
    updatedTaxItem.pincode(UPDATED_PINCODE).name(UPDATED_NAME).description(UPDATED_DESCRIPTION)
        .percentage(UPDATED_PERCENTAGE).applicableFrom(UPDATED_APPLICABLE_FROM)
        .applicableTill(UPDATED_APPLICABLE_TILL);
    TaxItemDTO taxItemDTO = taxItemMapper.toDto(updatedTaxItem);

    restTaxItemMockMvc.perform(put("/api/tax-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
        .content(TestUtil.convertObjectToJsonBytes(taxItemDTO))).andExpect(status().isOk());

    // Validate the TaxItem in the database
    List<TaxItem> taxItemList = taxItemRepository.findAll();
    assertThat(taxItemList).hasSize(databaseSizeBeforeUpdate);
    TaxItem testTaxItem = taxItemList.get(taxItemList.size() - 1);
    assertThat(testTaxItem.getPincode()).isEqualTo(UPDATED_PINCODE);
    assertThat(testTaxItem.getName()).isEqualTo(UPDATED_NAME);
    assertThat(testTaxItem.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testTaxItem.getPercentage()).isEqualTo(UPDATED_PERCENTAGE);
    assertThat(testTaxItem.getApplicableFrom()).isEqualTo(UPDATED_APPLICABLE_FROM);
    assertThat(testTaxItem.getApplicableTill()).isEqualTo(UPDATED_APPLICABLE_TILL);
  }

  @Test
  @Transactional
  public void updateNonExistingTaxItem() throws Exception {
    int databaseSizeBeforeUpdate = taxItemRepository.findAll().size();

    // Create the TaxItem
    TaxItemDTO taxItemDTO = taxItemMapper.toDto(taxItem);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restTaxItemMockMvc
        .perform(put("/api/tax-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(taxItemDTO)))
        .andExpect(status().isCreated());

    // Validate the TaxItem in the database
    List<TaxItem> taxItemList = taxItemRepository.findAll();
    assertThat(taxItemList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteTaxItem() throws Exception {
    // Initialize the database
    taxItemRepository.saveAndFlush(taxItem);
    int databaseSizeBeforeDelete = taxItemRepository.findAll().size();

    // Get the taxItem
    restTaxItemMockMvc
        .perform(
            delete("/api/tax-items/{id}", taxItem.getId()).accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    // Validate the database is empty
    List<TaxItem> taxItemList = taxItemRepository.findAll();
    assertThat(taxItemList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(TaxItem.class);
    TaxItem taxItem1 = new TaxItem();
    taxItem1.setId(1L);
    TaxItem taxItem2 = new TaxItem();
    taxItem2.setId(taxItem1.getId());
    assertThat(taxItem1).isEqualTo(taxItem2);
    taxItem2.setId(2L);
    assertThat(taxItem1).isNotEqualTo(taxItem2);
    taxItem1.setId(null);
    assertThat(taxItem1).isNotEqualTo(taxItem2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(TaxItemDTO.class);
    TaxItemDTO taxItemDTO1 = new TaxItemDTO();
    taxItemDTO1.setId(1L);
    TaxItemDTO taxItemDTO2 = new TaxItemDTO();
    assertThat(taxItemDTO1).isNotEqualTo(taxItemDTO2);
    taxItemDTO2.setId(taxItemDTO1.getId());
    assertThat(taxItemDTO1).isEqualTo(taxItemDTO2);
    taxItemDTO2.setId(2L);
    assertThat(taxItemDTO1).isNotEqualTo(taxItemDTO2);
    taxItemDTO1.setId(null);
    assertThat(taxItemDTO1).isNotEqualTo(taxItemDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(taxItemMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(taxItemMapper.fromId(null)).isNull();
  }
}
