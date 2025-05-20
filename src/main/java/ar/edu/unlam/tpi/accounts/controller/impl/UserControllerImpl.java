package ar.edu.unlam.tpi.accounts.controller.impl;

import ar.edu.unlam.tpi.accounts.controller.UserController;
import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public GenericResponse<UserDetailResponse> getUserContactData(UserDetailRequest userDetailRequest) {
        var response = userService.getUserDetail(userDetailRequest);
        return GenericResponse.<UserDetailResponse>builder()
                .code(Constants.STATUS_OK)
                .message(Constants.SUCCESS_MESSAGE)
                .data(response)
                .build();
    }
}
