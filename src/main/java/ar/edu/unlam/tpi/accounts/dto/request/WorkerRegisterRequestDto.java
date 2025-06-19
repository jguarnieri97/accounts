package ar.edu.unlam.tpi.accounts.dto.request;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class WorkerRegisterRequestDto extends BaseUserRegisterRequestDto {
    @NotNull
    private Long companyId;

}
