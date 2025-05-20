package ar.edu.unlam.tpi.accounts.controller;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/accounts/v1/users")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<UserDetailResponse> getUserContactData(@RequestBody UserDetailRequest userDetailRequest);

}
