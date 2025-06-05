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
                .build(),
                // AgroFertil Workers adicionales
                WorkerEntity.builder()
                        .name("Sofía Ramírez")
                        .email("sofia.ramirez@agrofertil.com")
                        .phone("4567891232")
                        .address("Ruta 8 Km 47, Zona Rural")
                        .cuit("27-45678914-5")
                        .isActive(true)
                        .build(),
                WorkerEntity.builder()
                        .name("Martín Gómez")
                        .email("martin.gomez@agrofertil.com")
                        .phone("4567891233")
                        .address("Ruta 8 Km 48, Zona Rural")
                        .cuit("27-45678915-6")
                        .isActive(true)
                        .build(),
                WorkerEntity.builder()
                        .name("Valentina Torres")
                        .email("valentina.torres@agrofertil.com")
                        .phone("4567891234")
                        .address("Ruta 8 Km 49, Zona Rural")
                        .cuit("27-45678916-7")
                        .isActive(true)
                        .build(),
                WorkerEntity.builder()
                        .name("Lucas Fernández")
                        .email("lucas.fernandez@agrofertil.com")
                        .phone("4567891235")
                        .address("Ruta 8 Km 50, Zona Rural")
                        .cuit("27-45678917-8")
                        .isActive(true)
                        .build(),
                WorkerEntity.builder()
                        .name("Camila Herrera")
                        .email("camila.herrera@agrofertil.com")
                        .phone("4567891236")
                        .address("Ruta 8 Km 51, Zona Rural")
                        .cuit("27-45678918-9")
                        .isActive(true)
                        .build(),
                WorkerEntity.builder()
                        .name("Joaquín Díaz")
                        .email("joaquin.diaz@agrofertil.com")
                        .phone("4567891237")
                        .address("Ruta 8 Km 52, Zona Rural")
                        .cuit("27-45678919-0")
                        .isActive(true)
                        .build(),
                WorkerEntity.builder()
                        .name("Lucía Castro")
                        .email("lucia.castro@agrofertil.com")
                        .phone("4567891238")
                        .address("Ruta 8 Km 53, Zona Rural")
                        .cuit("27-45678920-1")
                        .isActive(true)
                        .build(),
                WorkerEntity.builder()
                        .name("Emiliano Ruiz")
                        .email("emiliano.ruiz@agrofertil.com")
                        .phone("4567891239")
                        .address("Ruta 8 Km 54, Zona Rural")
                        .cuit("27-45678921-2")
                        .isActive(true)
                        .build()
        );
    }
}
