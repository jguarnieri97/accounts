package ar.edu.unlam.tpi.accounts.controller;
import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;


 
/**
 * Controlador de endpoints relacionados con proveedores y sus métricas o trabajadores.
 */

@RequestMapping("/accounts/v1/suppliers")
public interface AccountController {

    /**
     * Obtiene todos los proveedores disponibles en el sistema.
     *
     * @return una respuesta genérica conteniendo una lista de proveedores.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all suppliers")
    GenericResponse<List<SupplierResponseDto>> getAllSuppliers();

    /**
     * Busca un proveedor por su ID.
     *
     * @param id el identificador único del proveedor.
     * @return una respuesta genérica conteniendo los datos del proveedor.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get supplier by ID")
    GenericResponse<SupplierResponseDto> getSupplierById(@PathVariable Long id);

    /**
     * Actualiza las métricas de un proveedor específico.
     *
     * @param supplierId el ID del proveedor a actualizar.
     * @param metrics objeto con las nuevas métricas del proveedor.
     * @return una respuesta genérica indicando el estado de la operación.
     */
    @PutMapping("/metrics/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update supplier metrics")
    GenericResponse<Void> putSupplierMetrics(@PathVariable("id") Long supplierId, @RequestBody MetricRequestDto metrics);

    /**
     * Obtiene los trabajadores asociados a una empresa proveedora por su ID.
     *
     * @param id el ID de la empresa proveedora.
     * @return una respuesta genérica conteniendo la lista de trabajadores asociados.
     */
    @GetMapping("/{id}/workers")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get workers by supplier company ID")
    GenericResponse<List<WorkerResponseDto>> getWorkersBySupplierCompanyId(@PathVariable Long id);

}
