package ar.edu.unlam.tpi.accounts.models;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
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
    @OneToOne
    @JoinColumn(name = "geolocation_id")
    private GeolocationEntity geolocation;

    private Boolean isVerified;
    private String description;
}