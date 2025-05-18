package ar.edu.unlam.tpi.accounts.service.impl;

import ar.edu.unlam.tpi.accounts.service.AccountService;
import ar.edu.unlam.tpi.accounts.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import ar.edu.unlam.tpi.accounts.persistence.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.WorkerDAO;
import ar.edu.unlam.tpi.accounts.persistence.repository.CommentaryRepository;

import lombok.extern.slf4j.Slf4j;

import ar.edu.unlam.tpi.accounts.dto.request.MetricRequestDto;
import ar.edu.unlam.tpi.accounts.dto.response.SupplierResponseDto;
import ar.edu.unlam.tpi.accounts.dto.response.WorkerResponseDto;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.models.CommentaryEntity;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final SupplierCompanyDAO supplierCompanyDAO;
    private final WorkerDAO workerDAO;
    private final CommentaryRepository commentaryRepository;
    private final ApplicantCompanyDAO applicantCompanyDAO;


    @Override
    public List<SupplierResponseDto> searchAllSuppliers() {
        return supplierCompanyDAO.findAll()
            .stream()
            .map(supplier -> Converter.convertToDto(supplier, SupplierResponseDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public SupplierResponseDto searchSupplierById(Long id) {
        SupplierCompanyEntity supplier = supplierCompanyDAO.findById(id);
        return Converter.convertToDto(supplier, SupplierResponseDto.class);
    }

    @Override
    public List<WorkerResponseDto> searchWorkersBySupplierCompanyId(Long companyId) {
        return workerDAO.findByCompanyId(companyId)
            .stream()
            .map(worker -> Converter.convertToDto(worker, WorkerResponseDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public void updateSupplierMetrics(Long supplierId, MetricRequestDto metrics) {
        SupplierCompanyEntity supplier = supplierCompanyDAO.findById(supplierId);
        supplier.setCommentsCount(supplier.getCommentsCount() + 1);
        supplier.setAvgPrice(calculateAvgPrice(supplier, metrics.getPrice()));
        supplier.setScore(calculateAvgScore(supplier, metrics.getScore()));

        ApplicantCompanyEntity applicant = applicantCompanyDAO.findById(metrics.getApplicantId());

        CommentaryEntity commentaryEntity = CommentaryEntity.builder()
            .comment(metrics.getComment())
            .score(metrics.getScore())
            .supplierCompany(supplier)
            .applicantCompany(applicant)
            .build();

        commentaryEntity = commentaryRepository.save(commentaryEntity);

        supplierCompanyDAO.save(supplier);
    }

    private Float calculateAvgPrice(SupplierCompanyEntity supplier, Integer price) {
        float currentTotal = supplier.getAvgPrice() * (supplier.getCommentsCount()-1);
        float newTotal = currentTotal + price;
        return newTotal / supplier.getCommentsCount();
    }
    
    private Float calculateAvgScore(SupplierCompanyEntity supplier, Double score) {
        float currentTotal = supplier.getScore() * (supplier.getCommentsCount()-1);
        float newTotal = currentTotal + score.floatValue();
        return newTotal / supplier.getCommentsCount();
    }
    
}
