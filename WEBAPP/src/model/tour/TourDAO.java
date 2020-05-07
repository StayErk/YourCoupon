package model.tour;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TourDAO implements ComponentCRUD<TourBean, UUID> {

    @Override
    public TourBean retrieveByKey(UUID key) throws SQLException {
        String sql = "SELECT * FROM VisitaGuidata WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        TourBean tour = new TourBean();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key.toString());
            ResultSet rs = preparedStatement.executeQuery();

           // mapFromResultSet(tour, rs);
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
    public List<TourBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM VisitaGuidata";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<TourBean> tours = new ArrayList<>();

        if(order != null && !order.equals("")){
            sql += " ORDER BY " + filter + " " + order;
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

           // mapFromResultSet(tours, rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return tours;
    }

    @Override
    public void doSave(TourBean objectToSave) throws SQLException{
        String sql = "INSERT INTO VisitaGuidata (id, id_luogo, costo, partecipanti)" +
                        "VALUES (UUID(), (SELECT id FROM Luogo WHERE id=?), ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getId().toString());
            preparedStatement.setDouble(2,objectToSave.getCosto());
            preparedStatement.setInt(3, objectToSave.getPartecipanti());

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

    @Override
    public void doUpdate(TourBean objectToUpdate) throws SQLException {
        String sql = "UPDATE VisitaGuidata SET id = ? " +
                "id_luogo = ? " +
                "costo = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
    }

    @Override
    public void doDelete(TourBean objectToDelete) throws SQLException {

    }


}
