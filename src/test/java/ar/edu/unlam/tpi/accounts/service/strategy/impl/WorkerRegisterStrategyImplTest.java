package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WorkerRegisterStrategyImplTest {

    @Mock
    private WorkerDAO workerDao;

    @Mock
    private SupplierCompanyDAO supplierDao;

    @InjectMocks
    private WorkerRegisterStrategyImpl strategy;

    private UserRegisterRequestDto request;
    private SupplierCompanyEntity company;

    @BeforeEach
    void setUp() {
        request = new UserRegisterRequestDto();
        request.setType("worker");
        request.setEmail("worker@mail.com");
        request.setName("Trabajador");
        request.setPhone("123456789");
        request.setAddress("Calle 123");
        request.setCuit("20-11223344-5");
        request.setCompanyId(100L);

        company = new SupplierCompanyEntity();
        company.setId(100L);
        company.setName("Proveedor Asociado");
    }

    @Test
    void givenValidRequest_whenRegister_thenReturnsUserCreatedResponse() {
        // Arrange
        when(supplierDao.findById(100L)).thenReturn(company);
        WorkerEntity saved = WorkerEntity.builder().id(99L).build();
        when(workerDao.save(any(WorkerEntity.class))).thenReturn(saved);

        // Act
        UserCreatedResponse response = strategy.register(request);

        // Assert
        assertNotNull(response);
        assertEquals(99L, response.getId());
        verify(workerDao).save(any(WorkerEntity.class));
    }

    @Test
    void givenNonExistingCompany_whenRegister_thenThrowsNotFoundException() {
        // Arrange
        when(supplierDao.findById(100L)).thenReturn(null);

        // Act & Assert
        assertThrows(NotFoundException.class, () -> strategy.register(request));
    }

    @Test
    void givenWorkerType_whenSupports_thenReturnTrue() {
        assertTrue(strategy.supports("worker"));
    }

    @Test
    void givenOtherType_whenSupports_thenReturnFalse() {
        assertFalse(strategy.supports("applicant"));
    }
}
