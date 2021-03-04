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
import org.junit.Ignore;
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
import com.biz2tech.app.domain.InventoryDtls;
import com.biz2tech.app.repository.InventoryDtlsRepository;
import com.biz2tech.app.service.InventoryDtlsService;
import com.biz2tech.app.service.dto.InventoryDtlsDTO;
import com.biz2tech.app.service.mapper.InventoryDtlsMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the InventoryDtlsResource REST controller.
 *
 * @see InventoryDtlsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
@Ignore("idnetifier will be assigned, not generated")
public class InventoryDtlsResourceIntTest {

  private static final String DEFAULT_DESC = "AAAAAAAAAA";
  private static final String UPDATED_DESC = "BBBBBBBBBB";

  private static final BigDecimal DEFAULT_TOTAL_CNT = new BigDecimal(1);
  private static final BigDecimal UPDATED_TOTAL_CNT = new BigDecimal(2);

  private static final BigDecimal DEFAULT_AVBL_CNT = new BigDecimal(1);
  private static final BigDecimal UPDATED_AVBL_CNT = new BigDecimal(2);

  private static final BigDecimal DEFAULT_SELL_CNT = new BigDecimal(1);
  private static final BigDecimal UPDATED_SELL_CNT = new BigDecimal(2);

  private static final Double DEFAULT_BASE_PRICE = 1D;
  private static final Double UPDATED_BASE_PRICE = 2D;

  private static final Double DEFAULT_MARKED_PERCENTAGE = 1D;
  private static final Double UPDATED_MARKED_PERCENTAGE = 2D;

  private static final Double DEFAULT_SELL_PRICE = 1D;
  private static final Double UPDATED_SELL_PRICE = 2D;

  private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
  private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

  private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_CREATED_ON = Instant.now();

  private static final Instant DEFAULT_LAST_UPDATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_LAST_UPDATED_ON = Instant.now();

  @Autowired
  private InventoryDtlsRepository inventoryDtlsRepository;

  @Autowired
  private InventoryDtlsMapper inventoryDtlsMapper;

  @Autowired
  private InventoryDtlsService inventoryDtlsService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restInventoryDtlsMockMvc;

  private InventoryDtls inventoryDtls;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final InventoryDtlsResource inventoryDtlsResource =
        new InventoryDtlsResource(inventoryDtlsService);
    this.restInventoryDtlsMockMvc = MockMvcBuilders.standaloneSetup(inventoryDtlsResource)
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
  public static InventoryDtls createEntity(EntityManager em) {
    InventoryDtls inventoryDtls = new InventoryDtls().desc(DEFAULT_DESC).totalCnt(DEFAULT_TOTAL_CNT)
        .avblCnt(DEFAULT_AVBL_CNT).sellCnt(DEFAULT_SELL_CNT).basePrice(DEFAULT_BASE_PRICE)
        .markedPercentage(DEFAULT_MARKED_PERCENTAGE).sellPrice(DEFAULT_SELL_PRICE)
        .currencyCode(DEFAULT_CURRENCY_CODE);

    inventoryDtls.setCreatedBy(DEFAULT_CREATED_BY);
    inventoryDtls.setCreatedOn(DEFAULT_CREATED_ON);
    inventoryDtls.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    inventoryDtls.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);



