package ar.edu.unlam.tpi.accounts.controller.integration;

import ar.edu.unlam.tpi.accounts.dto.request.EmailRequest;
import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.utils.ApplicantCompanyHelperTest;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelperTest;
import ar.edu.unlam.tpi.accounts.utils.WorkerDataHelperTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

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

    @Test
    void givenValidSupplierEmailRequest_whenGetDetails_thenReturnsUserDetailResponse() throws Exception {
        //Registro de supplier
        var registerRequest = SupplierCompanyHelperTest.buildSupplierRegisterRequest();
        mockMvc.perform(post("/accounts/v1/users/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk());

        //Request de mail
        EmailRequest request = EmailRequest.builder()
                .email(registerRequest.getEmail())
                .type(registerRequest.getType())
                .build();

        mockMvc.perform(post("/accounts/v1/users/email")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.email").value(registerRequest.getEmail()))
                .andExpect(jsonPath("$.data.name").value(registerRequest.getName()));
    }

    @Test
    void givenValidSupplierId_whenGetUsersDetails_thenReturnsUserResponse() throws Exception {
        //Registro de supplier
        UserRegisterRequestDto registerRequest = SupplierCompanyHelperTest.buildSupplierRegisterRequest();
        String saveResponse = mockMvc.perform(post("/accounts/v1/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Extraemos el ID del supplier creado
        JsonNode jsonNode = objectMapper.readTree(saveResponse);
        Long supplierId = jsonNode.get("data").get("id").asLong();

        //UserRequest con ese ID
        UserRequest userRequest = UserRequest.builder()
                .userId(supplierId)
                .type("supplier")
                .build();

        //Mandamos como lista al endpoint
        mockMvc.perform(post("/accounts/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(userRequest))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.suppliers").isArray())
                .andExpect(jsonPath("$.data.suppliers[0].id").value(supplierId))
                .andExpect(jsonPath("$.data.suppliers[0].email").value(registerRequest.getEmail()));
    }
}
    
