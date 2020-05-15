package model.cliente;


import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;
import model.carrello.CarrelloBean;
import model.carrello.CarrelloDAO;
import model.hotel.HotelBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClienteDAO implements ComponentCRUD<ClienteBean, String>{

    /**
     * Restituisce un singolo Cliente data una chiave
     * @param key email del cliente
     * @return ClienteBean con email key
     * @throws SQLException
     */
    @Override
    public ClienteBean retrieveByKey(String key) throws SQLException {
        String sql = "SELECT * FROM Cliente where email = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ClienteBean cliente;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key);
            ResultSet rs = preparedStatement.executeQuery();

            cliente = mapFromResultSet(rs);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return cliente;
    }

    /**
     * Restituisce tutti i Clienti memorizzati nella trabella Clienti
     * Li ordina secondo il valore di una determinata colonna, in senso crescente o decrescente
     * @param filter Colonna per cui ordinare i dati
     * @param order ASC | DESC
     * @return Lista di ClienteBean Ordinata
     * @throws SQLException
     */
    @Override
    public List<ClienteBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM Cliente";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<ClienteBean> clienti = new ArrayList<>();

        if(filter != null && !filter.equals("")) {
            sql += " ORDER BY " + filter;
            if(order != null && !order.equals("")) {
                sql += " " + order;
            }
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            mapFromResultSet(clienti, rs);
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return clienti;
    }

    @Override
    public void doSave(ClienteBean objectToSave) throws SQLException {
        CarrelloDAO modelDAO = new CarrelloDAO();
        String sql = "INSERT INTO Cliente " +
                "(nome, cognome, puntiViaggio, email, password, admin, immagine) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getNome());
            preparedStatement.setString(2, objectToSave.getCognome());
            preparedStatement.setInt(3, objectToSave.getPuntiViaggio());
            preparedStatement.setString(4, objectToSave.getEmail());
            preparedStatement.setBytes(5, objectToSave.getPassword());
            preparedStatement.setBoolean(6, objectToSave.isAdmin());
            preparedStatement.setString(7, objectToSave.getImmagine());

            preparedStatement.executeUpdate();
            connection.commit();
            CarrelloBean carrelloBean = new CarrelloBean(objectToSave.getEmail(), 0);
            modelDAO.doSave(carrelloBean);
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
    public void doUpdate(ClienteBean objectToUpdate) throws SQLException {
        String sql = "UPDATE Cliente SET " +
                "nome = ?, " +
                "cognome = ?, " +
                "puntiViaggio = ?, " +
                "password = ?, " + //password già codificata
                "admin = ?, " +
                "immagine = ? " +
                "WHERE email = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToUpdate.getNome());
            preparedStatement.setString(2, objectToUpdate.getCognome());
            preparedStatement.setInt(3, objectToUpdate.getPuntiViaggio());
            preparedStatement.setBytes(4, objectToUpdate.getPassword());
            preparedStatement.setBoolean(5, objectToUpdate.isAdmin());
            preparedStatement.setString(6, objectToUpdate.getImmagine());
            preparedStatement.setString(7, objectToUpdate.getEmail());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            }
            finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void doDelete(ClienteBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE email = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToDelete.getEmail());

            preparedStatement.executeUpdate();
            connection.commit();
        }
        finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            }
            finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    private ClienteBean mapFromResultSet(ResultSet rs) throws SQLException {
        if(rs.next()){
            return new ClienteBean(
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getInt("puntiViaggio"),
                    rs.getString("email"),
                    rs.getBytes("password"),
                    rs.getBoolean("admin"),
                    rs.getString("immagine")
            );
        }
        return new ClienteBean();
    }

    private void mapFromResultSet(List<ClienteBean> clienti, ResultSet rs) throws SQLException {
        while (rs.next()) {
            clienti.add(new ClienteBean(
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getInt("puntiViaggio"),
                    rs.getString("email"),
                    rs.getBytes("password"),
                    rs.getBoolean("admin"),
                    rs.getString("immagine")
            ));
        }
    }
}
