package ar.edu.unlam.tpi.accounts.service;

import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse getUsersDetail(List<UserRequest> userRequests);

}
