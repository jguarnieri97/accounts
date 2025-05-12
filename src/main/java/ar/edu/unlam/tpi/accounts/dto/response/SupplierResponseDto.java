package ar.edu.unlam.tpi.accounts.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierResponseDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String description;
    private String type;
    private GeolocationResponseDto geolocation;
    private Float score;
    

    @JsonProperty("company_type")
    private CompanyResponseTypeDto companyType;

    @JsonProperty("avg_price")
    private Float avgPrice;

    @JsonProperty("comments_count")
    private Integer commentsCount;

    @JsonProperty("is_verified")
    private Boolean isVerified;
}
