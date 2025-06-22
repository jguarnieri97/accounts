package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import org.springframework.stereotype.Component;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;
import ar.edu.unlam.tpi.accounts.service.strategy.UserRegisterStrategy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkerRegisterStrategyImpl implements UserRegisterStrategy {

    private final WorkerDAO workerDao;
    private final SupplierCompanyDAO supplierDao;

    @Override
    public boolean supports(String userType) {
        return "worker".equalsIgnoreCase(userType);
    }

    @Override
    public UserCreatedResponse register(UserRegisterRequestDto request) {
        request.validate();

        SupplierCompanyEntity company = supplierDao.findById(request.getCompanyId());
        if (company == null) {
            throw new NotFoundException("Empresa (supplier) no encontrada con ID: " + request.getCompanyId());
        }

        WorkerEntity worker = WorkerEntity.builder()
            .email(request.getEmail())
            .name(request.getName())
            .phone(request.getPhone())
            .address(request.getAddress())
            .cuit(request.getCuit())
            .isActive(true)
            .company(company)
            .build();

        WorkerEntity saved = workerDao.save(worker);

            return new UserCreatedResponse(saved.getId());
    }
}
