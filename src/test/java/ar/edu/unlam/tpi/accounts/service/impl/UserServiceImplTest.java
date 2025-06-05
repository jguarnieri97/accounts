package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.controller.impl.UserControllerImpl;
import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerImpl controller;

    @Test
    void givenUserRequestList_whenGetUsersDetails_thenReturnsGroupedUserResponse() {
        // Given
        List<UserRequest> userRequests = List.of(
                UserRequest.builder().userId(1L).type("applicant").build(),
                UserRequest.builder().userId(2L).type("supplier").build(),
                UserRequest.builder().userId(3L).type("worker").build()
        );

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
}
