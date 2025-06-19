package ar.edu.unlam.tpi.accounts.mapper;

import ar.edu.unlam.tpi.accounts.dto.request.*;
import ar.edu.unlam.tpi.accounts.exceptions.UserServiceBadRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserRequestFactory {

    private final ObjectMapper objectMapper;

    public UserRequestFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public BaseUserRegisterRequestDto resolve(Map<String, Object> json) {
        String type = (String) json.get("type");

        if (type == null) {
            throw new UserServiceBadRequestException("Falta el campo 'type' para determinar el tipo de usuario");
        }

        return switch (type.toLowerCase()) {
            case "supplier" -> objectMapper.convertValue(json, SupplierRegisterRequestDto.class);
            case "worker"   -> objectMapper.convertValue(json, WorkerRegisterRequestDto.class);
            case "applicant"-> objectMapper.convertValue(json, ApplicantRegisterRequestDto.class);
            default         -> throw new UserServiceBadRequestException("Tipo de usuario inválido: " + type);
        };
    }
}
