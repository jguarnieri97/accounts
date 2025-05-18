package ar.edu.unlam.tpi.accounts.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import java.util.List;

public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {
    List<WorkerEntity> findByCompanyId(Long companyId);
}
