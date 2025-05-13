package ar.edu.unlam.tpi.accounts.utils;

import lombok.experimental.UtilityClass;
import ar.edu.unlam.tpi.accounts.models.CompanyTypeEntity;
import java.util.List;

@UtilityClass
public class CompanyTypeHelperTest {

    public static List<CompanyTypeEntity> getCompanyTypes() {
        return List.of(
            CompanyTypeEntity.builder().name("Alimentos y Bebidas").build(),
            CompanyTypeEntity.builder().name("Construcción e Infraestructura").build(),
            CompanyTypeEntity.builder().name("Logística y Transporte").build(),
            CompanyTypeEntity.builder().name("Salud y Farmacéutica").build(),
            CompanyTypeEntity.builder().name("Tecnología y Software").build(),
            CompanyTypeEntity.builder().name("Finanzas y Seguros").build(),
            CompanyTypeEntity.builder().name("Industrial y Manufactura").build(),
            CompanyTypeEntity.builder().name("Agroindustria").build(),
            CompanyTypeEntity.builder().name("Retail Mayorista").build(),
            CompanyTypeEntity.builder().name("Educación y Formación").build(),
            CompanyTypeEntity.builder().name("Energía y Minería").build(),
            CompanyTypeEntity.builder().name("Publicidad y Marketing").build(),
            CompanyTypeEntity.builder().name("Servicios Profesionales").build(),
            CompanyTypeEntity.builder().name("Telecomunicaciones").build(),
            CompanyTypeEntity.builder().name("Mobiliario y Equipamiento").build(),
            CompanyTypeEntity.builder().name("Importación y Exportación").build(),
            CompanyTypeEntity.builder().name("Recursos Humanos y Consultoría").build(),
            CompanyTypeEntity.builder().name("Textil y Moda Mayorista").build(),
            CompanyTypeEntity.builder().name("Limpieza e Higiene Industrial").build(),
            CompanyTypeEntity.builder().name("Seguridad y Vigilancia").build()
        );
    }
    
}