package ar.edu.unlam.tpi.accounts.dto.request;

import lombok.Data;

@Data
public class MetricRequestDto {
    private Long applicantId;

    private Double score;
    private String comment;
    private Integer price;

}