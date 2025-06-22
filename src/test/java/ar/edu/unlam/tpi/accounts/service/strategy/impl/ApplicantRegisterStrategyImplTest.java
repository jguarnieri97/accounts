package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.ApplicantCompanyDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.* ;

@ExtendWith(MockitoExtension.class)
class ApplicantRegisterStrategyImplTest {

    @Mock
    private ApplicantCompanyDAO applicantDao;

    @InjectMocks
    private ApplicantRegisterStrategyImpl strategy;

    private UserRegisterRequestDto request;

    @BeforeEach
    void setUp() {
        request = new UserRegisterRequestDto();
        request.setType("applicant");
        request.setEmail("applicant@mail.com");
        request.setName("Empresa Solicitante");
        request.setPhone("1122334455");
        request.setAddress("Av. Empresa 456");
        request.setCuit("30-98765432-1");
        request.setLat(-34.6f);
        request.setLn(-58.4f);
        request.setDescription("Empresa que solicita servicios técnicos");
        request.setCategory("SERVICIOS");
    }

    @Test
    void givenValidRequest_whenRegister_thenReturnsUserCreatedResponse() {
        // Arrange
        ApplicantCompanyEntity saved = ApplicantCompanyEntity.builder().id(101L).build();
        when(applicantDao.save(any(ApplicantCompanyEntity.class))).thenReturn(saved);

        // Act
        UserCreatedResponse response = strategy.register(request);

        // Assert
        assertNotNull(response);
        assertEquals(101L, response.getId());
        verify(applicantDao).save(any(ApplicantCompanyEntity.class));
    }

    @Test
    void givenApplicantType_whenSupports_thenReturnTrue() {
        assertTrue(strategy.supports("applicant"));
    }

    @Test
    void givenOtherType_whenSupports_thenReturnFalse() {
        assertFalse(strategy.supports("supplier"));
    }
}
