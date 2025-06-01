package ar.edu.unlam.tpi.accounts.controller;

import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Controller", description = "Operaciones relacionadas con usuarios")
@RequestMapping("/accounts/v1/users")
public interface UserController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    GenericResponse<UserResponse> getUsersDetails(@RequestBody List<UserRequest> users);
}

