package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.ApplicantCompanyDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplicantUserDetailStrategyImplTest {

    @Mock
    private ApplicantCompanyDAO applicantRepository;

    @InjectMocks
    private ApplicantUserDetailStrategyImpl strategy;

    @Test
    void givenUserTypeApplicant_whenSupports_thenReturnTrue() {
        // Act
        boolean result = strategy.supports("applicant");

        // Assert
        assertTrue(result);
    }

    @Test
    void givenUserDetailRequest_whenGetUser_thenReturnUserEntity() {
        // Arrange
        UserDetailRequest request = UserDetailRequest.builder()
                .userId(1L)
                .build();
        ApplicantCompanyEntity mockUser = ApplicantCompanyEntity.builder()
                .id(1L)
                .name("John Doe")
                .build();
        when(applicantRepository.findById(1L)).thenReturn(mockUser);

        // Act
        UserEntity result = strategy.getUser(request);

        // Assert
        assertEquals(mockUser, result);
    }

}
