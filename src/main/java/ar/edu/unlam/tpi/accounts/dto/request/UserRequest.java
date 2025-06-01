package ar.edu.unlam.tpi.accounts.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private Long userId;
    private String type;
}