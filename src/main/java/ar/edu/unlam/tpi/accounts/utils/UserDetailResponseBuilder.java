package ar.edu.unlam.tpi.accounts.utils;

import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.CompanyEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Component;

@UtilityClass
public class UserDetailResponseBuilder {

    public static UserDetailResponse build(UserEntity user) {
        var userResponse = UserDetailResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .cuit(user.getCuit())
                .build();

        if (user instanceof CompanyEntity company) {
            userResponse.setLn(company.getGeolocation().getLn());
            userResponse.setLat(company.getGeolocation().getLat());
        }

        return userResponse;
    }
}