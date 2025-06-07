package ar.edu.unlam.tpi.accounts.persistence.dao.impl;

import java.util.List;
import java.util.Optional;

import ar.edu.unlam.tpi.accounts.utils.ApplicantCompanyHelperTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.persistence.repository.ApplicantCompanyRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicantCompanyDAOImplTest {
    
    @Mock
    private ApplicantCompanyRepository repository;

    @InjectMocks
    private ApplicantCompanyDAOImpl applicantCompanyDAO;

    @Test
    void given_whenFindAll_ThenReturnListOfApplicants() {
        // Arrange
        List<ApplicantCompanyEntity> expected = ApplicantCompanyHelperTest.getApplicantCompanyList();
        
        when(repository.findAll()).thenReturn(ApplicantCompanyHelperTest.getApplicantCompanyList());
        List<ApplicantCompanyEntity> result = applicantCompanyDAO.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expected.get(0).getId(), result.get(0).getId());
    }

    @Test
    void givenRepositoryThrowsException_whenFindAll_thenThrowsInternalException() {
        // Given
        when(repository.findAll()).thenThrow(new InternalException("Database down"));

        // When & Then
        InternalException thrown = Assertions.assertThrows(
                InternalException.class,
                () -> applicantCompanyDAO.findAll()
        );
        Assertions.assertEquals("INTERNAL_SERVER_ERROR", thrown.getMessage());
    }

    @Test
    void givenAnId_whenFindById_ThenReturnApplicant() {
        // Arrange
        Long id = 1L;
        ApplicantCompanyEntity expected = ApplicantCompanyHelperTest.getApplicantCompanyList().get(0);

        when(repository.findById(id)).thenReturn(Optional.of(ApplicantCompanyHelperTest.getApplicantCompanyList().get(0)));
        ApplicantCompanyEntity result = applicantCompanyDAO.findById(id);

        assertNotNull(result);
        assertEquals(expected.getId(), result.getId());
    }


    @Test
    void givenAnId_whenFindById_thenReturnException() {
        // Arrange
        Long id = 1L;
       
        //Act  & Assert
        when(repository.findById(id)).thenThrow(new NotFoundException("Applicant not found"));
        assertThrows(NotFoundException.class, ()-> applicantCompanyDAO.findById(id));
    }

    @Test
    void givenValidEntity_whenSave_thenReturnsSavedEntity() {
        // Given
        ApplicantCompanyEntity entity = new ApplicantCompanyEntity();
        entity.setName("Empresa Test");

        when(repository.save(entity)).thenReturn(entity);

        // When
        ApplicantCompanyEntity result = applicantCompanyDAO.save(entity);

        // Then
        assertNotNull(result);
        assertEquals("Empresa Test", result.getName());
    }

    @Test
    void givenRepositoryThrowsException_whenSave_thenThrowsInternalException() {
        // Given
        ApplicantCompanyEntity entity = new ApplicantCompanyEntity();

        when(repository.save(entity)).thenThrow(new InternalException("Error saving entity"));

        // When & Then
        assertThrows( InternalException.class,    () -> applicantCompanyDAO.save(entity));
    }


    @Test
    void givenValidId_whenDelete_thenRepositoryDeleteByIdIsCalled() {
        // Given
        Long id = 1L;

        // When
        applicantCompanyDAO.delete(id);

        // Then
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    void givenRepositoryThrowsException_whenDelete_thenThrowsInternalException() {
        // Given
        Long id = 99L;
        doThrow(new InternalException("Database error"))
                .when(repository).deleteById(id);

        // When & Then
        InternalException thrown = Assertions.assertThrows(
                InternalException.class,
                () -> applicantCompanyDAO.delete(id)
        );

        assertEquals("INTERNAL_SERVER_ERROR", thrown.getMessage());
    }
}
