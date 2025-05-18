package ar.edu.unlam.tpi.accounts.persistence.impl;

import java.util.List;

import ar.edu.unlam.tpi.accounts.persistence.CompanyTypeDAO;
import ar.edu.unlam.tpi.accounts.models.CompanyTypeEntity;
import ar.edu.unlam.tpi.accounts.persistence.repository.CompanyTypeRepository;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.utils.annotations.DataAccessObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DataAccessObject
@RequiredArgsConstructor
@Slf4j
public class CompanyTypeDAOImpl implements CompanyTypeDAO{
    
    private final CompanyTypeRepository companyTypeRepository;

    @Override
    public List<CompanyTypeEntity> findAll() {
        try{
            return companyTypeRepository.findAll();
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public CompanyTypeEntity findById(Long id) {
        try{
            return companyTypeRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Company type not found"));
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        }
        catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public CompanyTypeEntity save(CompanyTypeEntity entity) {
        try{
            return companyTypeRepository.save(entity);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try{
            companyTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
}
