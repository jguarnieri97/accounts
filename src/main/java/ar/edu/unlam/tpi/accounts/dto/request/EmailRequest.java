package ar.edu.unlam.tpi.accounts.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailRequest {
    private String email;
    private String type;
}
