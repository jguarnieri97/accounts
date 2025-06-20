package ar.edu.unlam.tpi.accounts.controller.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.service.AccountService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class AccountControllerImplTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountControllerImpl accountController;

    @Test
void givenCategoryAndLocationAndWorkResume_whenGetAllSuppliers_thenReturnSupplierList() {
    // Given
    String category = "AGRO";
    Float lat = -34.6340f;
    Float ln = -58.4065f;
    String workResume = "techo";

    List<SupplierResponseDto> suppliers = List.of(new SupplierResponseDto());

    // Mock
    when(accountService.getAllSuppliers(category, lat, ln, workResume)).thenReturn(suppliers);

    // When
    GenericResponse<List<SupplierResponseDto>> response =
        accountController.getAllSuppliers(category, lat, ln, workResume);

    // Then
    assertNotNull(response);
    assertEquals(Constants.STATUS_OK, response.getCode());
    assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
    assertEquals(suppliers, response.getData());
}

    @Test
    void givenSupplierId_whenGetSupplierById_thenReturnSupplierDetails() {
        // Given
        Long supplierId = 1L;
        SupplierResponseDto supplier = new SupplierResponseDto();
        when(accountService.getSupplierById(supplierId)).thenReturn(supplier);

        // When
        GenericResponse<SupplierResponseDto> response = accountController.getSupplierById(supplierId);

        // Then
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(supplier, response.getData());
    }

    @Test
    void givenSupplierIdAndMetrics_whenPutSupplierMetrics_thenReturnUpdatedMessage() {
        // Given
        Long supplierId = 1L;
        MetricRequestDto metrics = new MetricRequestDto();

        // When
        GenericResponse<Void> response = accountController.putSupplierMetrics(supplierId, metrics);

        // Then
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.UPDATE_MESSAGE, response.getMessage());
        assertNull(response.getData());
        verify(accountService).updateSupplierMetrics(supplierId, metrics);
    }

    @Test
    void givenSupplierId_whenGetWorkersBySupplierCompanyId_thenReturnWorkersList() {
        // Given
        Long supplierId = 1L;
        List<WorkerResponseDto> workers = List.of(new WorkerResponseDto());
        when(accountService.getWorkersBySupplierCompanyId(supplierId)).thenReturn(workers);

        // When
        GenericResponse<List<WorkerResponseDto>> response = accountController.getWorkersBySupplierCompanyId(supplierId);

        // Then
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(workers, response.getData());
    }

}
