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
import com.biz2tech.app.domain.ShoppingCartItem;
import com.biz2tech.app.repository.ShoppingCartItemRepository;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.ShoppingCartItemService;
import com.biz2tech.app.service.dto.ShoppingCartItemDTO;
import com.biz2tech.app.service.mapper.ShoppingCartItemMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the ShoppingCartItemResource REST controller.
 *
 * @see ShoppingCartItemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class ShoppingCartItemResourceIntTest {

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private ShoppingCartItemMapper shoppingCartItemMapper;

    @Autowired
    private ShoppingCartItemService shoppingCartItemService;
    
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

    private MockMvc restShoppingCartItemMockMvc;

    private ShoppingCartItem shoppingCartItem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ShoppingCartItemResource shoppingCartItemResource = new ShoppingCartItemResource(shoppingCartItemService,tokenProvider);
        this.restShoppingCartItemMockMvc = MockMvcBuilders.standaloneSetup(shoppingCartItemResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ShoppingCartItem createEntity(EntityManager em) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem()
            .quantity(DEFAULT_QUANTITY);
        return shoppingCartItem;
    }

    @Before
    public void initTest() {
        shoppingCartItem = createEntity(em);
    }

    @Test
    @Transactional
    public void createShoppingCartItem() throws Exception {
        int databaseSizeBeforeCreate = shoppingCartItemRepository.findAll().size();

        // Create the ShoppingCartItem
        ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartItemMapper.toDto(shoppingCartItem);
        restShoppingCartItemMockMvc.perform(post("/api/shopping-cart-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ShoppingCartItem in the database
        List<ShoppingCartItem> shoppingCartItemList = shoppingCartItemRepository.findAll();
        assertThat(shoppingCartItemList).hasSize(databaseSizeBeforeCreate + 1);
        ShoppingCartItem testShoppingCartItem = shoppingCartItemList.get(shoppingCartItemList.size() - 1);
        assertThat(testShoppingCartItem.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createShoppingCartItemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = shoppingCartItemRepository.findAll().size();

        // Create the ShoppingCartItem with an existing ID
        shoppingCartItem.setId(1L);
        ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartItemMapper.toDto(shoppingCartItem);

        // An entity with an existing ID cannot be created, so this API call must fail
        restShoppingCartItemMockMvc.perform(post("/api/shopping-cart-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartItemDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ShoppingCartItem in the database
        List<ShoppingCartItem> shoppingCartItemList = shoppingCartItemRepository.findAll();
        assertThat(shoppingCartItemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllShoppingCartItems() throws Exception {
        // Initialize the database
        shoppingCartItemRepository.saveAndFlush(shoppingCartItem);

        // Get all the shoppingCartItemList
        restShoppingCartItemMockMvc.perform(get("/api/shopping-cart-items?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(shoppingCartItem.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }

    @Test
    @Transactional
    public void getShoppingCartItem() throws Exception {
        // Initialize the database
        shoppingCartItemRepository.saveAndFlush(shoppingCartItem);

        // Get the shoppingCartItem
        restShoppingCartItemMockMvc.perform(get("/api/shopping-cart-items/{id}", shoppingCartItem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(shoppingCartItem.getId().intValue()))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }

    @Test
    @Transactional
    public void getNonExistingShoppingCartItem() throws Exception {
        // Get the shoppingCartItem
        restShoppingCartItemMockMvc.perform(get("/api/shopping-cart-items/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateShoppingCartItem() throws Exception {
        // Initialize the database
        shoppingCartItemRepository.saveAndFlush(shoppingCartItem);
        int databaseSizeBeforeUpdate = shoppingCartItemRepository.findAll().size();

        // Update the shoppingCartItem
        ShoppingCartItem updatedShoppingCartItem = shoppingCartItemRepository.findOne(shoppingCartItem.getId());
        // Disconnect from session so that the updates on updatedShoppingCartItem are not directly saved in db
        em.detach(updatedShoppingCartItem);
        updatedShoppingCartItem
            .quantity(UPDATED_QUANTITY);
        ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartItemMapper.toDto(updatedShoppingCartItem);

        restShoppingCartItemMockMvc.perform(put("/api/shopping-cart-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartItemDTO)))
            .andExpect(status().isOk());

        // Validate the ShoppingCartItem in the database
        List<ShoppingCartItem> shoppingCartItemList = shoppingCartItemRepository.findAll();
        assertThat(shoppingCartItemList).hasSize(databaseSizeBeforeUpdate);
        ShoppingCartItem testShoppingCartItem = shoppingCartItemList.get(shoppingCartItemList.size() - 1);
        assertThat(testShoppingCartItem.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingShoppingCartItem() throws Exception {
        int databaseSizeBeforeUpdate = shoppingCartItemRepository.findAll().size();

        // Create the ShoppingCartItem
        ShoppingCartItemDTO shoppingCartItemDTO = shoppingCartItemMapper.toDto(shoppingCartItem);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restShoppingCartItemMockMvc.perform(put("/api/shopping-cart-items")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(shoppingCartItemDTO)))
            .andExpect(status().isCreated());

        // Validate the ShoppingCartItem in the database
        List<ShoppingCartItem> shoppingCartItemList = shoppingCartItemRepository.findAll();
        assertThat(shoppingCartItemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteShoppingCartItem() throws Exception {
        // Initialize the database
        shoppingCartItemRepository.saveAndFlush(shoppingCartItem);
        int databaseSizeBeforeDelete = shoppingCartItemRepository.findAll().size();

        // Get the shoppingCartItem
        restShoppingCartItemMockMvc.perform(delete("/api/shopping-cart-items/{id}", shoppingCartItem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ShoppingCartItem> shoppingCartItemList = shoppingCartItemRepository.findAll();
        assertThat(shoppingCartItemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingCartItem.class);
        ShoppingCartItem shoppingCartItem1 = new ShoppingCartItem();
        shoppingCartItem1.setId(1L);
        ShoppingCartItem shoppingCartItem2 = new ShoppingCartItem();
        shoppingCartItem2.setId(shoppingCartItem1.getId());
        assertThat(shoppingCartItem1).isEqualTo(shoppingCartItem2);
        shoppingCartItem2.setId(2L);
        assertThat(shoppingCartItem1).isNotEqualTo(shoppingCartItem2);
        shoppingCartItem1.setId(null);
        assertThat(shoppingCartItem1).isNotEqualTo(shoppingCartItem2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ShoppingCartItemDTO.class);
        ShoppingCartItemDTO shoppingCartItemDTO1 = new ShoppingCartItemDTO();
        shoppingCartItemDTO1.setId(1L);
        ShoppingCartItemDTO shoppingCartItemDTO2 = new ShoppingCartItemDTO();
        assertThat(shoppingCartItemDTO1).isNotEqualTo(shoppingCartItemDTO2);
        shoppingCartItemDTO2.setId(shoppingCartItemDTO1.getId());
        assertThat(shoppingCartItemDTO1).isEqualTo(shoppingCartItemDTO2);
        shoppingCartItemDTO2.setId(2L);
        assertThat(shoppingCartItemDTO1).isNotEqualTo(shoppingCartItemDTO2);
        shoppingCartItemDTO1.setId(null);
        assertThat(shoppingCartItemDTO1).isNotEqualTo(shoppingCartItemDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(shoppingCartItemMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(shoppingCartItemMapper.fromId(null)).isNull();
    }
}
