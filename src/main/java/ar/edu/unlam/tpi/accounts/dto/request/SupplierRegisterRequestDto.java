package ar.edu.unlam.tpi.accounts.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SupplierRegisterRequestDto extends BaseUserRegisterRequestDto{
    
    @NotNull
    private Float lat;

    @NotNull
    private Float ln;

    @NotBlank
    private String description;

    @NotEmpty
    private List<String> labels;

    @NotBlank
    private String category;
}