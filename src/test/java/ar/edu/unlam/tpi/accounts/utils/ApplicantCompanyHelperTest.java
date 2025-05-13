package ar.edu.unlam.tpi.accounts.utils;

import java.util.List;

import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ApplicantCompanyHelperTest {
    
    public static List<ApplicantCompanyEntity> getApplicantCompanyList() {
        return List.of(
            ApplicantCompanyEntity.builder()
                .name("Nexwork")
                .isVerified(true)
                .isActive(true)
                .cuit("20-12345678-9")
                .email("nx@gmail.com")
                .phone("1231231")
                .description("Nexwork is a leading technology company.")
                .address("123 Nexwork St, Tech City")
                .build(),
            ApplicantCompanyEntity.builder()
                .name("Tech Innovators")
                .isVerified(true)
                .isActive(true)
                .cuit("20-1444444-9")
                .email("teh@gmail.com")
                .phone("12131231")
                .description("Tech Innovators is a startup focused on AI solutions.")
                .address("456 Innovation Ave, Silicon Valley")
                .build()
        );
    }
}
