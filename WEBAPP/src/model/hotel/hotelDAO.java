package model.hotel;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class hotelDAO implements ComponentCRUD<HotelBean, UUID> {

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

    @Override
    public List<HotelBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM StrutturaAlberghiera";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<HotelBean> hotels = new ArrayList<>();

        if(order != null && !order.equals("")){
            sql += " ORDER BY " + filter + " " + order;
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



    @Override
    public void doSave(HotelBean objectToSave) throws SQLException {

    }

    @Override
    public void doUpdate(HotelBean objectToUpdate) throws SQLException {

    }

    @Override
    public void doDelete(HotelBean objectToDelete) throws SQLException {

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
