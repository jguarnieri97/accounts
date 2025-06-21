package ar.edu.unlam.tpi.accounts.controller.impl;

import ar.edu.unlam.tpi.accounts.controller.UserController;
import ar.edu.unlam.tpi.accounts.dto.request.EmailRequest;
import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public GenericResponse<UserResponse> getUsersDetails(List<UserRequest> users) {
        var response = userService.getUsersDetail(users);
        return GenericResponse.<UserResponse>builder()
                .code(Constants.STATUS_OK)
                .message(Constants.SUCCESS_MESSAGE)
                .data(response)
                .build();
    }

    @Override
    public GenericResponse<UserDetailResponse> getUsersDetailsEmail(EmailRequest user) {
        var response = userService.getUsersDetailEmail(user);
        return GenericResponse.<UserDetailResponse>builder()
                .code(Constants.STATUS_OK)
                .message(Constants.SUCCESS_MESSAGE)
                .data(response)
                .build();
    }

    @Override
    public GenericResponse<UserCreatedResponse> createUser(Map<String, Object> request) {
        var response = userService.createUser(request);
        return GenericResponse.<UserCreatedResponse>builder()
                .code(Constants.STATUS_CREATED)
                .message(Constants.SUCCESS_MESSAGE)
                .data(response)
                .build();
    }
}
