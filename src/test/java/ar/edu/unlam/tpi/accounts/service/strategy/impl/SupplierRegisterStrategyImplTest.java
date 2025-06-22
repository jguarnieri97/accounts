package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.*;
import ar.edu.unlam.tpi.accounts.persistence.dao.CategoryDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.LabelDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public  class SupplierRegisterStrategyImplTest {

    @Mock
    private SupplierCompanyDAO supplierDao;

    @Mock
    private CategoryDAO categoryDAO;

    @Mock
    private LabelDAO labelDAO;

    @InjectMocks
    private SupplierRegisterStrategyImpl strategy;

    private UserRegisterRequestDto validRequest;

    @BeforeEach
    void setUp() {
        validRequest = new UserRegisterRequestDto();
        validRequest.setType("supplier");
        validRequest.setEmail("test@supplier.com");
        validRequest.setName("Proveedor Ejemplo");
        validRequest.setPhone("123456789");
        validRequest.setAddress("Calle Falsa 123");
        validRequest.setCuit("30-12345678-9");
        validRequest.setLat(-34.60f);
        validRequest.setLn(-58.38f);
        validRequest.setDescription("Proveedor eléctrico");
        validRequest.setCategory("ELECTRICIAN");
        validRequest.setLabels(List.of("electrical_wiring_repair"));
    }

    @Test
    void givenValidRequest_whenRegister_thenReturnsUserCreatedResponse() {
        // Arrange
        CategoryEntity category = new CategoryEntity();
        LabelEntity label = new LabelEntity();
        label.setTag("electrical_wiring_repair");

        when(categoryDAO.findByName("ELECTRICIAN")).thenReturn(category);
        when(labelDAO.findByTag("electrical_wiring_repair")).thenReturn(Optional.of(label));

        SupplierCompanyEntity saved = SupplierCompanyEntity.builder().id(42L).build();
        when(supplierDao.save(any(SupplierCompanyEntity.class))).thenReturn(saved);

        // Act
        UserCreatedResponse response = strategy.register(validRequest);

        // Assert
        assertNotNull(response);
        assertEquals(42L, response.getId());
        verify(supplierDao).save(any(SupplierCompanyEntity.class));
    }

    @Test
    void givenNonExistingCategory_whenRegister_thenThrowsNotFoundException() {
        // Arrange
        when(categoryDAO.findByName("ELECTRICIAN")).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> strategy.register(validRequest));
    }

    @Test
    void givenInvalidLabel_whenRegister_thenThrowsNotFoundException() {
        // Arrange
        CategoryEntity category = new CategoryEntity();
        when(categoryDAO.findByName("ELECTRICIAN")).thenReturn(category);
        when(labelDAO.findByTag("electrical_wiring_repair")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> strategy.register(validRequest));
    }

    @Test
    void givenTypeSupplier_whenSupports_thenReturnsTrue() {
        assertTrue(strategy.supports("supplier"));
    }

    @Test
    void givenTypeOther_whenSupports_thenReturnsFalse() {
        assertFalse(strategy.supports("worker"));
    }
}
