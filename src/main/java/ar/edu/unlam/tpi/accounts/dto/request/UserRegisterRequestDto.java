package ar.edu.unlam.tpi.accounts.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRegisterRequestDto {

    @NotNull(message = "El campo 'type' es obligatorio")
    private String type; // "supplier", "applicant", "worker"

    @NotNull(message = "El campo 'email' es obligatorio")
    @Email(message = "El campo 'email' debe tener un formato válido")
    private String email;

    @NotNull(message = "El campo 'name' es obligatorio")
    private String name;

    @NotNull(message = "El campo 'phone' es obligatorio")
    private String phone;

    @NotNull(message = "El campo 'address' es obligatorio")
    private String address;

    @NotNull(message = "El campo 'cuit' es obligatorio")
    private String cuit;

    // Campos opcionales según tipo
    private Float lat;
    private Float ln;
    private String description;
    private List<String> labels;
    private String category;
    private Long companyId;


}
