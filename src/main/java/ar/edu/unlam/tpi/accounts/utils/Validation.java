package ar.edu.unlam.tpi.accounts.utils;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class Validation {

    private String type;

    // Validación dinámica según type
    public static void validate(UserRegisterRequestDto request) {
        type = request.getType().toLowerCase();
        switch (type) {
            case "supplier" -> {
                requireNonNull(request.getLat(), "lat");
                requireNonNull(request.getLn(), "ln");
                requireNonEmpty(request.getDescription(), "description");
                requireNonEmpty(request.getCategory(), "category");
                requireNonEmpty(request.getLabels(), "labels");
            }
            case "applicant" -> {
                requireNonNull(request.getLat(), "lat");
                requireNonNull(request.getLn(), "ln");
                requireNonEmpty(request.getDescription(), "description");
            }
            case "worker" -> {
                requireNonNull(request.getCompanyId(), "companyId");
            }
            default -> throw new IllegalArgumentException("Tipo de usuario inválido: " + request.getType());
        }
    }

    private static void requireNonNull(Object value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException("El campo '" + fieldName + "' es obligatorio para el tipo '" + type + "'");
        }
    }

    private static void requireNonEmpty(Object value, String fieldName) {
        if (value == null ||
                (value instanceof String s && s.isBlank()) ||
                (value instanceof List<?> list && list.isEmpty())) {
            throw new IllegalArgumentException("El campo '" + fieldName + "' es obligatorio para el tipo '" + type + "'");
        }
    }
}
