package ar.edu.unlam.tpi.accounts.utils;

import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;

import lombok.experimental.UtilityClass;
import java.util.List;

@UtilityClass
public class SupplierCompanyHelper {
    
    public static List<SupplierCompanyEntity> getSupplierCompanies() {
        return List.of(
            SupplierCompanyEntity.builder()
                .name("ElectraSol")
                .description("Soluciones eléctricas integrales para empresas, industrias y obras")
                .address("Calle 123, Ciudad")
                .phone("123456789")
                .email("employee@electrasol.com")
                .cuit("20-12345678-9")
                .isActive(true)
                .isVerified(true)
                .score(4.5f)
                .avgPrice(1500.0f)
                .commentsCount(10)
                .build(),
            SupplierCompanyEntity.builder()
                .name("Voltix")
                .description("Especialistas en instalaciones eléctricas modernas y tableros electrónicos.")
                .address("Av. Tecnología 456, Zona Industrial")
                .phone("987654321")
                .email("contacto@voltix.com")
                .cuit("30-98765432-1")
                .isActive(true)
                .isVerified(true)
                .score(4.8f)
                .avgPrice(2500.0f)
                .commentsCount(25)
                .build(),
            SupplierCompanyEntity.builder()
                .name("Lumenek")
                .description("Proveemos servicios eléctricos completamente sustentables.")
                .address("Ruta 8 Km 45, Zona Rural")
                .phone("456789123")
                .email("ventas@lumenek.com")
                .cuit("27-45678912-3")
                .isActive(true)
                .isVerified(true)
                .score(4.2f)
                .avgPrice(1800.0f)
                .commentsCount(15)
                .build()
        );
    }

}
