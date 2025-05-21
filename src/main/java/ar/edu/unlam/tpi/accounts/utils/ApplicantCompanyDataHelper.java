package ar.edu.unlam.tpi.accounts.utils;

import lombok.experimental.UtilityClass;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import java.util.List;

@UtilityClass
public class ApplicantCompanyDataHelper {
    
    public static List<ApplicantCompanyEntity> getApplicantCompanyList() {
        return List.of(
            ApplicantCompanyEntity.builder()
                .name("Logibyte")
                .isVerified(true)
                .isActive(true)
                .cuit("20-12345678-9")
                .email("employee@lobibyte.com")
                .phone("+54 11 1234-5678")
                .description("Logibyte is a leading technology company.")
                .address("Av. Alberdi 35199, Buenos Aires")
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
