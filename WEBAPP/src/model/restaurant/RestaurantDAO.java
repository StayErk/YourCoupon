package model.restaurant;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantDAO implements ComponentCRUD<RestaurantBean, UUID> {

    @Override
    public RestaurantBean retrieveByKey(UUID key) throws SQLException {
        String sql = "SELECT * FROM StrutturaRistorativa WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        RestaurantBean ristorante = new RestaurantBean();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key.toString());
            ResultSet result = preparedStatement.executeQuery();

            mapFromResultSet(ristorante, result);
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

    @Override
    public List<RestaurantBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM StruttureRistorative";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<RestaurantBean> ristoranti = new ArrayList<>();

        if(order != null && !order.equals("")){
            sql += " ORDER BY " + filter + " " + order;
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

    @Override
    public void doSave(RestaurantBean objectToSave) throws SQLException {
        String sql = "INSERT INTO StruttureRistorative (id, indirizzo, nome, costo, immagine, numeroTelefono, email) " +
                    "VALUES (UUID(), ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, objectToSave.getIndirizzo());
            preparedStatement.setString(2, objectToSave.getNome());
            preparedStatement.setDouble(3, objectToSave.getCosto());
            preparedStatement.setString(4, objectToSave.getImmagine());
            preparedStatement.setString(5, objectToSave.getNumeroTelefono());
            preparedStatement.setString(6, objectToSave.getEmail());

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

    @Override
    public void doUpdate(RestaurantBean objectToUpdate) throws SQLException {
        String sql = "UPDATE StruttureRistorative SET " +
                "indirizzo = ? " +
                "nome = ? " +
                "costo = ? " +
                "immagine = ? " +
                "numeroTelefono = ? " +
                "email = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToUpdate.getIndirizzo());
            preparedStatement.setString(2, objectToUpdate.getNome());
            preparedStatement.setDouble(3, objectToUpdate.getCosto());
            preparedStatement.setString(4, objectToUpdate.getImmagine());
            preparedStatement.setString(5, objectToUpdate.getNumeroTelefono());
            preparedStatement.setString(6, objectToUpdate.getEmail());

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

    @Override
    public void doDelete(RestaurantBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM StruttureRistorative id = ?";
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
                    result.getString("nome"),
                    result.getDouble("costo"),
                    result.getString("immagine"),
                    result.getString("numeroTelefono"),
                    result.getString("email")
            ));
        }
    }

    private void mapFromResultSet(RestaurantBean ristorante, ResultSet result) throws SQLException {
        while (result.next()){
            ristorante = new RestaurantBean(
                    UUID.fromString(result.getString("id")),
                    result.getString("indirizzo"),
                    result.getString("nome"),
                    result.getDouble("costo"),
                    result.getString("immagine"),
                    result.getString("numeroTelefono"),
                    result.getString("email")
            );
        }
    }
}
