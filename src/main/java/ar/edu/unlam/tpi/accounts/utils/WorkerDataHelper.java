package ar.edu.unlam.tpi.accounts.utils;

import java.util.List;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorkerDataHelper {
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
                .build(),
            
            // TechSolutions Workers
            WorkerEntity.builder()
                .name("Carlos Rodríguez")
                .email("carlos.rodriguez@techsolutions.com")
                .phone("9876543210")
                .address("Av. Tecnología 456, Zona Industrial")
                .cuit("30-98765432-1")
                .isActive(true)
                .build(),
            WorkerEntity.builder()
                .name("Ana Martínez")
                .email("ana.martinez@techsolutions.com")
                .phone("9876543211")
                .address("Av. Tecnología 457, Zona Industrial")
                .cuit("30-98765433-2")
                .isActive(true)
                .build(),
            
            // AgroFertil Workers
            WorkerEntity.builder()
                .name("Roberto López")
                .email("roberto.lopez@agrofertil.com")
                .phone("4567891230")
                .address("Ruta 8 Km 45, Zona Rural")
                .cuit("27-45678912-3")
                .isActive(true)
                .build(),
            WorkerEntity.builder()
                .name("Laura Sánchez")
                .email("laura.sanchez@agrofertil.com")
                .phone("4567891231")
                .address("Ruta 8 Km 46, Zona Rural")
                .cuit("27-45678913-4")
                .isActive(true)
                .build()
        );
    }
}
