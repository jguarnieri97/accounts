package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.EmailRequest;
import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.service.strategy.UserDetailStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicantUserDetailStrategyImpl implements UserDetailStrategy {

    private final ApplicantCompanyDAO applicantRepository;

    @Override
    public boolean supports(String userType) {
        return "applicant".equalsIgnoreCase(userType);
    }

    @Override
    public UserEntity getUser(UserDetailRequest request) {
        return applicantRepository.findById(request.getUserId());
    }

    @Override
    public UserEntity getUserEmail(EmailRequest userRequest) {
        return applicantRepository.findByEmail(userRequest.getEmail());
    }
}
