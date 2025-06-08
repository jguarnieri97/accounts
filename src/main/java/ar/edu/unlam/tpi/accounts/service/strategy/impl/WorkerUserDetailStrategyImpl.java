package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.EmailRequest;
import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;
import ar.edu.unlam.tpi.accounts.service.strategy.UserDetailStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkerUserDetailStrategyImpl implements UserDetailStrategy {

    private final WorkerDAO workerRepository;

    @Override
    public boolean supports(String userType) {
        return "worker".equalsIgnoreCase(userType);
    }

    @Override
    public UserEntity getUser(UserDetailRequest request) {
        return workerRepository.findById(request.getUserId());
    }

    @Override
    public UserEntity getUserEmail(EmailRequest userRequest) {
        return workerRepository.findByEmail(userRequest.getEmail());
    }

}
