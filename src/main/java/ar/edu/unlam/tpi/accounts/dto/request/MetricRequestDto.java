package ar.edu.unlam.tpi.accounts.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MetricRequestDto {
    @JsonProperty("applicant_id")
    private Long applicantId;

    private Double score;
    private String comment;
    private Integer price;

}