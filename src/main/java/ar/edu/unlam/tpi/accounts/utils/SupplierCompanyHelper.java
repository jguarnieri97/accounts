package ar.edu.unlam.tpi.accounts.utils;

import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;

import lombok.experimental.UtilityClass;
import java.util.List;

@UtilityClass
public class SupplierCompanyHelper {
    
    public static List<SupplierCompanyEntity> getSupplierCompanies() {
        return List.of(
                //Electricity Suppliers
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
                    .build(),
                //Construction Suppliers
                SupplierCompanyEntity.builder()
                        .name("ConstruArq")
                        .description("Servicios de construcción y reformas integrales para empresas y particulares.")
                        .address("Av. Rivadavia 2000, Buenos Aires, Argentina")
                        .phone("01122334455")
                        .email("info@construarq.com")
                        .cuit("30-22334455-6")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.7f)
                        .avgPrice(3200.0f)
                        .commentsCount(16)
                        .build(),
                SupplierCompanyEntity.builder()
                        .name("ObrasPlus")
                        .description("Contratista general para obras civiles, industriales y comerciales.")
                        .address("Calle Obra 123, Córdoba, Argentina")
                        .phone("03511223344")
                        .email("contacto@obrasplus.com")
                        .cuit("27-33445566-7")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.8f)
                        .avgPrice(4100.0f)
                        .commentsCount(22)
                        .build(),
                SupplierCompanyEntity.builder()
                        .name("ReformasYA")
                        .description("Especialistas en remodelaciones, ampliaciones y mantenimiento edilicio.")
                        .address("Av. Pellegrini 789, Rosario, Argentina")
                        .phone("03411223344")
                        .email("servicios@reformasya.com")
                        .cuit("20-44556677-8")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.5f)
                        .avgPrice(2900.0f)
                        .commentsCount(10)
                        .build(),
                //Clean Suppliers
                SupplierCompanyEntity.builder()
                        .name("CleanPro")
                        .description("Servicios profesionales de limpieza para oficinas y empresas.")
                        .address("Av. Las Heras 1500, Mendoza, Argentina")
                        .phone("02611234567")
                        .email("contacto@cleanpro.com")
                        .cuit("30-33445566-7")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.7f)
                        .avgPrice(1950.0f)
                        .commentsCount(18)
                        .build(),
                SupplierCompanyEntity.builder()
                        .name("LimpiaSur")
                        .description("Limpieza integral de espacios urbanos y rurales.")
                        .address("Calle Principal 321, Salta, Argentina")
                        .phone("03871234567")
                        .email("info@limpiasur.com")
                        .cuit("27-22334455-6")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.4f)
                        .avgPrice(1600.0f)
                        .commentsCount(11)
                        .build(),
                SupplierCompanyEntity.builder()
                        .name("BrilloPro")
                        .description("Mantenimiento y limpieza profesional para industrias y comercios.")
                        .address("Av. San Martín 654, Tucumán, Argentina")
                        .phone("03811234567")
                        .email("servicios@brillopro.com")
                        .cuit("20-44556677-8")
                        .isActive(true)
                        .isVerified(true)
                        .score(4.5f)
                        .avgPrice(2300.0f)
                        .commentsCount(14)
                        .build()
        );
    }

}
