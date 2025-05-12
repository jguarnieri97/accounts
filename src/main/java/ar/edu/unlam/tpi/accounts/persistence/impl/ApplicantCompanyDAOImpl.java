package ar.edu.unlam.tpi.accounts.persistence.impl;

import java.util.List;
import ar.edu.unlam.tpi.accounts.persistence.ApplicantCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.repository.ApplicantCompanyRepository;
import ar.edu.unlam.tpi.accounts.models.ApplicantCompanyEntity;
import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.utils.annotations.DataAccessObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DataAccessObject
@RequiredArgsConstructor
@Slf4j
public class ApplicantCompanyDAOImpl implements ApplicantCompanyDAO {
    
    private final ApplicantCompanyRepository repository;
    
    @Override
    public List<ApplicantCompanyEntity> findAll() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public ApplicantCompanyEntity findById(Long id) {
        try {
            return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Applicant not found"));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public ApplicantCompanyEntity save(ApplicantCompanyEntity entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
}
