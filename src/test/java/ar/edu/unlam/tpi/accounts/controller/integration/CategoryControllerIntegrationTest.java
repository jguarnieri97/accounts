package ar.edu.unlam.tpi.accounts.controller.integration;


import ar.edu.unlam.tpi.accounts.models.CategoryEntity;
import ar.edu.unlam.tpi.accounts.models.LabelEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.CategoryDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.LabelDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoryControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private LabelDAO labelDAO;

    @BeforeEach
    void setUp() {
     
        CategoryEntity category = categoryDAO.findByName("CONTRACTOR");
        labelDAO.save(LabelEntity.builder().tag("pipe_repair").category(category).build());
        labelDAO.save(LabelEntity.builder().tag("leak_fix").category(category).build());
    }

    @Test
    void givenLabelsInDatabase_whenGetCategoryConfiguration_thenReturnsGroupedLabels() throws Exception {
        mockMvc.perform(get("/accounts/v1/category/config")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.config").exists())
                .andExpect(jsonPath("$.data.config", hasKey("CONTRACTOR")))
                .andExpect(jsonPath("$.data.config.CONTRACTOR", hasItem("wall_repair")))
                .andExpect(jsonPath("$.data.config.CONTRACTOR", hasItem("infrastructure_repair")));
    }
}