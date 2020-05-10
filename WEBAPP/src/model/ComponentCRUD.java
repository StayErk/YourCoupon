package model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ComponentCRUD<T, S> {

    /**
     * Ritorna un signolo elemnto (se esiste) data la sua chiave
     * @param key
     * @return
     * @throws SQLException
     */
    public T retrieveByKey(S key) throws SQLException;

    /**
     * Ritorna tutti gli elementi di una tabella, opzionalmente può tornare gli elementi ordinati in un dato ordine
     * @param filter Colonna per cui ordinare i dati
     * @param order ASC | DESC
     * @return Lista di tutti gli elementi presi dal database
     * @throws SQLException
     */
    public List<T> retrieveAll(String filter, String order) throws SQLException;

    /**
     * Inserisce un elmento nella tabella
     * @param objectToSave elemento da inserire
     * @throws SQLException
     */
    public void doSave(T objectToSave) throws SQLException;

    /**
     * Modifica un dato elemento nel database
     * @param objectToUpdate elemento da modificare
     * @throws SQLException
     */
    public void doUpdate(T objectToUpdate) throws SQLException;

    /**
     * Cancella un elemento dal database
     * @param objectToDelete elemento da eliminare
     * @throws SQLException
     */
    public void doDelete(T objectToDelete) throws SQLException;
}
