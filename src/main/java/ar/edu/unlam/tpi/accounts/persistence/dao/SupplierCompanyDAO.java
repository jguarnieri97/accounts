package ar.edu.unlam.tpi.accounts.persistence.dao;

import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;

import java.util.List;

public interface SupplierCompanyDAO extends GenericInterfaceDAO<SupplierCompanyEntity, Long> {
    /**
     * Find all supplier companies from category, lat and ln.
     *
     * @return a list of all supplier companies.
     */
    List<SupplierCompanyEntity> findByCategoryAndLatAndLn(Long category, Float lat, Float ln, Float radius);
}
