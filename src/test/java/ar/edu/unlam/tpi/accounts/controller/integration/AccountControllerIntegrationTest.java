package ar.edu.unlam.tpi.accounts.controller.integration;

import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.models.CategoryEntity;
import ar.edu.unlam.tpi.accounts.models.LabelEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.LabelDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.CategoryDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelperTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Set;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")

public class AccountControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryDAO categoryDAO;

    @Autowired
    private LabelDAO labelDAO;

    @Autowired
    private SupplierCompanyDAO supplierDAO;

    @Autowired
    private WorkerDAO workerDAO;

    @Autowired
    private ObjectMapper objectMapper;

    private SupplierCompanyEntity savedSupplier;

    @BeforeEach
    void setUp() {
        CategoryEntity category = categoryDAO.findByName("CONTRACTOR");
        LabelEntity label = labelDAO.findByTag("infrastructure_repair").orElseThrow();

        SupplierCompanyEntity supplier = SupplierCompanyHelperTest.buildTestSupplier(category, Set.of(label));
        savedSupplier = supplierDAO.save(supplier);

        WorkerEntity worker = WorkerEntity.builder()
                .name("Juan Pérez")
                .email("juan.perez@arrteh.com")
                .phone("1234567890")
                .address("Calle 123, Ciudad")
                .cuit("20-12345678-9")
                .isActive(true)
                .company(savedSupplier)
                .build();

        workerDAO.save(worker);

    }

    @Test
    void givenCategoryAndWorkResume_whenGetAllSuppliers_thenReturnsFilteredSuppliers() throws Exception {
        mockMvc.perform(get("/accounts/v1/suppliers")
                .param("category", "CONTRACTOR")
                .param("lat", "-34.6037")
                .param("ln", "-58.3816")
                .param("workResume", "infrastructure_repair")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[*].email").value(org.hamcrest.Matchers.hasItem("test@supplier.com")));
    }

    @Test
    void givenValidSupplierId_whenGetSupplierById_thenReturnsSupplierResponseDto() throws Exception {
        mockMvc.perform(get("/accounts/v1/suppliers/" + savedSupplier.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(savedSupplier.getId()))
                .andExpect(jsonPath("$.data.email").value(savedSupplier.getEmail()))
                .andExpect(jsonPath("$.data.name").value(savedSupplier.getName()))
                .andExpect(jsonPath("$.data.description").value(savedSupplier.getDescription()))
                .andExpect(jsonPath("$.data.geolocation.lat").value(savedSupplier.getGeolocation().getLat()))
                .andExpect(jsonPath("$.data.geolocation.ln").value(savedSupplier.getGeolocation().getLn()));
    }

    @Test
    void givenValidMetrics_whenUpdateSupplierMetrics_thenReturnsOk() throws Exception {
        MetricRequestDto request = new MetricRequestDto();
        request.setApplicantId(1L);
        request.setScore(5.0);
        request.setPrice(1200);
        request.setComment("Excelente servicio");

        mockMvc.perform(put("/accounts/v1/suppliers/metrics/" + savedSupplier.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("UPDATED"));
    }

    @Test
    void givenValidSupplierId_whenGetWorkersBySupplierCompanyId_thenReturnsWorkersResponseDto() throws Exception {
        mockMvc.perform(get("/accounts/v1/suppliers/" + savedSupplier.getId() + "/workers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].name").value("Juan Pérez"))
                .andExpect(jsonPath("$.data[0].email").value("juan.perez@arrteh.com"));
    }
}