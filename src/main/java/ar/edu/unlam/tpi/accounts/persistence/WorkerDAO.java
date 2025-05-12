package ar.edu.unlam.tpi.accounts.persistence;

import java.util.List;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;

public interface WorkerDAO extends GenericInterfaceDAO<WorkerEntity, Long> {
    List<WorkerEntity> findByCompanyId(Long companyId);
}
