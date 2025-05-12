package ar.edu.unlam.tpi.accounts.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.accounts.models.CompanyTypeEntity;

public interface CompanyTypeRepository extends JpaRepository<CompanyTypeEntity, Long> {
    Optional<CompanyTypeEntity> findByName(String name);
}

