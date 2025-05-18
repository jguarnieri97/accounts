package ar.edu.unlam.tpi.accounts.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.persistence.impl.WorkerDAOImpl;
import ar.edu.unlam.tpi.accounts.persistence.repository.WorkerRepository;
import ar.edu.unlam.tpi.accounts.utils.WorkerDataHelperTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class WorkerDAOTest {

    @Mock
    private WorkerRepository repository;

    @InjectMocks
    private WorkerDAOImpl workerDAO;

    @Test
    void givenWorkersExist_whenFindAll_thenReturnsWorkerList() {
        // Given
        List<WorkerEntity> workers = WorkerDataHelperTest.getWorkers();
        when(repository.findAll()).thenReturn(workers);

        // When
        List<WorkerEntity> result = workerDAO.findAll();

        // Then
        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    void givenRepositoryFails_whenFindAll_thenThrowsInternalException() {
        // Given
        when(repository.findAll()).thenThrow(new RuntimeException("DB error"));

        // When & Then
        assertThrows(InternalException.class, () -> workerDAO.findAll());
        verify(repository).findAll();
    }

    @Test
    void givenCompanyHasWorkers_whenFindByCompanyId_thenReturnsWorkerList() {
        // Given
        Long companyId = 1L;
        List<WorkerEntity> workers = WorkerDataHelperTest.getWorkers();
        when(repository.findByCompanyId(companyId)).thenReturn(workers);

        // When
        List<WorkerEntity> result = workerDAO.findByCompanyId(companyId);

        // Then
        assertFalse(result.isEmpty());
        verify(repository).findByCompanyId(companyId);
    }

    @Test
    void givenCompanyHasNoWorkers_whenFindByCompanyId_thenThrowsNotFoundException() {
        // Given
        Long companyId = 2L;
        when(repository.findByCompanyId(companyId)).thenReturn(List.of());

        // When & Then
        NotFoundException ex = assertThrows(NotFoundException.class, () -> {
            workerDAO.findByCompanyId(companyId);
        });

        assertEquals("Workers not found for the company", ex.getDetail());
        verify(repository).findByCompanyId(companyId);
    }

    @Test
    void givenWorkerExists_whenFindById_thenReturnsWorker() {
        // Given
        WorkerEntity worker = WorkerDataHelperTest.getWorkers().get(0);
        when(repository.findById(1L)).thenReturn(Optional.of(worker));

        // When
        WorkerEntity result = workerDAO.findById(1L);

        // Then
        assertNotNull(result);
        assertEquals("Juan Pérez", result.getName());
        verify(repository).findById(1L);
    }

    @Test
    void givenWorkerNotExists_whenFindById_thenThrowsNotFoundException() {
        // Given
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // When & Then
        NotFoundException ex = assertThrows(NotFoundException.class, () -> {
            workerDAO.findById(99L);
        });

        assertEquals("Worker not found", ex.getDetail());
        verify(repository).findById(99L);
    }

    @Test
    void givenValidWorker_whenSave_thenReturnsSavedWorker() {
        // Given
        WorkerEntity worker = WorkerDataHelperTest.getWorkers().get(0);
        when(repository.save(worker)).thenReturn(worker);

        // When
        WorkerEntity result = workerDAO.save(worker);

        // Then
        assertNotNull(result);
        assertEquals(worker.getName(), result.getName());
        verify(repository).save(worker);
    }

    @Test
    void givenSaveFails_whenSave_thenThrowsInternalException() {
        // Given
        WorkerEntity worker = WorkerDataHelperTest.getWorkers().get(0);
        when(repository.save(worker)).thenThrow(new RuntimeException("Save failed"));

        // When & Then
        assertThrows(InternalException.class, () -> workerDAO.save(worker));
        verify(repository).save(worker);
    }

    @Test
    void givenId_whenDelete_thenDeletesWorker() {
        // Given
        Long id = 1L;

        // When
        workerDAO.delete(id);

        // Then
        verify(repository).deleteById(id);
    }

    @Test
    void givenDeleteFails_whenDelete_thenThrowsInternalException() {
        // Given
        Long id = 2L;
        doThrow(new InternalException("Delete failed")).when(repository).deleteById(id);

        // When & Then
        assertThrows(InternalException.class, () -> workerDAO.delete(id));
        verify(repository).deleteById(id);
    }
}
