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

            mapFromResultSet(rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return tour;
    }

    @Override
    public List<TourBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM VisitaGuidata";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<TourBean> tours = new ArrayList<>();

        if(filter != null && !filter.equals("")){
            sql += " ORDER BY " + filter;
            if(order != null && !order.equals("")){
                sql+= " " + order;
            }
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            mapFromResultSet(tours, rs);
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
                        "VALUES (?, (SELECT id FROM Luogo WHERE id=?), ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getId().toString());
            preparedStatement.setString(2, objectToSave.getId_luogo().toString());
            preparedStatement.setDouble(3,objectToSave.getCosto());
            preparedStatement.setInt(4, objectToSave.getPartecipanti());

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
        String sql = "UPDATE VisitaGuidata SET costo = ?, " +
                "partecipanti = ? " + "WHERE id = ?;";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,objectToUpdate.getCosto());
            preparedStatement.setInt(2,objectToUpdate.getPartecipanti());
            preparedStatement.setString(3, objectToUpdate.getId().toString());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
            connection.commit();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void doDelete(TourBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM VisitaGuidata WHERE id = ?";
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

    private void mapFromResultSet(List<TourBean> tours, ResultSet rs) throws SQLException{
        while(rs.next()){
            tours.add(new TourBean(
                    UUID.fromString(rs.getString("id")),
                    UUID.fromString(rs.getString("id_luogo")),
                    rs.getDouble("costo"),
                    rs.getInt("partecipanti")
            ));
        }
    }

    private TourBean mapFromResultSet(ResultSet rs) throws SQLException{
        if(rs.next()) {
            return new TourBean(
                    UUID.fromString(rs.getString("id")),
                    UUID.fromString(rs.getString("id_luogo")),
                    rs.getDouble("costo"),
                    rs.getInt("partecipanti")
            );
        }
        return new TourBean();
    }

}
