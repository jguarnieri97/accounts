package ar.edu.unlam.tpi.accounts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SupplierCompanyRepository extends JpaRepository<SupplierCompanyEntity, Long> {

    /**
     * Find all supplier companies from category, lat and ln.
     *
     * @return a list of all supplier companies.
     */
    @Query(
            value = "SELECT s.* FROM USERS.SUPPLIER_COMPANY s " +
                    "WHERE (:category IS NULL OR s.company_type = :category) AND " +
                    "(:lat IS NULL OR :ln IS NULL OR " +
                    "(6371 * acos(cos(radians(:lat)) * cos(radians(s.lat)) * cos(radians(s.ln) - radians(:ln)) + sin(radians(:lat)) * sin(radians(s.lat)))) <= :radius) " +
                    "ORDER BY (6371 * acos(cos(radians(:lat)) * cos(radians(s.lat)) * cos(radians(s.ln) - radians(:ln)) + sin(radians(:lat)) * sin(radians(s.lat)))) ASC",
            nativeQuery = true
    )
    List<SupplierCompanyEntity> findByCategoryAndLatAndLn(
            @Param("category") Long category,
            @Param("lat") Float lat,
            @Param("ln") Float ln,
            @Param("radius") Float radius
    );

    Optional<SupplierCompanyEntity> findByEmail(String email);
}
