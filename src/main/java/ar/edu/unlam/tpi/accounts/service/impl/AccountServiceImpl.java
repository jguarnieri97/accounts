package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.service.AccountService;
import ar.edu.unlam.tpi.accounts.utils.Converter;
import ar.edu.unlam.tpi.accounts.utils.MetricsCalculator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import ar.edu.unlam.tpi.accounts.persistence.dao.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.CategoryDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.dao.WorkerDAO;
import ar.edu.unlam.tpi.accounts.persistence.repository.CommentaryRepository;

import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.CategoryEntity;
import ar.edu.unlam.tpi.accounts.models.CommentaryEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;
import ar.edu.unlam.tpi.accounts.mapper.LabelMapper;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final SupplierCompanyDAO supplierCompanyDAO;
    private final WorkerDAO workerDAO;
    private final CommentaryRepository commentaryRepository;
    private final ApplicantCompanyDAO applicantCompanyDAO;
    private final MetricsCalculator metricsCalculator;
    private final LabelMapper labelMapper;
    private final CategoryDAO categoryDAO;

    @Value("${supplier.search.radius}")
    private Float searchRadius;


    @Override
    public List<SupplierResponseDto> getAllSuppliers(String category, Float lat, Float ln, String workResume) {
        Long categoryId = null;
        if (category != null) {
            CategoryEntity categoryType = categoryDAO.findByName(category);
            categoryId = categoryType != null ? categoryType.getId() : null;
        }
    
        List<SupplierCompanyEntity> suppliers = supplierCompanyDAO.findByCategoryAndLatAndLn(
            categoryId, lat, ln, searchRadius
        );
    
        if (workResume != null && !workResume.isBlank()) {
            Optional<String> mappedTag = labelMapper.getLabelByWorkResume(workResume);
            if (mappedTag.isPresent()) {
                String tag = mappedTag.get();
                suppliers = suppliers.stream()
                    .filter(supplier -> supplier.getLabels() != null &&
                        supplier.getLabels().stream()
                            .anyMatch(label -> label.getTag().equalsIgnoreCase(tag)))
                    .toList();
            } else {
                return List.of();
            }
        }
    
        return suppliers.stream()
            .map(supplier -> Converter.convertToDto(supplier, SupplierResponseDto.class))
            .collect(Collectors.toList());
    }

    

    @Override
    public SupplierResponseDto getSupplierById(Long id) {
        SupplierCompanyEntity supplier = supplierCompanyDAO.findById(id);
        return Converter.convertToDto(supplier, SupplierResponseDto.class);
    }

    @Override
    public List<WorkerResponseDto> getWorkersBySupplierCompanyId(Long companyId) {
        return workerDAO.findByCompanyId(companyId)
            .stream()
            .map(worker -> Converter.convertToDto(worker, WorkerResponseDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void updateSupplierMetrics(Long supplierId, MetricRequestDto metrics) {
        SupplierCompanyEntity supplier = supplierCompanyDAO.findById(supplierId);
        supplier.setCommentsCount(supplier.getCommentsCount() + 1);
        supplier.setAvgPrice(metricsCalculator.calculateAvgPrice(supplier, metrics.getPrice()));
        supplier.setScore(metricsCalculator.calculateAvgScore(supplier, metrics.getScore()));

        ApplicantCompanyEntity applicant = applicantCompanyDAO.findById(metrics.getApplicantId());

        CommentaryEntity commentaryEntity = new CommentaryEntity(
                metrics.getComment(),
                metrics.getScore(),
                supplier,
                applicant
        );

        commentaryRepository.save(commentaryEntity);
        supplierCompanyDAO.save(supplier);
    }
    
}
