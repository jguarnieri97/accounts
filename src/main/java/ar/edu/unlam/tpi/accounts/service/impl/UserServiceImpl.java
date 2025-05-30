package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.dto.request.UserDetailRequest;
import ar.edu.unlam.tpi.accounts.dto.response.UserDetailResponse;
import ar.edu.unlam.tpi.accounts.exceptions.UserServiceBadRequestException;
import ar.edu.unlam.tpi.accounts.models.UserEntity;
import ar.edu.unlam.tpi.accounts.service.UserService;
import ar.edu.unlam.tpi.accounts.service.strategy.UserDetailStrategy;
import ar.edu.unlam.tpi.accounts.utils.UserDetailResponseBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final List<UserDetailStrategy> strategies;
    private final UserDetailResponseBuilder responseBuilder;

    @Override
    public UserDetailResponse getUserDetail(UserDetailRequest userDetailRequest) {
        return strategies.stream()
                .filter(strategy -> strategy.supports(userDetailRequest.getType()))
                .findFirst()
                .map(strategy -> {
                    UserEntity user = strategy.getUser(userDetailRequest);
                    return responseBuilder.build(user);
                })
                .orElseThrow(() -> {
                    log.error("Tipo de usuario no válido: {}", userDetailRequest.getType());
                    return new UserServiceBadRequestException("Tipo de usuario no válido");
                });
    }

}
