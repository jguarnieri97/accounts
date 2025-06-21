package ar.edu.unlam.tpi.accounts.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantRegisterRequestDto extends BaseUserRegisterRequestDto {
    @NotNull
    private Float lat;

    @NotNull
    private Float ln;

    @NotBlank
    private String description;

    @NotBlank
    private String category;
}
