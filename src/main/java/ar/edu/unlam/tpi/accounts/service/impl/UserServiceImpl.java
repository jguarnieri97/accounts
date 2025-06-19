package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.dto.request.BaseUserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.request.EmailRequest;
import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserCreatedResponse;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.exceptions.GenericException;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.service.strategy.UserDetailStrategy;
import ar.edu.unlam.tpi.accounts.utils.UserDetailResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import ar.edu.unlam.tpi.accounts.dto.request.UserRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserResponse;
import ar.edu.unlam.tpi.accounts.mapper.UserRequestFactory;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.dto.request.SupplierRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.request.WorkerRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.request.ApplicantRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.mapper.UserMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final List<UserDetailStrategy> strategies;
    private final UserRequestFactory userRequestFactory;
    private final SupplierCompanyDAO supplierCompanyDAO;
    private final WorkerDAO workerDAO;
    private final ApplicantCompanyDAO applicantCompanyDAO;
    private final UserMapper userMapper;
    @Override
    public UserResponse getUsersDetail(List<UserRequest> userRequests) {
        List<UserDetailResponse> applicants = new ArrayList<>();
        List<UserDetailResponse> suppliers = new ArrayList<>();
        List<UserDetailResponse> workers = new ArrayList<>();

        for (UserRequest request : userRequests) {
            try {
                UserDetailStrategy strategy = getStrategy(request.getType());

                UserEntity user = strategy.getUser(
                        UserDetailRequest.builder()
                                .userId(request.getUserId())
                                .type(request.getType())
                                .build());

                if (user != null) {
                    UserDetailResponse detail = UserDetailResponseBuilder.build(user);
                    switch (request.getType().toLowerCase()) {
                        case "applicant" -> applicants.add(detail);
                        case "supplier" -> suppliers.add(detail);
                        case "worker" -> workers.add(detail);
                    }
                } else {
                    log.warn("Usuario no encontrado: id={} tipo={}", request.getUserId(), request.getType());
                }

            } catch (Exception e) {
                log.warn("Error al procesar usuario id={} tipo={}: {}", request.getUserId(), request.getType(),
                        e.getMessage());
            }
        }

        if (applicants.isEmpty() && suppliers.isEmpty() && workers.isEmpty()) {
            throw new NotFoundException("No se encontraron usuarios para los datos enviados");
        }

        return UserResponse.builder()
                .applicants(applicants)
                .suppliers(suppliers)
                .workers(workers)
                .build();
    }

    @Override
    public UserDetailResponse getUsersDetailEmail(EmailRequest userRequest) {
        log.info("Buscando detalles del usuario por email: {}", userRequest.getEmail());
        UserDetailStrategy strategy = getStrategy(userRequest.getType());

        UserEntity user = strategy.getUserEmail(userRequest);
        log.info("Usuario encontrado: {}", user.getId());

        return UserDetailResponseBuilder.build(user);
    }

    private UserDetailStrategy getStrategy(String type) {
        return strategies.stream()
                .filter(s -> s.supports(type))
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Tipo de usuario no válido: {}", type);
                    return new IllegalArgumentException("Tipo de usuario no válido");
                });
    }

    @Override
    public UserCreatedResponse createUser(Map<String, Object> request) {
        BaseUserRegisterRequestDto userRequest = userRequestFactory.resolve(request);
    
        if (userRequest instanceof SupplierRegisterRequestDto supplierReq) {
            var entity = userMapper.toSupplierEntity(supplierReq);
            var saved = supplierCompanyDAO.save(entity);
            return UserCreatedResponse.builder().id(saved.getId()).build();
    
        } else if (userRequest instanceof WorkerRegisterRequestDto workerReq) {
            System.out.println("userRequest instanceof WorkerRegisterRequestDto? " + (userRequest instanceof WorkerRegisterRequestDto));
System.out.println("userRequest actual class: " + userRequest.getClass());

            var supplier = supplierCompanyDAO.findById(workerReq.getCompanyId());
            var entity = userMapper.toWorkerEntity(workerReq, supplier);
            var saved = workerDAO.save(entity);
            return UserCreatedResponse.builder().id(saved.getId()).build();
    
        } else if (userRequest instanceof ApplicantRegisterRequestDto applicantReq) {
            var entity = userMapper.toApplicantEntity(applicantReq);
            var saved = applicantCompanyDAO.save(entity);
            return UserCreatedResponse.builder().id(saved.getId()).build();
        }
        return null;
    }

    

}
