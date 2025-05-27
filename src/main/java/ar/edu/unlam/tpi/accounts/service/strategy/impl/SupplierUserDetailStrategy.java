package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.persistence.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.service.strategy.UserDetailStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierUserDetailStrategy implements UserDetailStrategy {

    private final SupplierCompanyDAO supplierRepository;

    @Override
    public boolean supports(String userType) {
        return "supplier".equalsIgnoreCase(userType);
    }

    @Override
    public UserEntity getUser(UserDetailRequest request) {
        return supplierRepository.findById(request.getUserId());
    }
}
