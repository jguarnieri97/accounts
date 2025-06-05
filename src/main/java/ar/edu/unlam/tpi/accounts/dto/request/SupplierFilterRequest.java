package ar.edu.unlam.tpi.accounts.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SupplierFilterRequest {

    private String category;
    private Float lat;
    private Float ln;

}
