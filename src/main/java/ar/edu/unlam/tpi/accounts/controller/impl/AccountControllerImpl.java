package ar.edu.unlam.tpi.accounts.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.controller.AccountController;
import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.service.AccountService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountControllerImpl implements AccountController {
    private final AccountService accountService;
    
    @Override
    public GenericResponse<List<SupplierResponseDto>> getAllSuppliers() {
        return GenericResponse.<List<SupplierResponseDto>>builder()
            .code(Constants.STATUS_OK)
            .data(accountService.searchAllSuppliers())
            .message(Constants.SUCCESS_MESSAGE)
            .build();
    }
    

    @Override
    public GenericResponse<SupplierResponseDto> getSupplierById(Long id) {
        return GenericResponse.<SupplierResponseDto>builder()
        .code(Constants.STATUS_OK)
        .data(accountService.searchSupplierById(id))
        .message(Constants.SUCCESS_MESSAGE)
        .build();
    }

    @Override
    public GenericResponse<Void> putSupplierMetrics(Long supplierId, MetricRequestDto metrics) {
        accountService.updateSupplierMetrics(supplierId, metrics);
        return GenericResponse.<Void>builder()
            .code(Constants.STATUS_OK)
            .message(Constants.UPDATE_MESSAGE)
            .data(null)
            .build();
    }

    @Override
    public GenericResponse<List<WorkerResponseDto>> getWorkersBySupplierCompanyId(Long id) {
        return GenericResponse.<List<WorkerResponseDto>>builder()
            .code(Constants.STATUS_OK) 
            .message(Constants.SUCCESS_MESSAGE)
            .data(accountService.searchWorkersBySupplierCompanyId(id))    
        .build();
    }

}
