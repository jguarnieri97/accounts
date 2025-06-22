package ar.edu.unlam.tpi.accounts.service.strategy;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;

public interface UserRegisterStrategy {
    boolean supports(String userType);
    UserCreatedResponse register(UserRegisterRequestDto request);
}
