package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import org.springframework.stereotype.Component;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.Geolocation;
import ar.edu.unlam.tpi.accounts.persistence.dao.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.service.strategy.UserRegisterStrategy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApplicantRegisterStrategyImpl implements UserRegisterStrategy {

    private final ApplicantCompanyDAO applicantDao;

    @Override
    public boolean supports(String userType) {
        return "applicant".equalsIgnoreCase(userType);
    }

    @Override
    public UserCreatedResponse register(UserRegisterRequestDto request) {
        request.validate();

        ApplicantCompanyEntity applicant = ApplicantCompanyEntity.builder()
            .email(request.getEmail())
            .name(request.getName())
            .phone(request.getPhone())
            .address(request.getAddress())
            .cuit(request.getCuit())
            .geolocation(new Geolocation(request.getLat(), request.getLn()))
            .description(request.getDescription())
            .isVerified(false)
            .isActive(true)
            .build();

        ApplicantCompanyEntity saved = applicantDao.save(applicant);

        return new UserCreatedResponse(saved.getId());
    }
}
