package ar.edu.unlam.tpi.accounts.controller;

import ar.edu.unlam.tpi.accounts.dto.response.CategoryConfiguration;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/accounts/v1/category")
public interface CategoryController {

    @GetMapping("/config")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get category configuration")
    GenericResponse<CategoryConfiguration> getSupplierById();

}
