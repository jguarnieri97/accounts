package ar.edu.unlam.tpi.accounts.service;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.persistence.impl.ApplicantCompanyDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.impl.SupplierCompanyDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.impl.WorkerDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.repository.CommentaryRepository;
import ar.edu.unlam.tpi.accounts.service.impl.AccountServiceImpl;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelper;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelperTest;
import ar.edu.unlam.tpi.accounts.utils.WorkerDataHelperTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    
    @Mock
    private SupplierCompanyDAOImpl supplierCompanyDAO;

    @Mock
    private WorkerDAOImpl workerDAO;

    @Mock 
    private CommentaryRepository commentaryRepository;

    @Mock
    private ApplicantCompanyDAOImpl applicantCompanyDAO;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    
    @Test
    @DisplayName("Testing searhAllSuppliers") 
    public void givenSupplierCompanyEntity_whenSearchAllSuppliers_thenReturnListOfSupplierResponseDto() {
        // Arrange
        // Mock the behavior of the supplierCompanyDAO to return a list of SupplierCompanyEntity
        when(supplierCompanyDAO.findAll()).thenReturn(SupplierCompanyHelper.getSupplierCompanies());
        
        // Act
        List<SupplierResponseDto> result = accountServiceImpl.searchAllSuppliers();
        
        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("Testing searchSupplierById")
    public void givenSupplierCompanyEntity_whenSearchSupplierById_thenReturnSupplierResponseDto() {
        // Arrange
        SupplierResponseDto expDto  = SupplierCompanyHelperTest.getSupplierDto();
        // Mock the behavior of the supplierCompanyDAO to return a specific SupplierCompanyEntity
        when(supplierCompanyDAO.findById(1L)).thenReturn(SupplierCompanyHelperTest.getSupplier());
        // Act
        SupplierResponseDto result = accountServiceImpl.searchSupplierById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(expDto.getName(), result.getName());
    }
    
    
    @Test
    @DisplayName("Testing searchSupplierById then it need to throw an exception")
    public void givenSupplierCompanyEntity_whenSearchSupplierById_thenThrowsException() {
        // Act        
        when(supplierCompanyDAO.findById(1L)).thenThrow(new NotFoundException("Supplier not found"));
        // Assert
        assertThrows(NotFoundException.class, ()->accountServiceImpl.searchSupplierById(1L));
        
    }

    @Test
    @DisplayName("Testing searchWorkersBySupplierCompanyId")
    public void givenSupplierCompanyIdWhensearchWorkersBySupplierCompanyId_thenReturnListOfWorkerResponseDto() {
        // Arrange
        List<WorkerResponseDto> expDto = WorkerDataHelperTest.getWorkersDto();

        // Mock the behavior of the workerDAO to return a list of WorkerEntity
        when(workerDAO.findByCompanyId(1L)).thenReturn(WorkerDataHelperTest.getWorkers());
        
        // Act
        List<WorkerResponseDto> result = accountServiceImpl.searchWorkersBySupplierCompanyId(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expDto.get(0).getName(), result.get(0).getName());
        assertEquals(expDto.get(1).getName(), result.get(1).getName());
    }

    @Test
    @DisplayName("Testing searchWorkersBySupplierCompanyId but it need to throw an exception")
    public void givenSupplierCompanyIdWhensearchWorkersBySupplierCompanyId_thenThrowsException() {
        // Act & Assert
        when(workerDAO.findByCompanyId(1L)).thenThrow(new NotFoundException("Workers not found for the company"));
        assertThrows(NotFoundException.class, ()->accountServiceImpl.searchWorkersBySupplierCompanyId(1L));
    }

}
