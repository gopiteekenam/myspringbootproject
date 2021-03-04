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
import com.biz2tech.app.domain.ProductTag;
import com.biz2tech.app.repository.ProductTagRepository;
import com.biz2tech.app.service.ProductDtlsService;
import com.biz2tech.app.service.ProductTagService;
import com.biz2tech.app.service.dto.ProductTagDTO;
import com.biz2tech.app.service.mapper.ProductTagMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the ProductTagResource REST controller.
 *
 * @see ProductTagResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class ProductTagResourceIntTest {

  private static final String DEFAULT_TAG_NAME = "AAAAAAAAAA";
  private static final String UPDATED_TAG_NAME = "BBBBBBBBBB";

  private static final String DEFAULT_TAG_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_TAG_DESCRIPTION = "BBBBBBBBBB";

  private static final BigDecimal DEFAULT_PRODUCT_TAG_PARENT_ID = new BigDecimal(1);
  private static final BigDecimal UPDATED_PRODUCT_TAG_PARENT_ID = new BigDecimal(2);

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";


  private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_CREATED_ON = Instant.now();

  private static final Instant DEFAULT_LAST_UPDATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_LAST_UPDATED_ON = Instant.now();
  @Autowired
  private ProductTagRepository productTagRepository;

  @Autowired
  private ProductTagMapper productTagMapper;

  @Autowired
  private ProductTagService productTagService;
  
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

  private MockMvc restProductTagMockMvc;

  private ProductTag productTag;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final ProductTagResource productTagResource = new ProductTagResource(productTagService,productDtlsService);
    this.restProductTagMockMvc = MockMvcBuilders.standaloneSetup(productTagResource)
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
  public static ProductTag createEntity(EntityManager em) {
    ProductTag productTag = new ProductTag().tagName(DEFAULT_TAG_NAME)
        .tagDescription(DEFAULT_TAG_DESCRIPTION).productTagParentId(DEFAULT_PRODUCT_TAG_PARENT_ID);
    productTag.setCreatedBy(DEFAULT_CREATED_BY);
    productTag.setCreatedOn(DEFAULT_CREATED_ON);
    productTag.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    productTag.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
    return productTag;
  }

  @Before
  public void initTest() {
    productTag = createEntity(em);
  }

  @Test
  @Transactional
  public void createProductTag() throws Exception {
    int databaseSizeBeforeCreate = productTagRepository.findAll().size();

    // Create the ProductTag
    ProductTagDTO productTagDTO = productTagMapper.toDto(productTag);
    restProductTagMockMvc
        .perform(post("/api/product-tags").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
        .andExpect(status().isCreated());

    // Validate the ProductTag in the database
    List<ProductTag> productTagList = productTagRepository.findAll();
    assertThat(productTagList).hasSize(databaseSizeBeforeCreate + 1);
    ProductTag testProductTag = productTagList.get(productTagList.size() - 1);
    assertThat(testProductTag.getTagName()).isEqualTo(DEFAULT_TAG_NAME);
    assertThat(testProductTag.getTagDescription()).isEqualTo(DEFAULT_TAG_DESCRIPTION);
    assertThat(testProductTag.getProductTagParentId()).isEqualTo(DEFAULT_PRODUCT_TAG_PARENT_ID);
    assertThat(testProductTag.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testProductTag.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    assertThat(testProductTag.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    assertThat(testProductTag.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void createProductTagWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = productTagRepository.findAll().size();

    // Create the ProductTag with an existing ID
    productTag.setId(1L);
    ProductTagDTO productTagDTO = productTagMapper.toDto(productTag);

    // An entity with an existing ID cannot be created, so this API call must fail
    restProductTagMockMvc
        .perform(post("/api/product-tags").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
        .andExpect(status().isBadRequest());

    // Validate the ProductTag in the database
    List<ProductTag> productTagList = productTagRepository.findAll();
    assertThat(productTagList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllProductTags() throws Exception {
    // Initialize the database
    productTagRepository.saveAndFlush(productTag);

    // Get all the productTagList
    restProductTagMockMvc.perform(get("/api/product-tags?sort=id,desc")).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(productTag.getId().intValue())))
        .andExpect(jsonPath("$.[*].tagName").value(hasItem(DEFAULT_TAG_NAME.toString())))
        .andExpect(
            jsonPath("$.[*].tagDescription").value(hasItem(DEFAULT_TAG_DESCRIPTION.toString())))
        .andExpect(jsonPath("$.[*].productTagParentId")
            .value(hasItem(DEFAULT_PRODUCT_TAG_PARENT_ID.intValue())))
        .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
        .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
  }

  @Test
  @Transactional
  public void getProductTag() throws Exception {
    // Initialize the database
    productTagRepository.saveAndFlush(productTag);

    // Get the productTag
    restProductTagMockMvc.perform(get("/api/product-tags/{id}", productTag.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(productTag.getId().intValue()))
        .andExpect(jsonPath("$.tagName").value(DEFAULT_TAG_NAME.toString()))
        .andExpect(jsonPath("$.tagDescription").value(DEFAULT_TAG_DESCRIPTION.toString()))
        .andExpect(jsonPath("$.productTagParentId").value(DEFAULT_PRODUCT_TAG_PARENT_ID.intValue()))
        .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
        .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
        .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
        .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingProductTag() throws Exception {
    // Get the productTag
    restProductTagMockMvc.perform(get("/api/product-tags/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateProductTag() throws Exception {
    // Initialize the database
    productTagRepository.saveAndFlush(productTag);
    int databaseSizeBeforeUpdate = productTagRepository.findAll().size();

    // Update the productTag
    ProductTag updatedProductTag = productTagRepository.findOne(productTag.getId());
    // Disconnect from session so that the updates on updatedProductTag are not directly saved in db
    em.detach(updatedProductTag);
    updatedProductTag.tagName(UPDATED_TAG_NAME).tagDescription(UPDATED_TAG_DESCRIPTION)
        .productTagParentId(UPDATED_PRODUCT_TAG_PARENT_ID);
    updatedProductTag.setCreatedBy(DEFAULT_CREATED_BY);
    updatedProductTag.setCreatedOn(DEFAULT_CREATED_ON);
    updatedProductTag.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    updatedProductTag.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);

    ProductTagDTO productTagDTO = productTagMapper.toDto(updatedProductTag);

    restProductTagMockMvc
        .perform(put("/api/product-tags").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
        .andExpect(status().isOk());

    // Validate the ProductTag in the database
    List<ProductTag> productTagList = productTagRepository.findAll();
    assertThat(productTagList).hasSize(databaseSizeBeforeUpdate);
    ProductTag testProductTag = productTagList.get(productTagList.size() - 1);
    assertThat(testProductTag.getTagName()).isEqualTo(UPDATED_TAG_NAME);
    assertThat(testProductTag.getTagDescription()).isEqualTo(UPDATED_TAG_DESCRIPTION);
    assertThat(testProductTag.getProductTagParentId()).isEqualTo(UPDATED_PRODUCT_TAG_PARENT_ID);
    assertThat(testProductTag.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testProductTag.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    assertThat(testProductTag.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    assertThat(testProductTag.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void updateNonExistingProductTag() throws Exception {
    int databaseSizeBeforeUpdate = productTagRepository.findAll().size();

    // Create the ProductTag
    ProductTagDTO productTagDTO = productTagMapper.toDto(productTag);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restProductTagMockMvc
        .perform(put("/api/product-tags").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(productTagDTO)))
        .andExpect(status().isCreated());

    // Validate the ProductTag in the database
    List<ProductTag> productTagList = productTagRepository.findAll();
    assertThat(productTagList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteProductTag() throws Exception {
    // Initialize the database
    productTagRepository.saveAndFlush(productTag);
    int databaseSizeBeforeDelete = productTagRepository.findAll().size();

    // Get the productTag
    restProductTagMockMvc.perform(
        delete("/api/product-tags/{id}", productTag.getId()).accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    // Validate the database is empty
    List<ProductTag> productTagList = productTagRepository.findAll();
    assertThat(productTagList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(ProductTag.class);
    ProductTag productTag1 = new ProductTag();
    productTag1.setId(1L);
    ProductTag productTag2 = new ProductTag();
    productTag2.setId(productTag1.getId());
    assertThat(productTag1).isEqualTo(productTag2);
    productTag2.setId(2L);
    assertThat(productTag1).isNotEqualTo(productTag2);
    productTag1.setId(null);
    assertThat(productTag1).isNotEqualTo(productTag2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(ProductTagDTO.class);
    ProductTagDTO productTagDTO1 = new ProductTagDTO();
    productTagDTO1.setId(1L);
    ProductTagDTO productTagDTO2 = new ProductTagDTO();
    assertThat(productTagDTO1).isNotEqualTo(productTagDTO2);
    productTagDTO2.setId(productTagDTO1.getId());
    assertThat(productTagDTO1).isEqualTo(productTagDTO2);
    productTagDTO2.setId(2L);
    assertThat(productTagDTO1).isNotEqualTo(productTagDTO2);
    productTagDTO1.setId(null);
    assertThat(productTagDTO1).isNotEqualTo(productTagDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(productTagMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(productTagMapper.fromId(null)).isNull();
  }
}
