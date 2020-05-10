package model.restaurant;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantDAO implements ComponentCRUD<RestaurantBean, UUID> {

    /**
     * Restituisce un ristorante dalla tabelle StrutturaRistorativa dato l'id
     * @param key chiave del ristorante da cercare
     * @return un RestaurantBean
     * @throws SQLException
     */
    @Override
    public RestaurantBean retrieveByKey(UUID key) throws SQLException {
        String sql = "SELECT * FROM StruttureRistorative WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        RestaurantBean ristorante = new RestaurantBean();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key.toString());
            ResultSet result = preparedStatement.executeQuery();

            ristorante = mapFromResultSet(result);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return ristorante;
    }

    /**
     * Restituisce una lista di Ristoranti dalla tabella StrutturaRistorative
     * @param filter Colonna per cui ordinare i dati
     * @param order ASC | DESC
     * @return Lista ordinata di ristoranti
     * @throws SQLException
     */
    @Override
    public List<RestaurantBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM StruttureRistorative";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<RestaurantBean> ristoranti = new ArrayList<>();

        if(filter != null && !filter.equals("")){
            sql += " ORDER BY " + filter;
            if(order != null && !order.equals("")) {
                sql += " " + order;
            }
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet result = preparedStatement.executeQuery();

            mapFromResultSet(ristoranti, result);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return ristoranti;
    }

    /**
     * Salva un ristorante all'interno della tabella StrutturaRistorativa
     * @param objectToSave elemento da inserire
     * @throws SQLException
     */
    @Override
    public void doSave(RestaurantBean objectToSave) throws SQLException {
        String sql = "INSERT INTO StruttureRistorative (id, indirizzo, citta, nome, costo, immagine, numeroTelefono, email) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getId().toString());
            preparedStatement.setString(2, objectToSave.getIndirizzo());
            preparedStatement.setString(3, objectToSave.getCitta());
            preparedStatement.setString(4, objectToSave.getNome());
            preparedStatement.setDouble(5, objectToSave.getCosto());
            preparedStatement.setString(6, objectToSave.getImmagine());
            preparedStatement.setString(7, objectToSave.getNumeroTelefono());
            preparedStatement.setString(8, objectToSave.getEmail());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }


    /**
     * Aggiorna i campi di un determinato ristorante all'interno della tabella StrutturaRistorativa
     * @param objectToUpdate elemento da modificare
     * @throws SQLException
     */
    @Override
    public void doUpdate(RestaurantBean objectToUpdate) throws SQLException {
        String sql = "UPDATE StruttureRistorative SET " +
                "indirizzo = ?, " +
                "citta =  ?, " +
                "nome = ?, " +
                "costo = ?, " +
                "immagine = ?, " +
                "numeroTelefono = ?, " +
                "email = ? " + "WHERE id=?" ;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToUpdate.getIndirizzo());
            preparedStatement.setString(2, objectToUpdate.getCitta());
            preparedStatement.setString(3, objectToUpdate.getNome());
            preparedStatement.setDouble(4, objectToUpdate.getCosto());
            preparedStatement.setString(5, objectToUpdate.getImmagine());
            preparedStatement.setString(6, objectToUpdate.getNumeroTelefono());
            preparedStatement.setString(7, objectToUpdate.getEmail());
            preparedStatement.setString(8, objectToUpdate.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Cancella un ristorante all'interno della tabella StruttureRistorative
     * @param objectToDelete elemento da eliminare
     * @throws SQLException
     */
    @Override
    public void doDelete(RestaurantBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM StruttureRistorative WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToDelete.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    private void mapFromResultSet(List<RestaurantBean> ristoranti, ResultSet result) throws SQLException {
        while (result.next()){
            ristoranti.add( new RestaurantBean(
                    UUID.fromString(result.getString("id")),
                    result.getString("indirizzo"),
                    result.getString("citta"),
                    result.getString("nome"),
                    result.getDouble("costo"),
                    result.getString("immagine"),
                    result.getString("numeroTelefono"),
                    result.getString("email")
            ));
        }
    }

    private RestaurantBean mapFromResultSet(ResultSet result) throws SQLException {
        while (result.next()){
            return new RestaurantBean(
                    UUID.fromString(result.getString("id")),
                    result.getString("indirizzo"),
                    result.getString("citta"),
                    result.getString("nome"),
                    result.getDouble("costo"),
                    result.getString("immagine"),
                    result.getString("numeroTelefono"),
                    result.getString("email")
            );
        }
        return new RestaurantBean();
    }
}