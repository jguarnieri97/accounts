package ar.edu.unlam.tpi.accounts.service;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;

public interface UserService {

    UserDetailResponse getUserDetail(UserDetailRequest userDetailRequest);
}
