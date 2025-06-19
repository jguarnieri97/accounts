package ar.edu.unlam.tpi.accounts.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseUserRegisterRequestDto {
    
    @NotNull
    private String type; //supplier, worker, applicant

    @NotNull
    @Email
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @NotNull
    private String address;

    @NotNull
    private String cuit;
}