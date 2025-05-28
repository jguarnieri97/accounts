package ar.edu.unlam.tpi.accounts.controller.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class UserControllerImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserControllerImpl userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void givenUserDetailRequest_whenGetUserContactData_thenReturnGenericResponse() {
        // Arrange
        UserDetailRequest request = UserDetailRequest.builder()
                .userId(1L)
                .type("email")
                .build();
        UserDetailResponse mockResponse = UserDetailResponse.builder()
                .id(1L)
                .email("user@example.com")
                .build();
        when(userService.getUserDetail(request)).thenReturn(mockResponse);

        // Act
        GenericResponse<UserDetailResponse> response = userController.getUserContactData(request);

        // Assert
        assertNotNull(response);
        assertEquals(Constants.STATUS_OK, response.getCode());
        assertEquals(Constants.SUCCESS_MESSAGE, response.getMessage());
        assertEquals(mockResponse, response.getData());
    }

}
