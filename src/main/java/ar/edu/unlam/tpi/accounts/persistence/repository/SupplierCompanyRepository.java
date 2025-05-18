package ar.edu.unlam.tpi.accounts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;

public interface SupplierCompanyRepository extends JpaRepository<SupplierCompanyEntity, Long> {
}
