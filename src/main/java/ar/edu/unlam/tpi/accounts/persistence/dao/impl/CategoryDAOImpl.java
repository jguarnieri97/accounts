package ar.edu.unlam.tpi.accounts.persistence.dao.impl;

import org.apache.logging.log4j.util.InternalException;

import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.CategoryEntity;
import ar.edu.unlam.tpi.accounts.persistence.dao.CategoryDAO;
import ar.edu.unlam.tpi.accounts.persistence.repository.CategoryRepository;
import ar.edu.unlam.tpi.accounts.utils.annotations.DataAccessObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DataAccessObject
@RequiredArgsConstructor
@Slf4j
public class CategoryDAOImpl implements CategoryDAO {

    private final CategoryRepository repository;


    @Override
    public CategoryEntity findByName(String name) {
        try {
            return repository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        } catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        } catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }
    
}