    return inventoryDtls;
  }

  @Before
  public void initTest() {
    inventoryDtls = createEntity(em);
  }

  @Test
  @Transactional
  public void createInventoryDtls() throws Exception {
    int databaseSizeBeforeCreate = inventoryDtlsRepository.findAll().size();

    // Create the InventoryDtls
    InventoryDtlsDTO inventoryDtlsDTO = inventoryDtlsMapper.toDto(inventoryDtls);
    restInventoryDtlsMockMvc
        .perform(post("/api/inventory-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inventoryDtlsDTO)))
        .andExpect(status().isCreated());

    // Validate the InventoryDtls in the database
    List<InventoryDtls> inventoryDtlsList = inventoryDtlsRepository.findAll();
    assertThat(inventoryDtlsList).hasSize(databaseSizeBeforeCreate + 1);
    InventoryDtls testInventoryDtls = inventoryDtlsList.get(inventoryDtlsList.size() - 1);
    assertThat(testInventoryDtls.getDesc()).isEqualTo(DEFAULT_DESC);
    assertThat(testInventoryDtls.getTotalCnt()).isEqualTo(DEFAULT_TOTAL_CNT);
    assertThat(testInventoryDtls.getAvblCnt()).isEqualTo(DEFAULT_AVBL_CNT);
    assertThat(testInventoryDtls.getSellCnt()).isEqualTo(DEFAULT_SELL_CNT);
    assertThat(testInventoryDtls.getBasePrice()).isEqualTo(DEFAULT_BASE_PRICE);
    assertThat(testInventoryDtls.getMarkedPercentage()).isEqualTo(DEFAULT_MARKED_PERCENTAGE);
    assertThat(testInventoryDtls.getSellPrice()).isEqualTo(DEFAULT_SELL_PRICE);
    assertThat(testInventoryDtls.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
    assertThat(testInventoryDtls.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testInventoryDtls.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    assertThat(testInventoryDtls.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    assertThat(testInventoryDtls.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void createInventoryDtlsWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = inventoryDtlsRepository.findAll().size();

    // Create the InventoryDtls with an existing ID
    inventoryDtls.setId(1L);
    InventoryDtlsDTO inventoryDtlsDTO = inventoryDtlsMapper.toDto(inventoryDtls);

    // An entity with an existing ID cannot be created, so this API call must fail
    restInventoryDtlsMockMvc
        .perform(post("/api/inventory-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inventoryDtlsDTO)))
        .andExpect(status().isBadRequest());

    // Validate the InventoryDtls in the database
    List<InventoryDtls> inventoryDtlsList = inventoryDtlsRepository.findAll();
    assertThat(inventoryDtlsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllInventoryDtls() throws Exception {
    // Initialize the database
    inventoryDtlsRepository.saveAndFlush(inventoryDtls);

    // Get all the inventoryDtlsList
    restInventoryDtlsMockMvc.perform(get("/api/inventory-dtls?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(inventoryDtls.getId().intValue())))
        .andExpect(jsonPath("$.[*].desc").value(hasItem(DEFAULT_DESC.toString())))
        .andExpect(jsonPath("$.[*].totalCnt").value(hasItem(DEFAULT_TOTAL_CNT.intValue())))
        .andExpect(jsonPath("$.[*].avblCnt").value(hasItem(DEFAULT_AVBL_CNT.intValue())))
        .andExpect(jsonPath("$.[*].sellCnt").value(hasItem(DEFAULT_SELL_CNT.intValue())))
        .andExpect(jsonPath("$.[*].basePrice").value(hasItem(DEFAULT_BASE_PRICE.doubleValue())))
        .andExpect(jsonPath("$.[*].markedPercentage")
            .value(hasItem(DEFAULT_MARKED_PERCENTAGE.doubleValue())))
        .andExpect(jsonPath("$.[*].sellPrice").value(hasItem(DEFAULT_SELL_PRICE.doubleValue())))
        .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE.toString())))
        .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
        .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
  }

  @Test
  @Transactional
  public void getInventoryDtls() throws Exception {
    // Initialize the database
    inventoryDtlsRepository.saveAndFlush(inventoryDtls);

    // Get the inventoryDtls
    restInventoryDtlsMockMvc.perform(get("/api/inventory-dtls/{id}", inventoryDtls.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(inventoryDtls.getId().intValue()))
        .andExpect(jsonPath("$.desc").value(DEFAULT_DESC.toString()))
        .andExpect(jsonPath("$.totalCnt").value(DEFAULT_TOTAL_CNT.intValue()))
        .andExpect(jsonPath("$.avblCnt").value(DEFAULT_AVBL_CNT.intValue()))
        .andExpect(jsonPath("$.sellCnt").value(DEFAULT_SELL_CNT.intValue()))
        .andExpect(jsonPath("$.basePrice").value(DEFAULT_BASE_PRICE.doubleValue()))
        .andExpect(jsonPath("$.markedPercentage").value(DEFAULT_MARKED_PERCENTAGE.doubleValue()))
        .andExpect(jsonPath("$.sellPrice").value(DEFAULT_SELL_PRICE.doubleValue()))
        .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE.toString()))
        .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
        .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
        .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
        .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingInventoryDtls() throws Exception {
    // Get the inventoryDtls
    restInventoryDtlsMockMvc.perform(get("/api/inventory-dtls/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateInventoryDtls() throws Exception {
    // Initialize the database
    inventoryDtlsRepository.saveAndFlush(inventoryDtls);
    int databaseSizeBeforeUpdate = inventoryDtlsRepository.findAll().size();

    // Update the inventoryDtls
    InventoryDtls updatedInventoryDtls = inventoryDtlsRepository.findOne(inventoryDtls.getId());
    // Disconnect from session so that the updates on updatedInventoryDtls are not directly saved in
    // db
    em.detach(updatedInventoryDtls);
    updatedInventoryDtls.desc(UPDATED_DESC).totalCnt(UPDATED_TOTAL_CNT).avblCnt(UPDATED_AVBL_CNT)
        .sellCnt(UPDATED_SELL_CNT).basePrice(UPDATED_BASE_PRICE)
        .markedPercentage(UPDATED_MARKED_PERCENTAGE).sellPrice(UPDATED_SELL_PRICE)
        .currencyCode(UPDATED_CURRENCY_CODE);

    updatedInventoryDtls.setCreatedBy(DEFAULT_CREATED_BY);
    updatedInventoryDtls.setCreatedOn(DEFAULT_CREATED_ON);
    updatedInventoryDtls.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    updatedInventoryDtls.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);

    InventoryDtlsDTO inventoryDtlsDTO = inventoryDtlsMapper.toDto(updatedInventoryDtls);

    restInventoryDtlsMockMvc
        .perform(put("/api/inventory-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inventoryDtlsDTO)))
        .andExpect(status().isOk());

    // Validate the InventoryDtls in the database
    List<InventoryDtls> inventoryDtlsList = inventoryDtlsRepository.findAll();
    assertThat(inventoryDtlsList).hasSize(databaseSizeBeforeUpdate);
    InventoryDtls testInventoryDtls = inventoryDtlsList.get(inventoryDtlsList.size() - 1);
    assertThat(testInventoryDtls.getDesc()).isEqualTo(UPDATED_DESC);
    assertThat(testInventoryDtls.getTotalCnt()).isEqualTo(UPDATED_TOTAL_CNT);
    assertThat(testInventoryDtls.getAvblCnt()).isEqualTo(UPDATED_AVBL_CNT);
    assertThat(testInventoryDtls.getSellCnt()).isEqualTo(UPDATED_SELL_CNT);
    assertThat(testInventoryDtls.getBasePrice()).isEqualTo(UPDATED_BASE_PRICE);
    assertThat(testInventoryDtls.getMarkedPercentage()).isEqualTo(UPDATED_MARKED_PERCENTAGE);
    assertThat(testInventoryDtls.getSellPrice()).isEqualTo(UPDATED_SELL_PRICE);
    assertThat(testInventoryDtls.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
    assertThat(testInventoryDtls.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testInventoryDtls.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    assertThat(testInventoryDtls.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    assertThat(testInventoryDtls.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void updateNonExistingInventoryDtls() throws Exception {
    int databaseSizeBeforeUpdate = inventoryDtlsRepository.findAll().size();

    // Create the InventoryDtls
    InventoryDtlsDTO inventoryDtlsDTO = inventoryDtlsMapper.toDto(inventoryDtls);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restInventoryDtlsMockMvc
        .perform(put("/api/inventory-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(inventoryDtlsDTO)))
        .andExpect(status().isCreated());

    // Validate the InventoryDtls in the database
    List<InventoryDtls> inventoryDtlsList = inventoryDtlsRepository.findAll();
    assertThat(inventoryDtlsList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteInventoryDtls() throws Exception {
    // Initialize the database
    inventoryDtlsRepository.saveAndFlush(inventoryDtls);
    int databaseSizeBeforeDelete = inventoryDtlsRepository.findAll().size();

    // Get the inventoryDtls
    restInventoryDtlsMockMvc.perform(delete("/api/inventory-dtls/{id}", inventoryDtls.getId())
        .accept(TestUtil.APPLICATION_JSON_UTF8)).andExpect(status().isOk());

    // Validate the database is empty
    List<InventoryDtls> inventoryDtlsList = inventoryDtlsRepository.findAll();
    assertThat(inventoryDtlsList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(InventoryDtls.class);
    InventoryDtls inventoryDtls1 = new InventoryDtls();
    inventoryDtls1.setId(1L);
    InventoryDtls inventoryDtls2 = new InventoryDtls();
    inventoryDtls2.setId(inventoryDtls1.getId());
    assertThat(inventoryDtls1).isEqualTo(inventoryDtls2);
    inventoryDtls2.setId(2L);
    assertThat(inventoryDtls1).isNotEqualTo(inventoryDtls2);
    inventoryDtls1.setId(null);
    assertThat(inventoryDtls1).isNotEqualTo(inventoryDtls2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(InventoryDtlsDTO.class);
    InventoryDtlsDTO inventoryDtlsDTO1 = new InventoryDtlsDTO();
    inventoryDtlsDTO1.setId(1L);
    InventoryDtlsDTO inventoryDtlsDTO2 = new InventoryDtlsDTO();
    assertThat(inventoryDtlsDTO1).isNotEqualTo(inventoryDtlsDTO2);
    inventoryDtlsDTO2.setId(inventoryDtlsDTO1.getId());
    assertThat(inventoryDtlsDTO1).isEqualTo(inventoryDtlsDTO2);
    inventoryDtlsDTO2.setId(2L);
    assertThat(inventoryDtlsDTO1).isNotEqualTo(inventoryDtlsDTO2);
    inventoryDtlsDTO1.setId(null);
    assertThat(inventoryDtlsDTO1).isNotEqualTo(inventoryDtlsDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(inventoryDtlsMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(inventoryDtlsMapper.fromId(null)).isNull();
  }
}
