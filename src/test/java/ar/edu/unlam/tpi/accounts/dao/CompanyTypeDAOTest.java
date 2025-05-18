package ar.edu.unlam.tpi.accounts.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import ar.edu.unlam.tpi.accounts.utils.CompanyTypeHelperTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.CompanyTypeEntity;
import ar.edu.unlam.tpi.accounts.persistence.impl.CompanyTypeDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.repository.CompanyTypeRepository;

@ExtendWith(MockitoExtension.class)
class CompanyTypeDAOTest {

    @Mock
    private CompanyTypeRepository repository;

    @InjectMocks
    private CompanyTypeDAOImpl commandTypeDAO;

    @Test
    void givenCompanyTypesInRepository_whenFindAll_thenReturnsAllCompanyTypes() {
        // Given
        List<CompanyTypeEntity> expected = CompanyTypeHelperTest.getCompanyTypes();
        when(repository.findAll()).thenReturn(expected);

        // When
        List<CompanyTypeEntity> result = commandTypeDAO.findAll();

        // Then
        assertEquals(expected.size(), result.size());
        assertEquals("Alimentos y Bebidas", result.get(0).getName());
        verify(repository).findAll();
    }

    @Test
    void givenValidId_whenFindById_thenReturnsCompanyTypeEntity() {
        // Given
        CompanyTypeEntity entity = CompanyTypeHelperTest.getCompanyTypes().get(0);
        entity.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        // When
        CompanyTypeEntity result = commandTypeDAO.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Alimentos y Bebidas", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    void givenInvalidId_whenFindById_thenThrowsNotFoundException() {
        // Given
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            commandTypeDAO.findById(99L);
        });

        assertEquals("Company type not found", exception.getDetail());
        verify(repository).findById(99L);
    }

    @Test
    void givenCompanyTypeEntity_whenSave_thenReturnsSavedEntity() {
        // Given
        CompanyTypeEntity entity = CompanyTypeHelperTest.getCompanyTypes().get(0);
        when(repository.save(entity)).thenReturn(entity);

        // When
        CompanyTypeEntity result = commandTypeDAO.save(entity);

        // Then
        assertNotNull(result);
        assertEquals("Alimentos y Bebidas", result.getName());
        verify(repository).save(entity);
    }

    @Test
    void givenRepositoryThrowsException_whenSave_thenThrowsInternalException() {
        // Given
        CompanyTypeEntity entity = CompanyTypeHelperTest.getCompanyTypes().get(0);
        when(repository.save(entity)).thenThrow(new InternalException("Save error"));

        // When & Then
        assertThrows(InternalException.class, () -> commandTypeDAO.save(entity));
    }

    @Test
    void givenId_whenDelete_thenRepositoryDeleteCalled() {
        // Given
        Long id = 5L;

        // When
        commandTypeDAO.delete(id);

        // Then
        verify(repository).deleteById(id);
    }
}
