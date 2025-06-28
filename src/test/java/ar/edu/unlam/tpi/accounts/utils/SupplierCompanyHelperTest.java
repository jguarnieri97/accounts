package ar.edu.unlam.tpi.accounts.utils;

import lombok.experimental.UtilityClass;
import java.util.List;
import java.util.Set;

import ar.edu.unlam.tpi.accounts.dto.request.UserRegisterRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.models.CategoryEntity;
import ar.edu.unlam.tpi.accounts.models.Geolocation;
import ar.edu.unlam.tpi.accounts.models.LabelEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;

@UtilityClass
public class SupplierCompanyHelperTest {

    public static List<SupplierCompanyEntity> getSupplierCompanies(CategoryEntity category, Set<LabelEntity> labels) {
        return List.of(
                SupplierCompanyEntity.builder()
                        .name("Arrteh")
                        .description(
                                "Arrteh es una empresa dedicada a la fabricación de productos de limpieza y desinfección.")
                        .address("Calle 123, Ciudad")
                        .phone("123456789")
                        .email("artteh@gmail.com")
                        .cuit("20-12345678-9")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.5f)
                        .avgPrice(1500.0f)
                        .commentsCount(10)
                        .geolocation(new Geolocation(-34.602f, -58.381f))
                        .companyType(category)
                        .labels(labels)
                        .build(),

                SupplierCompanyEntity.builder()
                        .name("TechSolutions")
                        .description("Empresa líder en soluciones tecnológicas.")
                        .address("Av. Tecnología 456, Zona Industrial")
                        .phone("987654321")
                        .email("contacto@techsolutions.com")
                        .cuit("30-98765432-1")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.8f)
                        .avgPrice(2500.0f)
                        .commentsCount(25)
                        .geolocation(new Geolocation(-34.6037f, -58.3816f))
                        .companyType(category)
                        .labels(labels)
                        .build(),

                SupplierCompanyEntity.builder()
                        .name("AgroFertil")
                        .description("Proveedor de fertilizantes de alta calidad.")
                        .address("Ruta 8 Km 45, Zona Rural")
                        .phone("456789123")
                        .email("ventas@agrofertil.com")
                        .cuit("27-45678912-3")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.2f)
                        .avgPrice(1800.0f)
                        .commentsCount(15)
                        .geolocation(new Geolocation(-34.6037f, -58.3816f))
                        .companyType(category)
                        .labels(labels)
                        .build());
    }

    public static List<SupplierCompanyEntity> getSupplierCompanies() {
        CategoryEntity dummyCategory = new CategoryEntity();
        dummyCategory.setId(2L);
        dummyCategory.setName("CONTRACTOR");

        LabelEntity dummyLabel = new LabelEntity();
        dummyLabel.setId(9L);
        dummyLabel.setTag("wall_repair");

        return getSupplierCompanies(dummyCategory, Set.of(dummyLabel));
    }

    public static SupplierCompanyEntity getSupplier() {
        return SupplierCompanyEntity.builder()
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

    public static SupplierResponseDto getSupplierDto() {
        return SupplierResponseDto.builder()
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

    public static SupplierCompanyEntity buildTestSupplier(CategoryEntity category, Set<LabelEntity> labels) {
        return SupplierCompanyEntity.builder()
                .name("Test Supplier")
                .description("Proveedor de pruebas")
                .address("Calle Test 123")
                .phone("111111111")
                .email("test@supplier.com")
                .cuit("20-11111111-1")
                .isActive(true)
                .isVerified(true)
                .score(5.0f)
                .avgPrice(1000.0f)
                .commentsCount(0)
                .geolocation(new Geolocation(-34.6037f, -58.3816f))
                .companyType(category)
                .labels(labels)
                .build();
    }

    public static UserRegisterRequestDto buildSupplierRegisterRequest() {
        UserRegisterRequestDto request = new UserRegisterRequestDto();
        request.setType("supplier");
        request.setEmail("proveedor@empresa.com");
        request.setName("Proveedor S.A.");
        request.setPhone("123456789");
        request.setAddress("Calle Falsa 123");
        request.setCuit("20-12345678-9");
        request.setCategory("CONTRACTOR");
        request.setLabels(List.of("wall_repair", "roof_repair"));
        request.setLat(-34.6037f);
        request.setLn(-58.3816f);
        request.setDescription("Empresa dedicada a limpieza profesional");
        return request;
    }

}
