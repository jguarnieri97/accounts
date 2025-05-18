package ar.edu.unlam.tpi.accounts.persistence;

import java.util.List;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;

public interface WorkerDAO extends GenericInterfaceDAO<WorkerEntity, Long> {
     /**
     * Busca todos los trabajadores que pertenecen a una empresa específica.
     *
     * @param companyId el ID de la empresa.
     * @return una lista de trabajadores asociados a la empresa.
     * @throws ar.edu.unlam.tpi.accounts.exceptions.NotFoundException si no se encuentran trabajadores para la empresa.
     */
    List<WorkerEntity> findByCompanyId(Long companyId);
}
