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
import com.biz2tech.app.domain.ShoppingCart;
import com.biz2tech.app.repository.ShoppingCartRepository;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.ShoppingCartService;
import com.biz2tech.app.service.dto.ShoppingCartDTO;
import com.biz2tech.app.service.mapper.ShoppingCartMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the ShoppingCartResource REST controller.
 *
 * @see ShoppingCartResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class ShoppingCartResourceIntTest {

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";


  private static final String DEFAULT_LAST_UPDATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_LAST_UPDATED_BY = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_CREATED_ON = Instant.now();

  private static final Instant DEFAULT_LAST_UPDATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_LAST_UPDATED_ON = Instant.now();
  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  @Autowired
  private ShoppingCartMapper shoppingCartMapper;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private ShoppingCartService shoppingCartService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restShoppingCartMockMvc;

  private ShoppingCart shoppingCart;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final ShoppingCartResource shoppingCartResource =
        new ShoppingCartResource(shoppingCartService, tokenProvider);
    this.restShoppingCartMockMvc = MockMvcBuilders.standaloneSetup(shoppingCartResource)
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
  public static ShoppingCart createEntity(EntityManager em) {
    ShoppingCart shoppingCart = new ShoppingCart().description(DEFAULT_DESCRIPTION);
    shoppingCart.setCreatedBy(DEFAULT_CREATED_BY);
    shoppingCart.setCreatedOn(DEFAULT_CREATED_ON);
    shoppingCart.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    shoppingCart.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
    return shoppingCart;
  }

  @Before
  public void initTest() {
    shoppingCart = createEntity(em);
  }

  @Test
  @Transactional
  public void createShoppingCart() throws Exception {
    int databaseSizeBeforeCreate = shoppingCartRepository.findAll().size();

    // Create the ShoppingCart
    ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toDto(shoppingCart);
    restShoppingCartMockMvc
        .perform(post("/api/shopping-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartDTO)))
        .andExpect(status().isCreated());

    // Validate the ShoppingCart in the database
    List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
    assertThat(shoppingCartList).hasSize(databaseSizeBeforeCreate + 1);
    ShoppingCart testShoppingCart = shoppingCartList.get(shoppingCartList.size() - 1);
    assertThat(testShoppingCart.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testShoppingCart.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testShoppingCart.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
    assertThat(testShoppingCart.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
    assertThat(testShoppingCart.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void createShoppingCartWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = shoppingCartRepository.findAll().size();

    // Create the ShoppingCart with an existing ID
    shoppingCart.setId(1L);
    ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toDto(shoppingCart);

    // An entity with an existing ID cannot be created, so this API call must fail
    restShoppingCartMockMvc
        .perform(post("/api/shopping-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartDTO)))
        .andExpect(status().isBadRequest());

    // Validate the ShoppingCart in the database
    List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
    assertThat(shoppingCartList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllShoppingCarts() throws Exception {
    // Initialize the database
    shoppingCartRepository.saveAndFlush(shoppingCart);

    // Get all the shoppingCartList
    restShoppingCartMockMvc.perform(get("/api/shopping-carts?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(shoppingCart.getId().intValue())))
        .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
        .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
        .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedBy").value(hasItem(DEFAULT_LAST_UPDATED_BY.toString())))
        .andExpect(
            jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
  }

  @Test
  @Transactional
  public void getShoppingCart() throws Exception {
    // Initialize the database
    shoppingCartRepository.saveAndFlush(shoppingCart);

    // Get the shoppingCart
    restShoppingCartMockMvc.perform(get("/api/shopping-carts/{id}", shoppingCart.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(shoppingCart.getId().intValue()))
        .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
        .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
        .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
        .andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()))
        .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
  }

  @Test
  @Transactional
  public void getNonExistingShoppingCart() throws Exception {
    // Get the shoppingCart
    restShoppingCartMockMvc.perform(get("/api/shopping-carts/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateShoppingCart() throws Exception {
    // Initialize the database
    shoppingCartRepository.saveAndFlush(shoppingCart);
    int databaseSizeBeforeUpdate = shoppingCartRepository.findAll().size();

    // Update the shoppingCart
    ShoppingCart updatedShoppingCart = shoppingCartRepository.findOne(shoppingCart.getId());
    // Disconnect from session so that the updates on updatedShoppingCart are not directly saved in
    // db
    em.detach(updatedShoppingCart);
    updatedShoppingCart.description(UPDATED_DESCRIPTION);

    updatedShoppingCart.setCreatedBy(DEFAULT_CREATED_BY);
    updatedShoppingCart.setCreatedOn(DEFAULT_CREATED_ON);
    updatedShoppingCart.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    updatedShoppingCart.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
    ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toDto(updatedShoppingCart);

    restShoppingCartMockMvc
        .perform(put("/api/shopping-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartDTO)))
        .andExpect(status().isOk());

    // Validate the ShoppingCart in the database
    List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
    assertThat(shoppingCartList).hasSize(databaseSizeBeforeUpdate);
    ShoppingCart testShoppingCart = shoppingCartList.get(shoppingCartList.size() - 1);
    assertThat(testShoppingCart.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testShoppingCart.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testShoppingCart.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    assertThat(testShoppingCart.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    assertThat(testShoppingCart.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void updateNonExistingShoppingCart() throws Exception {
    int databaseSizeBeforeUpdate = shoppingCartRepository.findAll().size();

    // Create the ShoppingCart
    ShoppingCartDTO shoppingCartDTO = shoppingCartMapper.toDto(shoppingCart);

    // If the entity doesn't have an ID, it will be created instead of just being updated
    restShoppingCartMockMvc
        .perform(put("/api/shopping-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartDTO)))
        .andExpect(status().isCreated());

    // Validate the ShoppingCart in the database
    List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
    assertThat(shoppingCartList).hasSize(databaseSizeBeforeUpdate + 1);
  }

  @Test
  @Transactional
  public void deleteShoppingCart() throws Exception {
    // Initialize the database
    shoppingCartRepository.saveAndFlush(shoppingCart);
    int databaseSizeBeforeDelete = shoppingCartRepository.findAll().size();

    // Get the shoppingCart
    restShoppingCartMockMvc.perform(delete("/api/shopping-carts/{id}", shoppingCart.getId())
        .accept(TestUtil.APPLICATION_JSON_UTF8)).andExpect(status().isOk());

    // Validate the database is empty
    List<ShoppingCart> shoppingCartList = shoppingCartRepository.findAll();
    assertThat(shoppingCartList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(ShoppingCart.class);
    ShoppingCart shoppingCart1 = new ShoppingCart();
    shoppingCart1.setId(1L);
    ShoppingCart shoppingCart2 = new ShoppingCart();
    shoppingCart2.setId(shoppingCart1.getId());
    assertThat(shoppingCart1).isEqualTo(shoppingCart2);
    shoppingCart2.setId(2L);
    assertThat(shoppingCart1).isNotEqualTo(shoppingCart2);
    shoppingCart1.setId(null);
    assertThat(shoppingCart1).isNotEqualTo(shoppingCart2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(ShoppingCartDTO.class);
    ShoppingCartDTO shoppingCartDTO1 = new ShoppingCartDTO();
    shoppingCartDTO1.setId(1L);
    ShoppingCartDTO shoppingCartDTO2 = new ShoppingCartDTO();
    assertThat(shoppingCartDTO1).isNotEqualTo(shoppingCartDTO2);
    shoppingCartDTO2.setId(shoppingCartDTO1.getId());
    assertThat(shoppingCartDTO1).isEqualTo(shoppingCartDTO2);
    shoppingCartDTO2.setId(2L);
    assertThat(shoppingCartDTO1).isNotEqualTo(shoppingCartDTO2);
    shoppingCartDTO1.setId(null);
    assertThat(shoppingCartDTO1).isNotEqualTo(shoppingCartDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(shoppingCartMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(shoppingCartMapper.fromId(null)).isNull();
  }
}
