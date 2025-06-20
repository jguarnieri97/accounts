package ar.edu.unlam.tpi.accounts.service.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Set;

import ar.edu.unlam.tpi.accounts.models.CompanyTypeEnum;
import ar.edu.unlam.tpi.accounts.models.LabelEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.persistence.dao.impl.ApplicantCompanyDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.dao.impl.SupplierCompanyDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.dao.impl.WorkerDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.repository.CommentaryRepository;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelperTest;
import ar.edu.unlam.tpi.accounts.utils.WorkerDataHelperTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {
    
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
    void givenSupplierCompanyEntity_whenSearchSupplierById_thenReturnSupplierResponseDto() {
        // Arrange
        SupplierResponseDto expDto  = SupplierCompanyHelperTest.getSupplierDto();
        // Mock the behavior of the supplierCompanyDAO to return a specific SupplierCompanyEntity
        when(supplierCompanyDAO.findById(1L)).thenReturn(SupplierCompanyHelperTest.getSupplier());
        // Act
        SupplierResponseDto result = accountServiceImpl.getSupplierById(1L);
        
        // Assert
        assertNotNull(result);
        assertEquals(expDto.getName(), result.getName());
    }
    
    
    @Test
    void givenSupplierCompanyEntity_whenSearchSupplierById_thenThrowsException() {
        // Act        
        when(supplierCompanyDAO.findById(1L)).thenThrow(new NotFoundException("Supplier not found"));
        // Assert
        assertThrows(NotFoundException.class, ()->accountServiceImpl.getSupplierById(1L));
        
    }

    @Test
    void givenSupplierCompanyId_whenSearchWorkersBySupplierCompanyId_thenReturnListOfWorkerResponseDto() {
        // Arrange
        List<WorkerResponseDto> expDto = WorkerDataHelperTest.getWorkersDto();

        // Mock the behavior of the workerDAO to return a list of WorkerEntity
        when(workerDAO.findByCompanyId(1L)).thenReturn(WorkerDataHelperTest.getWorkers());

        // Act
        List<WorkerResponseDto> result = accountServiceImpl.getWorkersBySupplierCompanyId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expDto.get(0).getName(), result.get(0).getName());
        assertEquals(expDto.get(1).getName(), result.get(1).getName());
    }

    @Test
    void givenSupplierCompanyId_whensearchWorkersBySupplierCompanyId_thenThrowsException() {
        // Act & Assert
        when(workerDAO.findByCompanyId(1L)).thenThrow(new NotFoundException("Workers not found for the company"));
        assertThrows(NotFoundException.class, ()->accountServiceImpl.getWorkersBySupplierCompanyId(1L));
    }

   /* @Test
    void givenCategoryLocationAndWorkResume_whenGetAllSuppliers_thenReturnSupplierResponseDtoList() {
        // Given
        String category = "ELECTRICIAN";
        String workResume = "techo";
        Integer categoryEnum = CompanyTypeEnum.valueOf(category).ordinal();
        Float lat = -34.6340f;
        Float ln = -58.4065f;
        Float radius = 10f;
    
        // Simula una entidad de proveedor con un label que matchea con "techo"
        LabelEntity label = new LabelEntity();
        label.setTag("roof_repair");
    
        SupplierCompanyEntity supplier = new SupplierCompanyEntity();
        supplier.setLabels(Set.of(label)); // simulamos que tiene ese label
    
        List<SupplierCompanyEntity> suppliers = List.of(supplier);
    
        ReflectionTestUtils.setField(accountServiceImpl, "searchRadius", radius);
    
        when(supplierCompanyDAO.findByCategoryAndLatAndLn(categoryEnum, lat, ln, radius))
            .thenReturn(suppliers);
    
        // When
        List<SupplierResponseDto> result = accountServiceImpl.getAllSuppliers(category, lat, ln, workResume);
    
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(supplierCompanyDAO).findByCategoryAndLatAndLn(categoryEnum, lat, ln, radius);
    }*/
    

}
