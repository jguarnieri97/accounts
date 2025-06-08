package ar.edu.unlam.tpi.accounts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;

import java.util.Optional;

public interface ApplicantCompanyRepository extends JpaRepository<ApplicantCompanyEntity, Long> {

    Optional<ApplicantCompanyEntity> findByEmail(String email);
}