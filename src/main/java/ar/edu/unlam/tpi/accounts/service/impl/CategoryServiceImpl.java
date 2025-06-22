package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.dto.response.CategoryConfiguration;
import ar.edu.unlam.tpi.accounts.models.LabelEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.LabelDAO;
import ar.edu.unlam.tpi.accounts.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final LabelDAO labelDAO;

    @Override
    public CategoryConfiguration getCategoryConfiguration() {
        List<LabelEntity> labels = labelDAO.findAll();

        Map<String, List<String>> config = labels.stream()
                .collect(Collectors.groupingBy(labelEntity -> labelEntity.getCategory().getName(),
                        Collectors.mapping(LabelEntity::getTag, Collectors.toList())));

        return CategoryConfiguration.builder()
                .config(config)
                .build();
    }
}
