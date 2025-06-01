package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class SupplierUserDetailStrategyImplTest {

    @Mock
    private SupplierCompanyDAO supplierRepository;

    @InjectMocks
    private SupplierUserDetailStrategyImpl strategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenUserTypeSupplier_whenSupports_thenReturnTrue() {
        // Act
        boolean result = strategy.supports("supplier");

        // Assert
        assertTrue(result);
    }

    @Test
    void givenUserDetailRequest_whenGetUser_thenReturnUserEntity() {
        // Arrange
        UserDetailRequest request = UserDetailRequest.builder()
                .userId(1L)
                .build();
        SupplierCompanyEntity mockUser = new SupplierCompanyEntity();
        mockUser.setId(1L);
        mockUser.setName("Jane Doe");
        when(supplierRepository.findById(1L)).thenReturn(mockUser);

        // Act
        UserEntity result = strategy.getUser(request);

        // Assert
        assertEquals(mockUser, result);
    }
}
