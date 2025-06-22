package ar.edu.unlam.tpi.accounts.service.strategy.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.CategoryEntity;
import ar.edu.unlam.tpi.accounts.models.Geolocation;
import ar.edu.unlam.tpi.accounts.models.LabelEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.CategoryDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.service.strategy.UserRegisterStrategy;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SupplierRegisterStrategyImpl implements UserRegisterStrategy {

    private final SupplierCompanyDAO supplierDao;
    private final CategoryDAO categoryDAO;

    @Override
    public boolean supports(String userType) {
        return "supplier".equalsIgnoreCase(userType);
    }

    @Override
    public UserCreatedResponse register(UserRegisterRequestDto request) {
        request.validate();

        CategoryEntity category = categoryDAO.findByName(request.getCategory());
        if (category == null) {
            throw new NotFoundException("Categoría no encontrada: " + request.getCategory());
        }

        Set<LabelEntity> labels = request.getLabels().stream()
            .map(tag -> LabelEntity.builder()
                    .tag(tag)
                    .build())
            .collect(Collectors.toSet());

        SupplierCompanyEntity supplier = SupplierCompanyEntity.builder()
            .email(request.getEmail())
            .name(request.getName())
            .phone(request.getPhone())
            .address(request.getAddress())
            .cuit(request.getCuit())
            .geolocation(new Geolocation(request.getLat(), request.getLn()))
            .description(request.getDescription())
            .isVerified(false)
            .isActive(true)
            .companyType(category)
            .labels(new HashSet<>()) // se setean luego
            .build();

        labels.forEach(label -> label.setSupplier(supplier));
        supplier.setLabels(labels);

        SupplierCompanyEntity saved = supplierDao.save(supplier);

        return new UserCreatedResponse(saved.getId());
    }
}
