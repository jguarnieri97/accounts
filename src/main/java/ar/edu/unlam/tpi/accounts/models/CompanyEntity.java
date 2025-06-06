package ar.edu.unlam.tpi.accounts.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class CompanyEntity extends UserEntity {

    @Embedded
    private Geolocation geolocation;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "company_description")
    private String description;
}