package ar.edu.unlam.tpi.accounts.persistence.dao.impl;

import ar.edu.unlam.tpi.accounts.exceptions.NotFoundException;
import ar.edu.unlam.tpi.accounts.models.CategoryEntity;
import ar.edu.unlam.tpi.accounts.persistence.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryDAOImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryDAOImpl categoryDAO;

    @Test
    void givenCategoryExists_whenFindByName_thenReturnsCategory() {
        String name = "ELECTRICIAN";
        CategoryEntity category = new CategoryEntity();
        category.setName(name);
        when(categoryRepository.findByName(name)).thenReturn(Optional.of(category));

        CategoryEntity result = categoryDAO.findByName(name);
        assertNotNull(result);
        assertEquals(name, result.getName());
    }

    @Test
    void givenCategoryDoesNotExist_whenFindByName_thenThrowsNotFoundException() {
        String name = "NON_EXISTENT";
        when(categoryRepository.findByName(name)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoryDAO.findByName(name));
    }
}
