package model.hotel;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Questa classe implementa tutte le operazioni CRUD relative alla tabella
 * Struttura Alberghiera
 */
public class hotelDAO implements ComponentCRUD<HotelBean, UUID> {

    /**
     * Restituisce un singolo Hotel data una chiave
     * @param key chiave dell'hotel
     * @return HotelBean con chiave key
     * @throws SQLException
     */
    @Override
    public HotelBean retrieveByKey(UUID key) throws SQLException {
        String sql = "SELECT * FROM StrutturaAlberghiera WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        HotelBean hotel = new HotelBean();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key.toString());
            ResultSet rs = preparedStatement.executeQuery();

            mapFromResultSet(hotel, rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return null;
    }

    /**
     * Restutuisce tutti gli Hotel memorizzati nella tabella StrutturaAlberghiera
     * Ordina il result set secondo una determinata colonna
     * @param filter Colonna per cui ordinare i dati
     * @param order ASC | DESC
     * @return Lista di HotelBean Ordinata
     * @throws SQLException
     */
    @Override
    public List<HotelBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM StrutturaAlberghiera";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<HotelBean> hotels = new ArrayList<>();

        if(filter != null && !filter.equals("")){
            sql += " ORDER BY " + filter;
            if (order != null && !order.equals("")) {
                sql += " " + order;
            }
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            mapFromResultSet(hotels, rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return hotels;
    }


    /**
     * Salva un hotel all'interno della tabella Struttura Albergiera
     * @param objectToSave elemento da inserire
     * @throws SQLException
     */
    @Override
    public void doSave(HotelBean objectToSave) throws SQLException {
        String sql = "INSERT INTO StrutturaAlberghiera + " +
                "(id, nome, indirizzo, costoNotte, stelle, immagine, email, numeroTelefono) " +
                "VALUES (UUID(), ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getNome());
            preparedStatement.setString(2, objectToSave.getIndirizzo());
            preparedStatement.setDouble(3, objectToSave.getCostoNotte());
            preparedStatement.setInt(4, objectToSave.getStelle());
            preparedStatement.setString(5, objectToSave.getImmagine());
            preparedStatement.setString(6, objectToSave.getEmail());
            preparedStatement.setString(7, objectToSave.getNumeroTelefono());

            preparedStatement.executeUpdate();
            connection.commit();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Aggiorna un hotel all'interno della tabella StrutturaAlberghiera
     * @param objectToUpdate elemento da modificare
     * @throws SQLException
     */
    @Override
    public void doUpdate(HotelBean objectToUpdate) throws SQLException {
        String sql = "UPDATE StrutturaAlberghiera SET nome = ? " +
                    "indirizzo = ? " +
                    "costoNotte = ? " +
                    "stelle = ? " +
                    "immagine = ? " +
                    "email = ? " +
                    "numeroTelefono = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToUpdate.getNome());
            preparedStatement.setString(2, objectToUpdate.getIndirizzo());
            preparedStatement.setDouble(3, objectToUpdate.getCostoNotte());
            preparedStatement.setInt(4, objectToUpdate.getStelle());
            preparedStatement.setString(5, objectToUpdate.getImmagine());
            preparedStatement.setString(6, objectToUpdate.getEmail());
            preparedStatement.setString(7, objectToUpdate.getNumeroTelefono());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Cancella un hotel all'interno della tabella StrutturaAlberghiera
     * @param objectToDelete elemento da eliminare
     * @throws SQLException
     */
    @Override
    public void doDelete(HotelBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM StrutturaAlberghiera WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToDelete.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();
        } finally {
            try {
               if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }


    private void mapFromResultSet(List<HotelBean> hotels, ResultSet rs) throws SQLException {
        while (rs.next()) {
            hotels.add(new HotelBean(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("nome"),
                    rs.getString("indirizzo"),
                    rs.getDouble("costoNotte"),
                    rs.getInt("stelle"),
                    rs.getString("immagine"),
                    rs.getString("email"),
                    rs.getString("numeroTelefono")
            ));
        }
    }

    private void mapFromResultSet(HotelBean hotel, ResultSet rs) throws SQLException {
        while (rs.next()) {
            hotel = new HotelBean(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("nome"),
                    rs.getString("indirizzo"),
                    rs.getDouble("costoNotte"),
                    rs.getInt("stelle"),
                    rs.getString("immagine"),
                    rs.getString("email"),
                    rs.getString("numeroTelefono")
            );
        }
    }
}
