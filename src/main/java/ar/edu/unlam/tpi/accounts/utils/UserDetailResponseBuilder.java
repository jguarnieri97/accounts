package ar.edu.unlam.tpi.accounts.utils;

import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserDetailResponseBuilder {

    public UserDetailResponse build(UserEntity user) {
        return UserDetailResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .cuit(user.getCuit())
                .build();
    }
}