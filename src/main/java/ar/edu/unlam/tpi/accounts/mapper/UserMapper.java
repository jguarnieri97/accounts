package ar.edu.unlam.tpi.accounts.mapper;

import ar.edu.unlam.tpi.accounts.dto.request.*;
import ar.edu.unlam.tpi.accounts.models.*;

public interface UserMapper {
    SupplierCompanyEntity toSupplierEntity(SupplierRegisterRequestDto req);
    WorkerEntity toWorkerEntity(WorkerRegisterRequestDto req, SupplierCompanyEntity supplier);
    ApplicantCompanyEntity toApplicantEntity(ApplicantRegisterRequestDto req);
}
