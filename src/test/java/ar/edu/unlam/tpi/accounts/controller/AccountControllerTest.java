package ar.edu.unlam.tpi.accounts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllSuppliers() throws Exception {
        mockMvc.perform(get("/accounts/v1/suppliers"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnSupplierById() throws Exception {
        mockMvc.perform(get("/accounts/v1/suppliers/{id}", 1L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data.id").value(1L));
    }

    @Test
    void shouldUpdateSupplierMetrics() throws Exception {
        MetricRequestDto metrics = new MetricRequestDto();
        metrics.setApplicantId(1L);
        metrics.setPrice(2000);
        metrics.setScore(4.7);

        mockMvc.perform(put("/accounts/v1/suppliers/metrics/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(metrics)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").value("UPDATED"));
    }

    @Test
    void shouldReturnWorkersBySupplierId() throws Exception {
        mockMvc.perform(get("/accounts/v1/suppliers/{id}/workers", 1L))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.data").isArray());
    }
}
