package ar.edu.unlam.tpi.accounts.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.edu.unlam.tpi.accounts.models.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  
    /**
     * Find a category by its name.
     *
     * @param name the name of the category.
     * @return the category entity if found, otherwise null.
     */
    Optional<CategoryEntity> findByName(String name);
    
}
