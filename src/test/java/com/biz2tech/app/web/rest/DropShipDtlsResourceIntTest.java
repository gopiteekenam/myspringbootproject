package com.biz2tech.app.web.rest;

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
import com.biz2tech.app.domain.DropShipDtls;
import com.biz2tech.app.repository.DropShipDtlsRepository;
import com.biz2tech.app.service.DropShipDtlsService;
import com.biz2tech.app.service.dto.DropShipDtlsDTO;
import com.biz2tech.app.service.mapper.DropShipDtlsMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the DropShipDtlsResource REST controller.
 *
 * @see DropShipDtlsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class DropShipDtlsResourceIntTest {

  private static final String DEFAULT_VENDOR_NAME = "AAAAAAAAAA";
  private static final String UPDATED_VENDOR_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_VENDOR_URL = "AAAAAAAAAA";
  private static final String UPDATED_VENDOR_URL = "BBBBBBBBBB";

  private static final Double DEFAULT_TOTAL_CHRG_TO_CUST = 1D;
  private static final Double UPDATED_TOTAL_CHRG_TO_CUST = 2D;

  private static final String DEFAULT_CURRENCY_CODE = "AAAAAAAAAA";
  private static final String UPDATED_CURRENCY_CODE = "BBBBBBBBBB";

  private static final Double DEFAULT_MARGIN = 1D;
  private static final Double UPDATED_MARGIN = 2D;

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

  private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_CREATED_ON = Instant.now();

  private static final Instant DEFAULT_LAST_UPDATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_LAST_UPDATED_ON = Instant.now();

  @Autowired
  private DropShipDtlsRepository dropShipDtlsRepository;

  @Autowired
  private DropShipDtlsMapper dropShipDtlsMapper;

  @Autowired
  private DropShipDtlsService dropShipDtlsService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restDropShipDtlsMockMvc;

  private DropShipDtls dropShipDtls;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final DropShipDtlsResource dropShipDtlsResource = new DropShipDtlsResource(dropShipDtlsService);
    this.restDropShipDtlsMockMvc = MockMvcBuilders.standaloneSetup(dropShipDtlsResource)
        .setCustomArgumentResolvers(pageableArgumentResolver)
        .setControllerAdvice(exceptionTranslator)
        .setConversionService(TestUtil.createFormattingConversionService())
        .setMessageConverters(jacksonMessageConverter).build();
  }

  /**
   * Create an entity for this test.
   *
   * This is a static method, as tests for other entities might also need it, if they test an entity
   * which requires the current entity.
   */
  public static DropShipDtls createEntity(EntityManager em) {
    DropShipDtls dropShipDtls = new DropShipDtls().vendorName(DEFAULT_VENDOR_NAME)
        .vendorUrl(DEFAULT_VENDOR_URL).totalChrgToCust(DEFAULT_TOTAL_CHRG_TO_CUST)
        .currencyCode(DEFAULT_CURRENCY_CODE).margin(DEFAULT_MARGIN);

    dropShipDtls.setCreatedBy(DEFAULT_CREATED_BY);
    dropShipDtls.setCreatedOn(DEFAULT_CREATED_ON);
    dropShipDtls.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    dropShipDtls.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
    return dropShipDtls;
  }

  @Before
  public void initTest() {
    dropShipDtls = createEntity(em);
  }

  @Test
  @Transactional
  public void createDropShipDtls() throws Exception {
    int databaseSizeBeforeCreate = dropShipDtlsRepository.findAll().size();

    // Create the DropShipDtls
    DropShipDtlsDTO dropShipDtlsDTO = dropShipDtlsMapper.toDto(dropShipDtls);
    restDropShipDtlsMockMvc
        .perform(post("/api/drop-ship-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dropShipDtlsDTO)))
        .andExpect(status().isCreated());

    // Validate the DropShipDtls in the database
    List<DropShipDtls> dropShipDtlsList = dropShipDtlsRepository.findAll();
    assertThat(dropShipDtlsList).hasSize(databaseSizeBeforeCreate + 1);
    DropShipDtls testDropShipDtls = dropShipDtlsList.get(dropShipDtlsList.size() - 1);
    assertThat(testDropShipDtls.getVendorName()).isEqualTo(DEFAULT_VENDOR_NAME);
    assertThat(testDropShipDtls.getVendorUrl()).isEqualTo(DEFAULT_VENDOR_URL);
    assertThat(testDropShipDtls.getTotalChrgToCust()).isEqualTo(DEFAULT_TOTAL_CHRG_TO_CUST);
    assertThat(testDropShipDtls.getCurrencyCode()).isEqualTo(DEFAULT_CURRENCY_CODE);
    assertThat(testDropShipDtls.getMargin()).isEqualTo(DEFAULT_MARGIN);
    // assertThat(testDropShipDtls.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    // assertThat(testDropShipDtls.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    // assertThat(testDropShipDtls.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    // assertThat(testDropShipDtls.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void createDropShipDtlsWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = dropShipDtlsRepository.findAll().size();

    // Create the DropShipDtls with an existing ID
    dropShipDtls.setId(1L);
    DropShipDtlsDTO dropShipDtlsDTO = dropShipDtlsMapper.toDto(dropShipDtls);

    // An entity with an existing ID cannot be created, so this API call must fail
    restDropShipDtlsMockMvc
        .perform(post("/api/drop-ship-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dropShipDtlsDTO)))
        .andExpect(status().isBadRequest());

    // Validate the DropShipDtls in the database
    List<DropShipDtls> dropShipDtlsList = dropShipDtlsRepository.findAll();
    assertThat(dropShipDtlsList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllDropShipDtls() throws Exception {
    // Initialize the database
    dropShipDtlsRepository.saveAndFlush(dropShipDtls);

    // Get all the dropShipDtlsList
    restDropShipDtlsMockMvc.perform(get("/api/drop-ship-dtls?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(dropShipDtls.getId().intValue())))
        .andExpect(jsonPath("$.[*].vendorName").value(hasItem(DEFAULT_VENDOR_NAME.toString())))
        .andExpect(jsonPath("$.[*].vendorUrl").value(hasItem(DEFAULT_VENDOR_URL.toString())))
        .andExpect(jsonPath("$.[*].totalChrgToCust")
            .value(hasItem(DEFAULT_TOTAL_CHRG_TO_CUST.doubleValue())))
        .andExpect(jsonPath("$.[*].currencyCode").value(hasItem(DEFAULT_CURRENCY_CODE.toString())))
        .andExpect(jsonPath("$.[*].margin").value(hasItem(DEFAULT_MARGIN.doubleValue())));
    // .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
    // .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
    // .andExpect(
    // jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
    // .andExpect(
    // jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
  }

  @Test
  @Transactional
  public void getDropShipDtls() throws Exception {
    // Initialize the database
    dropShipDtlsRepository.saveAndFlush(dropShipDtls);

    // Get the dropShipDtls
    restDropShipDtlsMockMvc.perform(get("/api/drop-ship-dtls/{id}", dropShipDtls.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(dropShipDtls.getId().intValue()))
        .andExpect(jsonPath("$.vendorName").value(DEFAULT_VENDOR_NAME.toString()))
        .andExpect(jsonPath("$.vendorUrl").value(DEFAULT_VENDOR_URL.toString()))
        .andExpect(jsonPath("$.totalChrgToCust").value(DEFAULT_TOTAL_CHRG_TO_CUST.doubleValue()))
        .andExpect(jsonPath("$.currencyCode").value(DEFAULT_CURRENCY_CODE.toString()))
        .andExpect(jsonPath("$.margin").value(DEFAULT_MARGIN.doubleValue()));
    // .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
    // .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
    // .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
    // .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingDropShipDtls() throws Exception {
    // Get the dropShipDtls
    restDropShipDtlsMockMvc.perform(get("/api/drop-ship-dtls/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateDropShipDtls() throws Exception {
    // Initialize the database
    dropShipDtlsRepository.saveAndFlush(dropShipDtls);
    int databaseSizeBeforeUpdate = dropShipDtlsRepository.findAll().size();

    // Update the dropShipDtls
    DropShipDtls updatedDropShipDtls = dropShipDtlsRepository.findOne(dropShipDtls.getId());
    // Disconnect from session so that the updates on updatedDropShipDtls are not directly saved in
    // db
    em.detach(updatedDropShipDtls);
    updatedDropShipDtls.vendorName(UPDATED_VENDOR_NAME).vendorUrl(UPDATED_VENDOR_URL)
        .totalChrgToCust(UPDATED_TOTAL_CHRG_TO_CUST).currencyCode(UPDATED_CURRENCY_CODE)
        .margin(UPDATED_MARGIN);

    updatedDropShipDtls.setCreatedBy(DEFAULT_CREATED_BY);
    updatedDropShipDtls.setCreatedOn(DEFAULT_CREATED_ON);
    updatedDropShipDtls.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    updatedDropShipDtls.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);

    DropShipDtlsDTO dropShipDtlsDTO = dropShipDtlsMapper.toDto(updatedDropShipDtls);

    restDropShipDtlsMockMvc
        .perform(put("/api/drop-ship-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dropShipDtlsDTO)))
        .andExpect(status().isOk());

    // Validate the DropShipDtls in the database
    List<DropShipDtls> dropShipDtlsList = dropShipDtlsRepository.findAll();
    assertThat(dropShipDtlsList).hasSize(databaseSizeBeforeUpdate);
    DropShipDtls testDropShipDtls = dropShipDtlsList.get(dropShipDtlsList.size() - 1);
    assertThat(testDropShipDtls.getVendorName()).isEqualTo(UPDATED_VENDOR_NAME);
    assertThat(testDropShipDtls.getVendorUrl()).isEqualTo(UPDATED_VENDOR_URL);
    assertThat(testDropShipDtls.getTotalChrgToCust()).isEqualTo(UPDATED_TOTAL_CHRG_TO_CUST);
    assertThat(testDropShipDtls.getCurrencyCode()).isEqualTo(UPDATED_CURRENCY_CODE);
    assertThat(testDropShipDtls.getMargin()).isEqualTo(UPDATED_MARGIN);
    // assertThat(testDropShipDtls.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    // assertThat(testDropShipDtls.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    // assertThat(testDropShipDtls.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    // assertThat(testDropShipDtls.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void updateNonExistingDropShipDtls() throws Exception {
    int databaseSizeBeforeUpdate = dropShipDtlsRepository.findAll().size();

    // Create the DropShipDtls
    DropShipDtlsDTO dropShipDtlsDTO = dropShipDtlsMapper.toDto(dropShipDtls);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restDropShipDtlsMockMvc
        .perform(put("/api/drop-ship-dtls").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dropShipDtlsDTO)))
        .andExpect(status().isCreated());

    // Validate the DropShipDtls in the database
    List<DropShipDtls> dropShipDtlsList = dropShipDtlsRepository.findAll();
    assertThat(dropShipDtlsList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteDropShipDtls() throws Exception {
    // Initialize the database
    dropShipDtlsRepository.saveAndFlush(dropShipDtls);
    int databaseSizeBeforeDelete = dropShipDtlsRepository.findAll().size();

    // Get the dropShipDtls
    restDropShipDtlsMockMvc.perform(delete("/api/drop-ship-dtls/{id}", dropShipDtls.getId())
        .accept(TestUtil.APPLICATION_JSON_UTF8)).andExpect(status().isOk());

    // Validate the database is empty
    List<DropShipDtls> dropShipDtlsList = dropShipDtlsRepository.findAll();
    assertThat(dropShipDtlsList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(DropShipDtls.class);
    DropShipDtls dropShipDtls1 = new DropShipDtls();
    dropShipDtls1.setId(1L);
    DropShipDtls dropShipDtls2 = new DropShipDtls();
    dropShipDtls2.setId(dropShipDtls1.getId());
    assertThat(dropShipDtls1).isEqualTo(dropShipDtls2);
    dropShipDtls2.setId(2L);
    assertThat(dropShipDtls1).isNotEqualTo(dropShipDtls2);
    dropShipDtls1.setId(null);
    assertThat(dropShipDtls1).isNotEqualTo(dropShipDtls2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(DropShipDtlsDTO.class);
    DropShipDtlsDTO dropShipDtlsDTO1 = new DropShipDtlsDTO();
    dropShipDtlsDTO1.setId(1L);
    DropShipDtlsDTO dropShipDtlsDTO2 = new DropShipDtlsDTO();
    assertThat(dropShipDtlsDTO1).isNotEqualTo(dropShipDtlsDTO2);
    dropShipDtlsDTO2.setId(dropShipDtlsDTO1.getId());
    assertThat(dropShipDtlsDTO1).isEqualTo(dropShipDtlsDTO2);
    dropShipDtlsDTO2.setId(2L);
    assertThat(dropShipDtlsDTO1).isNotEqualTo(dropShipDtlsDTO2);
    dropShipDtlsDTO1.setId(null);
    assertThat(dropShipDtlsDTO1).isNotEqualTo(dropShipDtlsDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(dropShipDtlsMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(dropShipDtlsMapper.fromId(null)).isNull();
  }
}
