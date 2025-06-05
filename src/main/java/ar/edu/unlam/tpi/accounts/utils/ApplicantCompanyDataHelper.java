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
                    .address("Florencio Varela 1950, B1754 San Justo, Provincia de Buenos Aires")
                .build(),
            ApplicantCompanyEntity.builder()
                .name("Tech Innovators")
                .isVerified(true)
                .isActive(true)
                .cuit("20-1444444-9")
                .email("teh@gmail.com")
                .phone("12131231")
                .description("Tech Innovators is a startup focused on AI solutions.")
                    .address("Av. Santa Fe 5000, C1425BHY, Ciudad Autónoma de Buenos Aires")
                .build(),
                ApplicantCompanyEntity.builder()
                        .name("Tech London")
                        .isVerified(true)
                        .isActive(true)
                        .cuit("20-12312312-9")
                        .email("tehLondon@gmail.com")
                        .phone("12131231")
                        .description("Tech Innovators is a startup focused on AI solutions.")
                        .address("Esteban de Luca 2237, C1246ABS Cdad. Autónoma de Buenos Aires")
                        .build()
        );
    }
}
