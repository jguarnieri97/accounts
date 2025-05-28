package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class WorkerUserDetailStrategyImplTest {

    @Mock
    private WorkerDAO workerRepository;

    @InjectMocks
    private WorkerUserDetailStrategyImpl strategy;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenUserTypeWorker_whenSupports_thenReturnTrue() {
        // Act
        boolean result = strategy.supports("worker");

        // Assert
        assertTrue(result);
    }

    @Test
    void givenUserDetailRequest_whenGetUser_thenReturnUserEntity() {
        // Arrange
        UserDetailRequest request = UserDetailRequest.builder()
                .userId(1L)
                .build();
        WorkerEntity mockUser = new WorkerEntity();
        mockUser.setId(1L);
        mockUser.setName("John Worker");
        when(workerRepository.findById(1L)).thenReturn(mockUser);

        // Act
        UserEntity result = strategy.getUser(request);

        // Assert
        assertEquals(mockUser, result);
    }

}
