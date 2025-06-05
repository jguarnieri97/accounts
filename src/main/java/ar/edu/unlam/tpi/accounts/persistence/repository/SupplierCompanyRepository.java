package ar.edu.unlam.tpi.accounts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SupplierCompanyRepository extends JpaRepository<SupplierCompanyEntity, Long> {

    /**
     * Find all supplier companies from category, lat and ln.
     *
     * @return a list of all supplier companies.
     */
    @Query(
            value = "SELECT s.* FROM supplier_companies s " +
                    "JOIN geolocations g ON s.geolocation_id = g.id " +
                    "WHERE (:category IS NULL OR s.company_type = :category) AND " +
                    "(:lat IS NULL OR :ln IS NULL OR " +
                    "(6371 * acos(cos(radians(:lat)) * cos(radians(g.lat)) * cos(radians(g.ln) - radians(:ln)) + sin(radians(:lat)) * sin(radians(g.lat)))) <= :radius) " +
                    "ORDER BY (6371 * acos(cos(radians(:lat)) * cos(radians(g.lat)) * cos(radians(g.ln) - radians(:ln)) + sin(radians(:lat)) * sin(radians(g.lat)))) ASC",
            nativeQuery = true
    )
    List<SupplierCompanyEntity> findByCategoryAndLatAndLn(
            @Param("category") String category,
            @Param("lat") Float lat,
            @Param("ln") Float ln,
            @Param("radius") Float radius
    );
}
