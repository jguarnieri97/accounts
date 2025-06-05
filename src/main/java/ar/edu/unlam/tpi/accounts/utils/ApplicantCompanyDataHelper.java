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
                    .address("350 5th Ave, New York, NY 10118, USA")
                .build(),
            ApplicantCompanyEntity.builder()
                .name("Tech Innovators")
                .isVerified(true)
                .isActive(true)
                .cuit("20-1444444-9")
                .email("teh@gmail.com")
                .phone("12131231")
                .description("Tech Innovators is a startup focused on AI solutions.")
                    .address("111 S Grand Ave, Los Angeles, CA 90012, USA")
                .build(),
                ApplicantCompanyEntity.builder()
                        .name("Tech London")
                        .isVerified(true)
                        .isActive(true)
                        .cuit("20-12312312-9")
                        .email("tehLondon@gmail.com")
                        .phone("12131231")
                        .description("Tech Innovators is a startup focused on AI solutions.")
                        .address("10 Downing St, London SW1A 2AA, UK")
                        .build()
        );
    }
}
