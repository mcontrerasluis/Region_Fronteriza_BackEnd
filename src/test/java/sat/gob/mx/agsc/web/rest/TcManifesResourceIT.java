package sat.gob.mx.agsc.web.rest;

import sat.gob.mx.agsc.ServiciosApp;
import sat.gob.mx.agsc.config.TestSecurityConfiguration;
import sat.gob.mx.agsc.domain.TcManifes;
import sat.gob.mx.agsc.repository.TcManifesRepository;
import sat.gob.mx.agsc.service.TcManifesService;
import sat.gob.mx.agsc.service.dto.TcManifesDTO;
import sat.gob.mx.agsc.service.mapper.TcManifesMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TcManifesResource} REST controller.
 */
@SpringBootTest(classes = { ServiciosApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class TcManifesResourceIT {

    private static final String DEFAULT_CLAVE = "AAAAAAAAAA";
    private static final String UPDATED_CLAVE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final Integer DEFAULT_MORAL = 1;
    private static final Integer UPDATED_MORAL = 2;

    private static final Integer DEFAULT_FISICA = 1;
    private static final Integer UPDATED_FISICA = 2;

    private static final Integer DEFAULT_ISR = 1;
    private static final Integer UPDATED_ISR = 2;

    private static final Integer DEFAULT_IVA = 1;
    private static final Integer UPDATED_IVA = 2;

    @Autowired
    private TcManifesRepository tcManifesRepository;

    @Autowired
    private TcManifesMapper tcManifesMapper;

    @Autowired
    private TcManifesService tcManifesService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTcManifesMockMvc;

    private TcManifes tcManifes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TcManifes createEntity(EntityManager em) {
        TcManifes tcManifes = new TcManifes()
            .clave(DEFAULT_CLAVE)
            .descripcion(DEFAULT_DESCRIPCION)
            .moral(DEFAULT_MORAL)
            .fisica(DEFAULT_FISICA)
            .isr(DEFAULT_ISR)
            .iva(DEFAULT_IVA);
        return tcManifes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TcManifes createUpdatedEntity(EntityManager em) {
        TcManifes tcManifes = new TcManifes()
            .clave(UPDATED_CLAVE)
            .descripcion(UPDATED_DESCRIPCION)
            .moral(UPDATED_MORAL)
            .fisica(UPDATED_FISICA)
            .isr(UPDATED_ISR)
            .iva(UPDATED_IVA);
        return tcManifes;
    }

    @BeforeEach
    public void initTest() {
        tcManifes = createEntity(em);
    }

    @Test
    @Transactional
    public void createTcManifes() throws Exception {
        int databaseSizeBeforeCreate = tcManifesRepository.findAll().size();
        // Create the TcManifes
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);
        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isCreated());

        // Validate the TcManifes in the database
        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeCreate + 1);
        TcManifes testTcManifes = tcManifesList.get(tcManifesList.size() - 1);
        assertThat(testTcManifes.getClave()).isEqualTo(DEFAULT_CLAVE);
        assertThat(testTcManifes.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testTcManifes.getMoral()).isEqualTo(DEFAULT_MORAL);
        assertThat(testTcManifes.getFisica()).isEqualTo(DEFAULT_FISICA);
        assertThat(testTcManifes.getIsr()).isEqualTo(DEFAULT_ISR);
        assertThat(testTcManifes.getIva()).isEqualTo(DEFAULT_IVA);
    }

    @Test
    @Transactional
    public void createTcManifesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tcManifesRepository.findAll().size();

        // Create the TcManifes with an existing ID
        tcManifes.setId(1L);
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TcManifes in the database
        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkClaveIsRequired() throws Exception {
        int databaseSizeBeforeTest = tcManifesRepository.findAll().size();
        // set the field null
        tcManifes.setClave(null);

        // Create the TcManifes, which fails.
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);


        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescripcionIsRequired() throws Exception {
        int databaseSizeBeforeTest = tcManifesRepository.findAll().size();
        // set the field null
        tcManifes.setDescripcion(null);

        // Create the TcManifes, which fails.
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);


        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMoralIsRequired() throws Exception {
        int databaseSizeBeforeTest = tcManifesRepository.findAll().size();
        // set the field null
        tcManifes.setMoral(null);

        // Create the TcManifes, which fails.
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);


        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFisicaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tcManifesRepository.findAll().size();
        // set the field null
        tcManifes.setFisica(null);

        // Create the TcManifes, which fails.
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);


        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsrIsRequired() throws Exception {
        int databaseSizeBeforeTest = tcManifesRepository.findAll().size();
        // set the field null
        tcManifes.setIsr(null);

        // Create the TcManifes, which fails.
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);


        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIvaIsRequired() throws Exception {
        int databaseSizeBeforeTest = tcManifesRepository.findAll().size();
        // set the field null
        tcManifes.setIva(null);

        // Create the TcManifes, which fails.
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);


        restTcManifesMockMvc.perform(post("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllTcManifes() throws Exception {
        // Initialize the database
        tcManifesRepository.saveAndFlush(tcManifes);

        // Get all the tcManifesList
        restTcManifesMockMvc.perform(get("/api/tc-manifes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tcManifes.getId().intValue())))
            .andExpect(jsonPath("$.[*].clave").value(hasItem(DEFAULT_CLAVE)))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION)))
            .andExpect(jsonPath("$.[*].moral").value(hasItem(DEFAULT_MORAL)))
            .andExpect(jsonPath("$.[*].fisica").value(hasItem(DEFAULT_FISICA)))
            .andExpect(jsonPath("$.[*].isr").value(hasItem(DEFAULT_ISR)))
            .andExpect(jsonPath("$.[*].iva").value(hasItem(DEFAULT_IVA)));
    }
    
    @Test
    @Transactional
    public void getTcManifes() throws Exception {
        // Initialize the database
        tcManifesRepository.saveAndFlush(tcManifes);

        // Get the tcManifes
        restTcManifesMockMvc.perform(get("/api/tc-manifes/{id}", tcManifes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tcManifes.getId().intValue()))
            .andExpect(jsonPath("$.clave").value(DEFAULT_CLAVE))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION))
            .andExpect(jsonPath("$.moral").value(DEFAULT_MORAL))
            .andExpect(jsonPath("$.fisica").value(DEFAULT_FISICA))
            .andExpect(jsonPath("$.isr").value(DEFAULT_ISR))
            .andExpect(jsonPath("$.iva").value(DEFAULT_IVA));
    }
    @Test
    @Transactional
    public void getNonExistingTcManifes() throws Exception {
        // Get the tcManifes
        restTcManifesMockMvc.perform(get("/api/tc-manifes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTcManifes() throws Exception {
        // Initialize the database
        tcManifesRepository.saveAndFlush(tcManifes);

        int databaseSizeBeforeUpdate = tcManifesRepository.findAll().size();

        // Update the tcManifes
        TcManifes updatedTcManifes = tcManifesRepository.findById(tcManifes.getId()).get();
        // Disconnect from session so that the updates on updatedTcManifes are not directly saved in db
        em.detach(updatedTcManifes);
        updatedTcManifes
            .clave(UPDATED_CLAVE)
            .descripcion(UPDATED_DESCRIPCION)
            .moral(UPDATED_MORAL)
            .fisica(UPDATED_FISICA)
            .isr(UPDATED_ISR)
            .iva(UPDATED_IVA);
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(updatedTcManifes);

        restTcManifesMockMvc.perform(put("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isOk());

        // Validate the TcManifes in the database
        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeUpdate);
        TcManifes testTcManifes = tcManifesList.get(tcManifesList.size() - 1);
        assertThat(testTcManifes.getClave()).isEqualTo(UPDATED_CLAVE);
        assertThat(testTcManifes.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testTcManifes.getMoral()).isEqualTo(UPDATED_MORAL);
        assertThat(testTcManifes.getFisica()).isEqualTo(UPDATED_FISICA);
        assertThat(testTcManifes.getIsr()).isEqualTo(UPDATED_ISR);
        assertThat(testTcManifes.getIva()).isEqualTo(UPDATED_IVA);
    }

    @Test
    @Transactional
    public void updateNonExistingTcManifes() throws Exception {
        int databaseSizeBeforeUpdate = tcManifesRepository.findAll().size();

        // Create the TcManifes
        TcManifesDTO tcManifesDTO = tcManifesMapper.toDto(tcManifes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTcManifesMockMvc.perform(put("/api/tc-manifes").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(tcManifesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TcManifes in the database
        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTcManifes() throws Exception {
        // Initialize the database
        tcManifesRepository.saveAndFlush(tcManifes);

        int databaseSizeBeforeDelete = tcManifesRepository.findAll().size();

        // Delete the tcManifes
        restTcManifesMockMvc.perform(delete("/api/tc-manifes/{id}", tcManifes.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TcManifes> tcManifesList = tcManifesRepository.findAll();
        assertThat(tcManifesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
