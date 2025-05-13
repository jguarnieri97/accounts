package ar.edu.unlam.tpi.accounts.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;

import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.MessageResponseDto;
import ar.edu.unlam.tpi.accounts.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/accounts/v1/suppliers")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all suppliers")
    public List<SupplierResponseDto> getAllSuppliers() {
        return accountService.searchAllSuppliers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get supplier by ID")
    public SupplierResponseDto getSupplierById(@PathVariable Long id) {
        return accountService.searchSupplierById(id);
    }

    @PutMapping("/metrics/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update supplier metrics")
    public MessageResponseDto putSupplierMetrics(@PathVariable("id") Long supplierId, @RequestBody MetricRequestDto metrics) {
        accountService.updateSupplierMetrics(supplierId,metrics);
        return MessageResponseDto.builder()
            .code(200)
            .message("UPDATED")
            .data(null)
            .build();
    }

    @GetMapping("/{id}/workers")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get workers by supplier company ID")
    public List<WorkerResponseDto> getWorkersBySupplierCompanyId(@PathVariable Long id) {
        return accountService.searchWorkersBySupplierCompanyId(id);
    }
}
