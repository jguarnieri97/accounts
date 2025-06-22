package ar.edu.unlam.tpi.accounts.controller.impl;

import ar.edu.unlam.tpi.accounts.controller.CategoryController;
import ar.edu.unlam.tpi.accounts.dto.response.CategoryConfiguration;
import ar.edu.unlam.tpi.accounts.dto.response.GenericResponse;
import ar.edu.unlam.tpi.accounts.service.CategoryService;
import ar.edu.unlam.tpi.accounts.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public GenericResponse<CategoryConfiguration> getSupplierById() {
        var config = categoryService.getCategoryConfiguration();
        return GenericResponse.<CategoryConfiguration>builder()
                .code(Constants.STATUS_OK)
                .message(Constants.SUCCESS_MESSAGE)
                .data(config)
                .build();
    }
}
