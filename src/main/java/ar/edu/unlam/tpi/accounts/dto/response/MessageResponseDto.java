package ar.edu.unlam.tpi.accounts.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageResponseDto {
    private Integer code;
    private String message;
    private Object data;
}

