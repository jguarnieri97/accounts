package ar.edu.unlam.tpi.accounts.service.strategy;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.models.UserEntity;

public interface UserDetailStrategy {

    boolean supports(String userType);
    UserEntity getUser(UserDetailRequest request);

}
