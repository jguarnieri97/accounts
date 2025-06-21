package ar.edu.unlam.tpi.accounts.persistence.dao;

import ar.edu.unlam.tpi.accounts.models.CategoryEntity;

public interface CategoryDAO {

    /**
     * Busca una categoría por su nombre.
     *
     * @param name el nombre de la categoría.
     * @return la entidad de la categoría si se encuentra, de lo contrario null.
     */
    CategoryEntity findByName(String name);
}
