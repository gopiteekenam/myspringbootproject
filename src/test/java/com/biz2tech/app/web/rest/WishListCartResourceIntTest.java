package com.biz2tech.app.web.rest;

import static com.biz2tech.app.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
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
import com.biz2tech.app.domain.WishListCart;
import com.biz2tech.app.repository.WishListCartRepository;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.WishListCartService;
import com.biz2tech.app.service.dto.WishListCartDTO;
import com.biz2tech.app.service.mapper.WishListCartMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the WishListCartResource REST controller.
 *
 * @see WishListCartResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class WishListCartResourceIntTest {

  private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
  private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

  private static final String DEFAULT_CREATED_BY = "system";
  

  private static final String DEFAULT_LAST_UPDATED_BY = "system";
  private static final String UPDATED_LAST_UPDATED_BY = "system";
  

  private static final Instant DEFAULT_CREATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_CREATED_ON = Instant.now();

  private static final Instant DEFAULT_LAST_UPDATED_ON = Instant.now().minus(1, ChronoUnit.HOURS);
  private static final Instant UPDATED_LAST_UPDATED_ON = Instant.now().truncatedTo(ChronoUnit.HOURS);
  @Autowired
  private WishListCartRepository wishListCartRepository;

  @Autowired
  private WishListCartMapper wishListCartMapper;

  @Autowired
  private WishListCartService wishListCartService;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restWishListCartMockMvc;

  private WishListCart wishListCart;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final WishListCartResource wishListCartResource =
        new WishListCartResource(wishListCartService, tokenProvider);
    this.restWishListCartMockMvc = MockMvcBuilders.standaloneSetup(wishListCartResource)
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
  public static WishListCart createEntity(EntityManager em) {
    WishListCart wishListCart = new WishListCart().description(DEFAULT_DESCRIPTION);
    wishListCart.setCreatedBy(DEFAULT_CREATED_BY);
    wishListCart.setCreatedOn(DEFAULT_CREATED_ON);
    wishListCart.setLastUpdatedBy(DEFAULT_LAST_UPDATED_BY);
    wishListCart.setLastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
    return wishListCart;
  }

  @Before
  public void initTest() {
    wishListCart = createEntity(em);
  }

  @Test
  @Transactional
  public void createWishListCart() throws Exception {
    int databaseSizeBeforeCreate = wishListCartRepository.findAll().size();

    // Create the WishListCart
    WishListCartDTO wishListCartDTO = wishListCartMapper.toDto(wishListCart);
		restWishListCartMockMvc.perform(post("/api/wish-list-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.header("Authorization", tokenProvider.getSecretKey())
				.content(TestUtil.convertObjectToJsonBytes(wishListCartDTO))).andExpect(status().isCreated());

    // Validate the WishListCart in the database
    List<WishListCart> wishListCartList = wishListCartRepository.findAll();
    assertThat(wishListCartList).hasSize(databaseSizeBeforeCreate + 1);
    WishListCart testWishListCart = wishListCartList.get(wishListCartList.size() - 1);
    assertThat(testWishListCart.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    assertThat(testWishListCart.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testWishListCart.getLastUpdatedBy()).isEqualTo(DEFAULT_LAST_UPDATED_BY);
  }

  @Test
  @Transactional
  public void createWishListCartWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = wishListCartRepository.findAll().size();

    // Create the WishListCart with an existing ID
    wishListCart.setId(1L);
    WishListCartDTO wishListCartDTO = wishListCartMapper.toDto(wishListCart);

    // An entity with an existing ID cannot be created, so this API call must fail
    restWishListCartMockMvc
        .perform(post("/api/wish-list-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListCartDTO)))
        .andExpect(status().isBadRequest());

    // Validate the WishListCart in the database
    List<WishListCart> wishListCartList = wishListCartRepository.findAll();
    assertThat(wishListCartList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getWishListCart() throws Exception {
    // Initialize the database
    wishListCartRepository.saveAndFlush(wishListCart);

		// Get the wishListCart
		restWishListCartMockMvc
				.perform(get("/api/wish-list-carts/{id}", wishListCart.getId()).header("Authorization",
						tokenProvider.getSecretKey()))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id").value(wishListCart.getId().intValue()))
				.andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
				.andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
				.andExpect(jsonPath("$.lastUpdatedBy").value(DEFAULT_LAST_UPDATED_BY.toString()));
	}

  @Test
  @Transactional
  public void getNonExistingWishListCart() throws Exception {
    // Get the wishListCart
    restWishListCartMockMvc.perform(get("/api/wish-list-carts/{id}", Long.MAX_VALUE).header("Authorization", tokenProvider.getSecretKey()))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateWishListCart() throws Exception {
    // Initialize the database
    wishListCartRepository.saveAndFlush(wishListCart);

    int databaseSizeBeforeUpdate = wishListCartRepository.findAll().size();

    // Update the wishListCart
    WishListCart updatedWishListCart = wishListCartRepository.findOne(wishListCart.getId());
    // Disconnect from session so that the updates on updatedWishListCart are not directly saved in
    // db
    em.detach(updatedWishListCart);
    updatedWishListCart.description(UPDATED_DESCRIPTION);

    updatedWishListCart.setCreatedBy(DEFAULT_CREATED_BY);
    updatedWishListCart.setCreatedOn(UPDATED_CREATED_ON);
    updatedWishListCart.setLastUpdatedBy(UPDATED_LAST_UPDATED_BY);
    updatedWishListCart.setLastUpdatedOn(UPDATED_LAST_UPDATED_ON);
    WishListCartDTO wishListCartDTO = wishListCartMapper.toDto(updatedWishListCart);

		restWishListCartMockMvc.perform(put("/api/wish-list-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.header("Authorization", tokenProvider.getSecretKey())
				.content(TestUtil.convertObjectToJsonBytes(wishListCartDTO))).andExpect(status().isOk());

    // Validate the WishListCart in the database
    List<WishListCart> wishListCartList = wishListCartRepository.findAll();
    assertThat(wishListCartList).hasSize(databaseSizeBeforeUpdate);
    WishListCart testWishListCart = wishListCartList.get(wishListCartList.size() - 1);
    assertThat(testWishListCart.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    assertThat(testWishListCart.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testWishListCart.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
    assertThat(testWishListCart.getLastUpdatedBy()).isEqualTo(UPDATED_LAST_UPDATED_BY);
    assertThat(testWishListCart.getLastUpdatedOn().truncatedTo(ChronoUnit.HOURS)).isEqualTo(UPDATED_LAST_UPDATED_ON);
  }

  @Test
  @Transactional
  public void updateNonExistingWishListCart() throws Exception {
    int databaseSizeBeforeUpdate = wishListCartRepository.findAll().size();

    // Create the WishListCart
    WishListCartDTO wishListCartDTO = wishListCartMapper.toDto(wishListCart);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restWishListCartMockMvc
        .perform(put("/api/wish-list-carts").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListCartDTO)))
        .andExpect(status().isBadRequest());

    // Validate the WishListCart in the database
    List<WishListCart> wishListCartList = wishListCartRepository.findAll();
    assertThat(wishListCartList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  public void deleteWishListCart() throws Exception {
    // Initialize the database
    wishListCartRepository.saveAndFlush(wishListCart);

    int databaseSizeBeforeDelete = wishListCartRepository.findAll().size();

    // Get the wishListCart
		restWishListCartMockMvc.perform(delete("/api/wish-list-carts/{id}", wishListCart.getId())
				.header("Authorization", tokenProvider.getSecretKey()).accept(TestUtil.APPLICATION_JSON_UTF8)).andExpect(status().isOk());
    // Validate the database is empty
    List<WishListCart> wishListCartList = wishListCartRepository.findAll();
    assertThat(wishListCartList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(WishListCart.class);
    WishListCart wishListCart1 = new WishListCart();
    wishListCart1.setId(1L);
    WishListCart wishListCart2 = new WishListCart();
    wishListCart2.setId(wishListCart1.getId());
    assertThat(wishListCart1).isEqualTo(wishListCart2);
    wishListCart2.setId(2L);
    assertThat(wishListCart1).isNotEqualTo(wishListCart2);
    wishListCart1.setId(null);
    assertThat(wishListCart1).isNotEqualTo(wishListCart2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(WishListCartDTO.class);
    WishListCartDTO wishListCartDTO1 = new WishListCartDTO();
    wishListCartDTO1.setId(1L);
    WishListCartDTO wishListCartDTO2 = new WishListCartDTO();
    assertThat(wishListCartDTO1).isNotEqualTo(wishListCartDTO2);
    wishListCartDTO2.setId(wishListCartDTO1.getId());
    assertThat(wishListCartDTO1).isEqualTo(wishListCartDTO2);
    wishListCartDTO2.setId(2L);
    assertThat(wishListCartDTO1).isNotEqualTo(wishListCartDTO2);
    wishListCartDTO1.setId(null);
    assertThat(wishListCartDTO1).isNotEqualTo(wishListCartDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(wishListCartMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(wishListCartMapper.fromId(null)).isNull();
  }
}
