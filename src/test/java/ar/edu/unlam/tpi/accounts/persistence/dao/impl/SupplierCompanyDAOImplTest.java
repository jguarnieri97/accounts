package ar.edu.unlam.tpi.accounts.persistence.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.persistence.repository.SupplierCompanyRepository;
import ar.edu.unlam.tpi.accounts.utils.SupplierCompanyHelperTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SupplierCompanyDAOImplTest {

    @Mock
    private SupplierCompanyRepository repository;

    @InjectMocks
    private SupplierCompanyDAOImpl supplierCompanyDAO;

    @Test
    void givenSupplierListInRepository_whenFindAll_thenReturnsAllSuppliers() {
        // Given
        List<SupplierCompanyEntity> expected = SupplierCompanyHelperTest.getSupplierCompanies();
        when(repository.findAll()).thenReturn(expected);

        // When
        List<SupplierCompanyEntity> result = supplierCompanyDAO.findAll();

        // Then
        assertEquals(expected.size(), result.size());
        verify(repository).findAll();
    }

    @Test
    void givenValidId_whenFindById_thenReturnsSupplierCompanyEntity() {
        // Given
        SupplierCompanyEntity expected = SupplierCompanyHelperTest.getSupplier();
        when(repository.findById(1L)).thenReturn(Optional.of(expected));

        // When
        SupplierCompanyEntity result = supplierCompanyDAO.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals("AgroFertil", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowsNotFoundException() {
        // Given
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            supplierCompanyDAO.findById(99L);
        });

        assertEquals("Supplier not found", exception.getDetail());
        verify(repository).findById(99L);
    }

    @Test
    void givenSupplierCompany_whenSave_thenReturnsSavedEntity() {
        // Given
        SupplierCompanyEntity supplier = SupplierCompanyHelperTest.getSupplier();
        when(repository.save(supplier)).thenReturn(supplier);

        // When
        SupplierCompanyEntity result = supplierCompanyDAO.save(supplier);

        // Then
        assertNotNull(result);
        assertEquals("AgroFertil", result.getName());
        verify(repository).save(supplier);
    }

    @Test
    void givenRepositoryThrowsException_whenSave_thenThrowsInternalException() {
        // Given
        SupplierCompanyEntity supplier = SupplierCompanyHelperTest.getSupplier();
        when(repository.save(supplier)).thenThrow(new InternalException("DB error"));

        // When & Then
        assertThrows(InternalException.class, () -> {
            supplierCompanyDAO.save(supplier);
        });

    }

    @Test
    void givenId_whenDelete_thenRepositoryDeleteCalled() {
        // Given
        Long id = 1L;

        // When
        supplierCompanyDAO.delete(id);

        // Then
        verify(repository).deleteById(id);
    }

    @Test
    void givenRepositoryThrowsException_whenDelete_thenThrowsInternalException() {
        // Given
        Long id = 2L;
        doThrow(new InternalException("Error deleting")).when(repository).deleteById(id);

        // When & Then
        assertThrows(InternalException.class, () -> {
            supplierCompanyDAO.delete(id);
        });

    }

    @Test
    void givenMatchingSuppliers_whenFindByCategoryAndLatAndLn_thenReturnsList() {
        // Given
        Long category = 1L;
        Float lat = -34.634f;
        Float ln = -58.4065f;
        Float radius = 10f;
        List<SupplierCompanyEntity> expected = List.of(new SupplierCompanyEntity());
        when(repository.findByCategoryAndLatAndLn(category, lat, ln, radius)).thenReturn(expected);

        // When
        List<SupplierCompanyEntity> result = supplierCompanyDAO.findByCategoryAndLatAndLn(category, lat, ln, radius);

        // Then
        assertEquals(expected, result);
        verify(repository).findByCategoryAndLatAndLn(category, lat, ln, radius);
    }

    @Test
    void givenNoSuppliers_whenFindByCategoryAndLatAndLn_thenThrowsNotFoundException() {
        // Given
        Long category = 1L;
        Float lat = -34.634f;
        Float ln = -58.4065f;
        Float radius = 10f;
        when(repository.findByCategoryAndLatAndLn(category, lat, ln, radius)).thenReturn(List.of());

        // When & Then
        NotFoundException ex = assertThrows(NotFoundException.class, () ->
                supplierCompanyDAO.findByCategoryAndLatAndLn(category, lat, ln, radius)
        );
        assertEquals("No se encontraron proveedores para los filtros dados", ex.getDetail());
        verify(repository).findByCategoryAndLatAndLn(category, lat, ln, radius);
    }

    @Test
    void givenRepositoryThrowsException_whenFindByCategoryAndLatAndLn_thenThrowsInternalException() {
        // Given
        Long category = 1L;
        Float lat = -34.634f;
        Float ln = -58.4065f;
        Float radius = 10f;
        when(repository.findByCategoryAndLatAndLn(category, lat, ln, radius)).thenThrow(new RuntimeException("DB error"));

        // When & Then
        assertThrows(InternalException.class, () ->
                supplierCompanyDAO.findByCategoryAndLatAndLn(category, lat, ln, radius)
        );
        verify(repository).findByCategoryAndLatAndLn(category, lat, ln, radius);
    }
}
