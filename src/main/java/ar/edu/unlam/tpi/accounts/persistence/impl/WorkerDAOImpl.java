package ar.edu.unlam.tpi.accounts.persistence.impl;

import java.util.List;

import ar.edu.unlam.tpi.accounts.persistence.WorkerDAO;
import ar.edu.unlam.tpi.accounts.exceptions.InternalException;
import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.WorkerEntity;
import ar.edu.unlam.tpi.accounts.persistence.repository.WorkerRepository;
import ar.edu.unlam.tpi.accounts.utils.annotations.DataAccessObject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@DataAccessObject
@RequiredArgsConstructor
@Slf4j
public class WorkerDAOImpl implements WorkerDAO {

    private final WorkerRepository repository;

    @Override
    public List<WorkerEntity> findAll() {
        try{
            return repository.findAll();
        }
        catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public List<WorkerEntity> findByCompanyId(Long companyId) {
        try{
            List<WorkerEntity> workers = repository.findByCompanyId(companyId);
            if(workers.isEmpty()){
                throw new NotFoundException("Workers not found for the company");
            }
            return workers;
        }
        catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        } 
        catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public WorkerEntity findById(Long id) {
        try{
            return repository.findById(id)
                .orElseThrow(()-> new NotFoundException("Worker not found"));
        } 
        catch (NotFoundException e) {
            throw new NotFoundException(e.getDetail());
        }
        catch (Exception e) {
            throw new InternalException(e.getMessage());
        }
    }

    @Override
    public WorkerEntity save(WorkerEntity worker) {
        try{
            return repository.save(worker);
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
