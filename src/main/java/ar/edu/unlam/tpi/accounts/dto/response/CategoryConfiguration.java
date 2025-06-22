package ar.edu.unlam.tpi.accounts.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CategoryConfiguration {

    private Map<String, List<String>> config;

}
