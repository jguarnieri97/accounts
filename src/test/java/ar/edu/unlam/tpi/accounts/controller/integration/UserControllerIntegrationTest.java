package ar.edu.unlam.tpi.accounts.controller.integration;
import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.utils.ApplicantCompanyHelperTest;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelperTest;
import ar.edu.unlam.tpi.accounts.utils.WorkerDataHelperTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenValidSupplierRequest_whenRegisterSupplier_thenReturnsCreated() throws Exception {
        UserRegisterRequestDto request = SupplierCompanyHelperTest.buildSupplierRegisterRequest();

        mockMvc.perform(post("/accounts/v1/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").exists());
    }


    @Test
    void givenValidApplicantRequest_whenRegisterApplicant_thenReturnsCreated() throws Exception {
        UserRegisterRequestDto request = ApplicantCompanyHelperTest.buildApplicantRegisterRequest();

        mockMvc.perform(post("/accounts/v1/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").exists());
    }

    @Test
    void givenValidWorkerRequest_whenRegisterWorker_thenReturnsCreated() throws Exception {
        UserRegisterRequestDto request = WorkerDataHelperTest.buildWorkerRegisterRequest();
        mockMvc.perform(post("/accounts/v1/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.id").exists());
    }
}
