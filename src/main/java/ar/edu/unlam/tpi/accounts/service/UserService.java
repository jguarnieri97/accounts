package ar.edu.unlam.tpi.accounts.service;

import ar.edu.unlam.tpi.accounts.dto.request.EmailRequest;
import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import java.util.List;
import java.util.Map;

public interface UserService {

    UserResponse getUsersDetail(List<UserRequest> userRequests);
    UserDetailResponse getUsersDetailEmail(EmailRequest userRequest);
	UserCreatedResponse createUser(Map<String, Object> request);
}
