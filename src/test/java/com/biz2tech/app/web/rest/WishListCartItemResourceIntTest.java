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
import com.biz2tech.app.domain.WishListCartItem;
import com.biz2tech.app.repository.WishListCartItemRepository;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.WishListCartItemService;
import com.biz2tech.app.service.dto.WishListCartItemDTO;
import com.biz2tech.app.service.mapper.WishListCartItemMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the WishListCartItemResource REST controller.
 *
 * @see WishListCartItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class WishListCartItemResourceIntTest {

  private static final Integer DEFAULT_QUANTITY = 1;
  private static final Integer UPDATED_QUANTITY = 2;

  @Autowired
  private WishListCartItemRepository wishListCartItemRepository;

  @Autowired
  private WishListCartItemMapper wishListCartItemMapper;

  @Autowired
  private WishListCartItemService wishListCartItemService;

  @Autowired
  private MappingJackson2HttpMessageConverter jacksonMessageConverter;

  @Autowired
  private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

  @Autowired
  private ExceptionTranslator exceptionTranslator;

  @Autowired
  private EntityManager em;

  private MockMvc restWishListCartItemMockMvc;

  private WishListCartItem wishListCartItem;
  
  private TokenProvider tokenProvider;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final WishListCartItemResource wishListCartItemResource =
        new WishListCartItemResource(wishListCartItemService, tokenProvider);
    this.restWishListCartItemMockMvc = MockMvcBuilders.standaloneSetup(wishListCartItemResource)
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
  public static WishListCartItem createEntity(EntityManager em) {
    WishListCartItem wishListCartItem = new WishListCartItem().quantity(DEFAULT_QUANTITY);
    return wishListCartItem;
  }

  @Before
  public void initTest() {
    wishListCartItem = createEntity(em);
  }

  @Test
  @Transactional
  public void createWishListCartItem() throws Exception {
    int databaseSizeBeforeCreate = wishListCartItemRepository.findAll().size();

    // Create the WishListCartItem
    WishListCartItemDTO wishListCartItemDTO = wishListCartItemMapper.toDto(wishListCartItem);
    restWishListCartItemMockMvc
        .perform(post("/api/wish-list-cart-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListCartItemDTO)))
        .andExpect(status().isCreated());

    // Validate the WishListCartItem in the database
    List<WishListCartItem> wishListCartItemList = wishListCartItemRepository.findAll();
    assertThat(wishListCartItemList).hasSize(databaseSizeBeforeCreate + 1);
    WishListCartItem testWishListCartItem =
        wishListCartItemList.get(wishListCartItemList.size() - 1);
    assertThat(testWishListCartItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
  }

  @Test
  @Transactional
  public void createWishListCartItemWithExistingId() throws Exception {
    int databaseSizeBeforeCreate = wishListCartItemRepository.findAll().size();

    // Create the WishListCartItem with an existing ID
    wishListCartItem.setId(1L);
    WishListCartItemDTO wishListCartItemDTO = wishListCartItemMapper.toDto(wishListCartItem);

    // An entity with an existing ID cannot be created, so this API call must fail
    restWishListCartItemMockMvc
        .perform(post("/api/wish-list-cart-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListCartItemDTO)))
        .andExpect(status().isBadRequest());

    // Validate the WishListCartItem in the database
    List<WishListCartItem> wishListCartItemList = wishListCartItemRepository.findAll();
    assertThat(wishListCartItemList).hasSize(databaseSizeBeforeCreate);
  }

  @Test
  @Transactional
  public void getAllWishListCartItems() throws Exception {
    // Initialize the database
    wishListCartItemRepository.saveAndFlush(wishListCartItem);

    // Get all the wishListCartItemList
    restWishListCartItemMockMvc.perform(get("/api/wish-list-cart-items?sort=id,desc"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.[*].id").value(hasItem(wishListCartItem.getId().intValue())))
        .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
  }

  @Test
  @Transactional
  public void getWishListCartItem() throws Exception {
    // Initialize the database
    wishListCartItemRepository.saveAndFlush(wishListCartItem);

    // Get the wishListCartItem
    restWishListCartItemMockMvc
        .perform(get("/api/wish-list-cart-items/{id}", wishListCartItem.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$.id").value(wishListCartItem.getId().intValue()))
        .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
  }

  @Test
  @Transactional
  public void getNonExistingWishListCartItem() throws Exception {
    // Get the wishListCartItem
    restWishListCartItemMockMvc.perform(get("/api/wish-list-cart-items/{id}", Long.MAX_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  public void updateWishListCartItem() throws Exception {
    // Initialize the database
    wishListCartItemRepository.saveAndFlush(wishListCartItem);

    int databaseSizeBeforeUpdate = wishListCartItemRepository.findAll().size();

    // Update the wishListCartItem
    WishListCartItem updatedWishListCartItem =
        wishListCartItemRepository.findOne(wishListCartItem.getId());
    // Disconnect from session so that the updates on updatedWishListCartItem are not directly saved
    // in db
    em.detach(updatedWishListCartItem);
    updatedWishListCartItem.quantity(UPDATED_QUANTITY);
    WishListCartItemDTO wishListCartItemDTO = wishListCartItemMapper.toDto(updatedWishListCartItem);

    restWishListCartItemMockMvc
        .perform(put("/api/wish-list-cart-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListCartItemDTO)))
        .andExpect(status().isOk());

    // Validate the WishListCartItem in the database
    List<WishListCartItem> wishListCartItemList = wishListCartItemRepository.findAll();
    assertThat(wishListCartItemList).hasSize(databaseSizeBeforeUpdate);
    WishListCartItem testWishListCartItem =
        wishListCartItemList.get(wishListCartItemList.size() - 1);
    assertThat(testWishListCartItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
  }

  @Test
  @Transactional
  public void updateNonExistingWishListCartItem() throws Exception {
    int databaseSizeBeforeUpdate = wishListCartItemRepository.findAll().size();

    // Create the WishListCartItem
    WishListCartItemDTO wishListCartItemDTO = wishListCartItemMapper.toDto(wishListCartItem);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restWishListCartItemMockMvc
        .perform(put("/api/wish-list-cart-items").contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wishListCartItemDTO)))
        .andExpect(status().isBadRequest());

    // Validate the WishListCartItem in the database
    List<WishListCartItem> wishListCartItemList = wishListCartItemRepository.findAll();
    assertThat(wishListCartItemList).hasSize(databaseSizeBeforeUpdate);
  }

  @Test
  @Transactional
  public void deleteWishListCartItem() throws Exception {
    // Initialize the database
    wishListCartItemRepository.saveAndFlush(wishListCartItem);

    int databaseSizeBeforeDelete = wishListCartItemRepository.findAll().size();

    // Get the wishListCartItem
    restWishListCartItemMockMvc
        .perform(delete("/api/wish-list-cart-items/{id}", wishListCartItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk());

    // Validate the database is empty
    List<WishListCartItem> wishListCartItemList = wishListCartItemRepository.findAll();
    assertThat(wishListCartItemList).hasSize(databaseSizeBeforeDelete - 1);
  }

  @Test
  @Transactional
  public void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(WishListCartItem.class);
    WishListCartItem wishListCartItem1 = new WishListCartItem();
    wishListCartItem1.setId(1L);
    WishListCartItem wishListCartItem2 = new WishListCartItem();
    wishListCartItem2.setId(wishListCartItem1.getId());
    assertThat(wishListCartItem1).isEqualTo(wishListCartItem2);
    wishListCartItem2.setId(2L);
    assertThat(wishListCartItem1).isNotEqualTo(wishListCartItem2);
    wishListCartItem1.setId(null);
    assertThat(wishListCartItem1).isNotEqualTo(wishListCartItem2);
  }

  @Test
  @Transactional
  public void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(WishListCartItemDTO.class);
    WishListCartItemDTO wishListCartItemDTO1 = new WishListCartItemDTO();
    wishListCartItemDTO1.setId(1L);
    WishListCartItemDTO wishListCartItemDTO2 = new WishListCartItemDTO();
    assertThat(wishListCartItemDTO1).isNotEqualTo(wishListCartItemDTO2);
    wishListCartItemDTO2.setId(wishListCartItemDTO1.getId());
    assertThat(wishListCartItemDTO1).isEqualTo(wishListCartItemDTO2);
    wishListCartItemDTO2.setId(2L);
    assertThat(wishListCartItemDTO1).isNotEqualTo(wishListCartItemDTO2);
    wishListCartItemDTO1.setId(null);
    assertThat(wishListCartItemDTO1).isNotEqualTo(wishListCartItemDTO2);
  }

  @Test
  @Transactional
  public void testEntityFromId() {
    assertThat(wishListCartItemMapper.fromId(42L).getId()).isEqualTo(42);
    assertThat(wishListCartItemMapper.fromId(null)).isNull();
  }
}
