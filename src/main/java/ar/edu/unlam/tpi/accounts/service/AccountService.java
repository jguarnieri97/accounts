package ar.edu.unlam.tpi.accounts.service;

import java.util.List;

import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;

/**
 * Servicio que maneja la lógica de negocio relacionada con proveedores y sus trabajadores.
 */
public interface AccountService {

    /**
     * Obtiene una lista de todos los proveedores disponibles.
     *
     * @return una lista de objetos {@link SupplierResponseDto}.
     */
    List<SupplierResponseDto> searchAllSuppliers();

    /**
     * Obtiene los detalles de un proveedor específico por su ID.
     *
     * @param id el identificador único del proveedor.
     * @return un objeto {@link SupplierResponseDto} con los datos del proveedor.
     */
    SupplierResponseDto searchSupplierById(Long id);

    /**
     * Obtiene los trabajadores asociados a una empresa proveedora específica.
     *
     * @param companyId el ID de la empresa proveedora.
     * @return una lista de objetos {@link WorkerResponseDto} correspondientes a los trabajadores.
     */
    List<WorkerResponseDto> searchWorkersBySupplierCompanyId(Long companyId);

    /**
     * Actualiza las métricas de un proveedor determinado.
     *
     * @param supplierId el ID del proveedor.
     * @param metrics objeto con los datos de métricas a actualizar.
     */
    void updateSupplierMetrics(Long supplierId, MetricRequestDto metrics);
}
