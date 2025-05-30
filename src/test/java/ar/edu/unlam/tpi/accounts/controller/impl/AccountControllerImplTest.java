package ar.edu.unlam.tpi.accounts.controller.impl;

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
public class AccountControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenNoParameters_whenGetAllSuppliers_thenReturnSupplierList() throws Exception {
        // Given: No se requieren parámetros adicionales

        // When: Se realiza una solicitud GET para obtener todos los proveedores
        mockMvc.perform(get("/accounts/v1/suppliers"))
                // Then: Se espera que la respuesta sea exitosa y contenga un array de proveedores
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    void givenSupplierId_whenGetSupplierById_thenReturnSupplierDetails() throws Exception {
        // Given: Un ID de proveedor válido
        Long supplierId = 1L;

        // When: Se realiza una solicitud GET para obtener los detalles del proveedor
        mockMvc.perform(get("/accounts/v1/suppliers/{id}", supplierId))
                // Then: Se espera que la respuesta sea exitosa y contenga los detalles del proveedor
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").value(supplierId));
    }

    @Test
    void givenSupplierIdAndMetrics_whenUpdateMetrics_thenReturnUpdatedMessage() throws Exception {
        // Given: Métricas válidas para un proveedor
        MetricRequestDto metrics = new MetricRequestDto();
        metrics.setApplicantId(1L);
        metrics.setPrice(2000);
        metrics.setScore(4.7);

        // When: Se realiza una solicitud PUT para actualizar las métricas del proveedor
        mockMvc.perform(put("/accounts/v1/suppliers/metrics/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(metrics)))
                // Then: Se espera que la respuesta sea exitosa y contenga un mensaje de actualización
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("UPDATED"));
    }

    @Test
    void givenSupplierId_whenGetWorkers_thenReturnWorkersList() throws Exception {
        // Given: Un ID de proveedor válido
        Long supplierId = 1L;

        // When: Se realiza una solicitud GET para obtener los trabajadores del proveedor
        mockMvc.perform(get("/accounts/v1/suppliers/{id}/workers", supplierId))
                // Then: Se espera que la respuesta sea exitosa y contenga un array de trabajadores
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data").isArray());
    }

}
