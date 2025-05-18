package ar.edu.unlam.tpi.accounts.persistence;

import java.util.List;

/**
 * Interfaz genérica para operaciones básicas de acceso a datos (DAO).
 *
 * @param <T>  el tipo de entidad.
 * @param <ID> el tipo del identificador de la entidad.
 */
public interface GenericInterfaceDAO<T, ID> {

    /**
     * Recupera todas las entidades del tipo T.
     *
     * @return una lista de todas las entidades.
     */
    List<T> findAll();

    /**
     * Busca una entidad por su identificador.
     *
     * @param id el identificador de la entidad a buscar.
     * @return la entidad encontrada.
     */
    T findById(ID id);

    /**
     * Guarda una nueva entidad o actualiza una existente.
     *
     * @param entity la entidad a guardar.
     * @return la entidad guardada.
     */
    T save(T entity);

    /**
     * Elimina una entidad por su identificador.
     *
     * @param id el identificador de la entidad a eliminar.
     */
    void delete(ID id);
}
