package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.exceptions.UserServiceBadRequestException;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.persistence.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.WorkerDAO;
import ar.edu.unlam.tpi.accounts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ApplicantCompanyDAO applicantRepository;
    private final SupplierCompanyDAO supplierRepository;
    private final WorkerDAO workerRepository;

    @Override
    public UserDetailResponse getUserDetail(UserDetailRequest userDetailRequest) {

        return switch (userDetailRequest.getType()) {
            case "applicant" -> {
                var user = applicantRepository.findById(userDetailRequest.getUserId());
                yield buildUserDetailResponse(user);
            }
            case "supplier" -> {
                var user = supplierRepository.findById(userDetailRequest.getUserId());
                yield buildUserDetailResponse(user);
            }
            case "worker" -> {
                var user = workerRepository.findById(userDetailRequest.getUserId());
                yield buildUserDetailResponse(user);
            }
            default -> {
                log.error("Tipo de usuario no válido: {}", userDetailRequest.getType());
                throw new UserServiceBadRequestException("Tipo de usuario no válido");
            }
        };

    }

    private UserDetailResponse buildUserDetailResponse(UserEntity user) {
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
