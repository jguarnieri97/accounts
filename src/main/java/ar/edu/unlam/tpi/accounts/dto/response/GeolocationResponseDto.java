package ar.edu.unlam.tpi.accounts.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocationResponseDto {
    private Float lat;
    private Float ln;
} 