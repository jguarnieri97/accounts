package ar.edu.unlam.tpi.accounts.utils;

import lombok.experimental.UtilityClass;
import java.util.List;

import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;


@UtilityClass
public class SupplierCompanyHelperTest {
    public static List<SupplierCompanyEntity> getSupplierCompanies() {
        return List.of(
            SupplierCompanyEntity.builder()
                .name("Arrteh")
                .description("Arrteh es una empresa dedicada a la fabricación de productos de limpieza y desinfección para el hogar y la industria. Ofrecemos una amplia gama de productos de alta calidad y eficacia.")
                .address("Calle 123, Ciudad")
                .phone("123456789")
                .email("artteh@gmail.ccom")
                .cuit("20-12345678-9")
                .isActive(true)
                .isVerified(true)
                .score(4.5f)
                .avgPrice(1500.0f)
                .commentsCount(10)
                .build(),
            SupplierCompanyEntity.builder()
                .name("TechSolutions")
                .description("Empresa líder en soluciones tecnológicas y desarrollo de software a medida.")
                .address("Av. Tecnología 456, Zona Industrial")
                .phone("987654321")
                .email("contacto@techsolutions.com")
                .cuit("30-98765432-1")
                .isActive(true)
                .isVerified(true)
                .score(4.8f)
                .avgPrice(2500.0f)
                .commentsCount(25)
                .build(),
            SupplierCompanyEntity.builder()
                .name("AgroFertil")
                .description("Proveedor de fertilizantes y productos agrícolas de alta calidad.")
                .address("Ruta 8 Km 45, Zona Rural")
                .phone("456789123")
                .email("ventas@agrofertil.com")
                .cuit("27-45678912-3")
                .isActive(true)
                .isVerified(true)
                .score(4.2f)
                .avgPrice(1800.0f)
                .commentsCount(15)
                .build()
        );
    }

    public static SupplierCompanyEntity getSupplier(){
        return  SupplierCompanyEntity.builder()
            .id(1L)
            .name("AgroFertil")
            .description("Proveedor de fertilizantes y productos agrícolas de alta calidad.")
            .address("Ruta 8 Km 45, Zona Rural")
            .phone("456789123")
            .email("ventas@agrofertil.com")
            .cuit("27-45678912-3")
            .isActive(true)
            .isVerified(true)
            .score(4.2f)
            .avgPrice(1800.0f)
            .commentsCount(15)
            .build();
    }

    public static SupplierResponseDto getSupplierDto(){
        return  SupplierResponseDto.builder()
            .id(1L)
            .name("AgroFertil")
            .description("Proveedor de fertilizantes y productos agrícolas de alta calidad.")
            .address("Ruta 8 Km 45, Zona Rural")
            .phone("456789123")
            .email("ventas@agrofertil.com")
            .cuit("27-45678912-3")
            .score(4.2f)
            .avgPrice(1800.0f)
            .commentsCount(15)
            .build();
    }
}
