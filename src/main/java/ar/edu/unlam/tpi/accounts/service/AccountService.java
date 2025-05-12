package ar.edu.unlam.tpi.accounts.service;

import java.util.List;

import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;

public interface AccountService {
    List<SupplierResponseDto> searchAllSuppliers();
    SupplierResponseDto searchSupplierById(Long id);
    List<WorkerResponseDto> searchWorkersBySupplierCompanyId(Long companyId);
    void updateSupplierMetrics(Long supplierId, MetricRequestDto metrics);
}
