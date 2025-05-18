package ar.edu.unlam.tpi.accounts.utils;

import java.util.List;

import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkerDataHelperTest {
    public static List<WorkerEntity> getWorkers(){
        return List.of(
            // Arrteh Workers
            WorkerEntity.builder()
                .name("Juan Pérez")
                .email("juan.perez@arrteh.com")
                .phone("1234567890")
                .address("Calle 123, Ciudad")
                .cuit("20-12345678-9")
                .isActive(true)
                .build(),
            WorkerEntity.builder()
                .name("María García")
                .email("maria.garcia@arrteh.com")
                .phone("1234567891")
                .address("Calle 124, Ciudad")
                .cuit("20-12345679-0")
                .isActive(true)
                .build()
        );
    }

    public static List<WorkerResponseDto> getWorkersDto(){
        return List.of(
            // Arrteh Workers
            WorkerResponseDto.builder()
                .name("Juan Pérez")
                .email("juan.perez@arrteh.com")
                .phone("1234567890")
                .address("Calle 123, Ciudad")
                .build(),
            WorkerResponseDto.builder()
                .name("María García")
                .email("maria.garcia@arrteh.com")
                .phone("1234567891")
                .address("Calle 124, Ciudad")
                .build()
        );
    }
}
