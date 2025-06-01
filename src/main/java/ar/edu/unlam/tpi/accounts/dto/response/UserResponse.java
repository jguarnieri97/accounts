package ar.edu.unlam.tpi.accounts.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class UserResponse {
    private List<UserDetailResponse> applicants;
    private List<UserDetailResponse> suppliers;
    private List<UserDetailResponse> workers;
}
