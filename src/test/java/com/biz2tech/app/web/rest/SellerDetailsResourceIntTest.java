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
import com.biz2tech.app.domain.SellerDetails;
import com.biz2tech.app.repository.SellerDetailsRepository;
import com.biz2tech.app.security.jwt.TokenProvider;
import com.biz2tech.app.service.SellerDetailsService;
import com.biz2tech.app.service.dto.SellerDetailsDTO;
import com.biz2tech.app.service.mapper.SellerDetailsMapper;
import com.biz2tech.app.web.rest.errors.ExceptionTranslator;

/**
 * Test class for the SellerDetailsResource REST controller.
 *
 * @see SellerDetailsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FragrancenetserviceApp.class)
public class SellerDetailsResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private SellerDetailsRepository sellerDetailsRepository;

    @Autowired
    private SellerDetailsMapper sellerDetailsMapper;

    @Autowired
    private SellerDetailsService sellerDetailsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSellerDetailsMockMvc;

    private SellerDetails sellerDetails;
    
    @Autowired
    private TokenProvider tokenProvider;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SellerDetailsResource sellerDetailsResource = new SellerDetailsResource(sellerDetailsService,tokenProvider);
        this.restSellerDetailsMockMvc = MockMvcBuilders.standaloneSetup(sellerDetailsResource)
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
    public static SellerDetails createEntity(EntityManager em) {
        SellerDetails sellerDetails = new SellerDetails()
            .name(DEFAULT_NAME)
            .address(DEFAULT_ADDRESS);
        return sellerDetails;
    }

    @Before
    public void initTest() {
        sellerDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createSellerDetails() throws Exception {
        int databaseSizeBeforeCreate = sellerDetailsRepository.findAll().size();

        // Create the SellerDetails
        SellerDetailsDTO sellerDetailsDTO = sellerDetailsMapper.toDto(sellerDetails);
        restSellerDetailsMockMvc.perform(post("/api/seller-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the SellerDetails in the database
        List<SellerDetails> sellerDetailsList = sellerDetailsRepository.findAll();
        assertThat(sellerDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SellerDetails testSellerDetails = sellerDetailsList.get(sellerDetailsList.size() - 1);
        assertThat(testSellerDetails.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSellerDetails.getAddress()).isEqualTo(DEFAULT_ADDRESS);
    }

    @Test
    @Transactional
    public void createSellerDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sellerDetailsRepository.findAll().size();

        // Create the SellerDetails with an existing ID
        sellerDetails.setId(1L);
        SellerDetailsDTO sellerDetailsDTO = sellerDetailsMapper.toDto(sellerDetails);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSellerDetailsMockMvc.perform(post("/api/seller-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerDetailsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SellerDetails in the database
        List<SellerDetails> sellerDetailsList = sellerDetailsRepository.findAll();
        assertThat(sellerDetailsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSellerDetails() throws Exception {
        // Initialize the database
        sellerDetailsRepository.saveAndFlush(sellerDetails);

        // Get all the sellerDetailsList
        restSellerDetailsMockMvc.perform(get("/api/seller-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sellerDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void getSellerDetails() throws Exception {
        // Initialize the database
        sellerDetailsRepository.saveAndFlush(sellerDetails);

        // Get the sellerDetails
        restSellerDetailsMockMvc.perform(get("/api/seller-details/{id}", sellerDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sellerDetails.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingSellerDetails() throws Exception {
        // Get the sellerDetails
        restSellerDetailsMockMvc.perform(get("/api/seller-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSellerDetails() throws Exception {
        // Initialize the database
        sellerDetailsRepository.saveAndFlush(sellerDetails);
        int databaseSizeBeforeUpdate = sellerDetailsRepository.findAll().size();

        // Update the sellerDetails
        SellerDetails updatedSellerDetails = sellerDetailsRepository.findOne(sellerDetails.getId());
        // Disconnect from session so that the updates on updatedSellerDetails are not directly saved in db
        em.detach(updatedSellerDetails);
        updatedSellerDetails
            .name(UPDATED_NAME)
            .address(UPDATED_ADDRESS);
        SellerDetailsDTO sellerDetailsDTO = sellerDetailsMapper.toDto(updatedSellerDetails);

        restSellerDetailsMockMvc.perform(put("/api/seller-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerDetailsDTO)))
            .andExpect(status().isOk());

        // Validate the SellerDetails in the database
        List<SellerDetails> sellerDetailsList = sellerDetailsRepository.findAll();
        assertThat(sellerDetailsList).hasSize(databaseSizeBeforeUpdate);
        SellerDetails testSellerDetails = sellerDetailsList.get(sellerDetailsList.size() - 1);
        assertThat(testSellerDetails.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSellerDetails.getAddress()).isEqualTo(UPDATED_ADDRESS);
    }

    @Test
    @Transactional
    public void updateNonExistingSellerDetails() throws Exception {
        int databaseSizeBeforeUpdate = sellerDetailsRepository.findAll().size();

        // Create the SellerDetails
        SellerDetailsDTO sellerDetailsDTO = sellerDetailsMapper.toDto(sellerDetails);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSellerDetailsMockMvc.perform(put("/api/seller-details")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sellerDetailsDTO)))
            .andExpect(status().isCreated());

        // Validate the SellerDetails in the database
        List<SellerDetails> sellerDetailsList = sellerDetailsRepository.findAll();
        assertThat(sellerDetailsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSellerDetails() throws Exception {
        // Initialize the database
        sellerDetailsRepository.saveAndFlush(sellerDetails);
        int databaseSizeBeforeDelete = sellerDetailsRepository.findAll().size();

        // Get the sellerDetails
        restSellerDetailsMockMvc.perform(delete("/api/seller-details/{id}", sellerDetails.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SellerDetails> sellerDetailsList = sellerDetailsRepository.findAll();
        assertThat(sellerDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SellerDetails.class);
        SellerDetails sellerDetails1 = new SellerDetails();
        sellerDetails1.setId(1L);
        SellerDetails sellerDetails2 = new SellerDetails();
        sellerDetails2.setId(sellerDetails1.getId());
        assertThat(sellerDetails1).isEqualTo(sellerDetails2);
        sellerDetails2.setId(2L);
        assertThat(sellerDetails1).isNotEqualTo(sellerDetails2);
        sellerDetails1.setId(null);
        assertThat(sellerDetails1).isNotEqualTo(sellerDetails2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SellerDetailsDTO.class);
        SellerDetailsDTO sellerDetailsDTO1 = new SellerDetailsDTO();
        sellerDetailsDTO1.setId(1L);
        SellerDetailsDTO sellerDetailsDTO2 = new SellerDetailsDTO();
        assertThat(sellerDetailsDTO1).isNotEqualTo(sellerDetailsDTO2);
        sellerDetailsDTO2.setId(sellerDetailsDTO1.getId());
        assertThat(sellerDetailsDTO1).isEqualTo(sellerDetailsDTO2);
        sellerDetailsDTO2.setId(2L);
        assertThat(sellerDetailsDTO1).isNotEqualTo(sellerDetailsDTO2);
        sellerDetailsDTO1.setId(null);
        assertThat(sellerDetailsDTO1).isNotEqualTo(sellerDetailsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(sellerDetailsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(sellerDetailsMapper.fromId(null)).isNull();
    }
}
