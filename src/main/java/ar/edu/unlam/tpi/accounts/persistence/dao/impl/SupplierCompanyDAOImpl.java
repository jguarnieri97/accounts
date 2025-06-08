package ar.edu.unlam.tpi.accounts.persistence.dao.impl;

import java.util.List;

import ar.edu.unlam.tpi.accounts.persistence.dao.SupplierCompanyDAO;
import ar.edu.unlam.tpi.accounts.persistence.repository.SupplierCompanyRepository;
import lombok.extern.slf4j.Slf4j;

import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.SupplierCompanyEntity;

import lombok.RequiredArgsConstructor;
import ar.edu.unlam.tpi.accounts.utils.annotations.DataAccessObject;

@DataAccessObject
@RequiredArgsConstructor
@Slf4j
public class SupplierCompanyDAOImpl implements SupplierCompanyDAO {
    

    private final SupplierCompanyRepository repository;
    
    @Override
    public List<SupplierCompanyEntity> findAll() {
        try{
            List<SupplierCompanyEntity> suppliers = repository.findAll();
            return suppliers;
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<SupplierCompanyEntity> findByCategoryAndLatAndLn(Integer category, Float lat, Float ln, Float radius) {
        try {
            List<SupplierCompanyEntity> result = repository.findByCategoryAndLatAndLn(category, lat, ln, radius);
            if (result.isEmpty()) {
                throw new NotFoundException("No se encontraron proveedores para los filtros dados");
            }
            return result;
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
    
    @Override
    public SupplierCompanyEntity findById(Long id) {
        try{
            return repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Supplier not found"));
        } 
        catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        }
        catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public SupplierCompanyEntity findByEmail(String email) {
        try{
            return repository.findByEmail(email)
                    .orElseThrow(()-> new NotFoundException("Supplier not found"));
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        }
        catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public SupplierCompanyEntity save(SupplierCompanyEntity supplierCompany) {
        try{
            return repository.save(supplierCompany);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
    
    @Override
    public void delete(Long id) {
        try{
            repository.deleteById(id);
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
}