package ar.edu.unlam.tpi.accounts.dto.response;

import lombok.Data;

@Data
public class WorkerResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;   
    private String address;
    private SupplierResponseDto company;
}
