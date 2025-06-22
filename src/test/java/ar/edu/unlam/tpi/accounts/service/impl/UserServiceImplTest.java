package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.controller.impl.UserControllerImpl;
import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.service.strategy.UserRegisterStrategy;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private UserRegisterStrategy supplierStrategy;

    @Mock
    private UserRegisterStrategy applicantStrategy;

    @Mock
    private UserRegisterStrategy workerStrategy;

    @InjectMocks
    private UserControllerImpl controller;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void givenUserRequestList_whenGetUsersDetails_thenReturnsGroupedUserResponse() {
        // Given
        List<UserRequest> userRequests = List.of(
                UserRequest.builder().userId(1L).type("applicant").build(),
                UserRequest.builder().userId(2L).type("supplier").build(),
                UserRequest.builder().userId(3L).type("worker").build());

        UserResponse mockResponse = UserResponse.builder()
                .applicants(List.of(UserDetailResponse.builder().id(1L).name("Empresa Uno").build()))
                .suppliers(List.of(UserDetailResponse.builder().id(2L).name("Proveedor Dos").build()))
                .workers(List.of(UserDetailResponse.builder().id(3L).name("Carlos Trabajador").build()))
                .build();

        when(userService.getUsersDetail(userRequests)).thenReturn(mockResponse);

        // When
        GenericResponse<UserResponse> response = controller.getUsersDetails(userRequests);

        // Then
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(mockResponse, response.getData());
        assertEquals(1, response.getData().getApplicants().size());
        assertEquals(1, response.getData().getSuppliers().size());
        assertEquals(1, response.getData().getWorkers().size());
    }

    @Test
    void givenSupplierRequest_whenRegister_thenUsesSupplierStrategy() {
        // Arrange
        UserRegisterRequestDto request = new UserRegisterRequestDto();
        request.setType("supplier");

        UserCreatedResponse expected = new UserCreatedResponse(77L);

        when(supplierStrategy.supports("supplier")).thenReturn(true);
        when(supplierStrategy.register(request)).thenReturn(expected);

        userService = new UserServiceImpl(null, List.of(supplierStrategy, applicantStrategy, workerStrategy));

        // Act
        UserCreatedResponse response = userService.register(request);

        // Assert
        assertNotNull(response);
        assertEquals(77L, response.getId());
        verify(supplierStrategy).register(request);
    }

    @Test
    void givenWorkerRequest_whenRegister_thenUsesWorkerStrategy() {
        // Arrange
        UserRegisterRequestDto request = new UserRegisterRequestDto();
        request.setType("worker");

        UserCreatedResponse expected = new UserCreatedResponse(99L);

        when(workerStrategy.supports("worker")).thenReturn(true);
        when(workerStrategy.register(request)).thenReturn(expected);

        userService = new UserServiceImpl(null, List.of(supplierStrategy, applicantStrategy, workerStrategy));

        // Act
        UserCreatedResponse response = userService.register(request);

        // Assert
        assertNotNull(response);
        assertEquals(99L, response.getId());
        verify(workerStrategy).register(request);
    }

    @Test
    void givenApplicantRequest_whenRegister_thenUsesApplicantStrategy() {
        // Arrange
        UserRegisterRequestDto request = new UserRegisterRequestDto();
        request.setType("applicant");

        UserCreatedResponse expected = new UserCreatedResponse(55L);

        when(applicantStrategy.supports("applicant")).thenReturn(true);
        when(applicantStrategy.register(request)).thenReturn(expected);

        userService = new UserServiceImpl(null, List.of(supplierStrategy, applicantStrategy, workerStrategy));

        // Act
        UserCreatedResponse response = userService.register(request);

        // Assert
        assertNotNull(response);
        assertEquals(55L, response.getId());
        verify(applicantStrategy).register(request);
    }

}
