package ar.edu.unlam.tpi.accounts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.unlam.tpi.accounts.models.GeolocationEntity;

public interface GeolocationRepository extends JpaRepository<GeolocationEntity, Long> {
}
