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

public class LuogoDAO implements ComponentCRUD<LuogoBean, UUID> {

    @Override
    public LuogoBean retrieveByKey(UUID key) throws SQLException{
        String sql = "SELECT * FROM Luogo WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        LuogoBean luogo = new LuogoBean();

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
        return luogo;
    }

    @Override
    public List<LuogoBean> retrieveAll(String filter, String order) throws SQLException{
        String sql = "SELECT * FROM Luogo";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<LuogoBean> luoghi = new ArrayList<>();

        if(order != null && !order.equals("")){
            sql += " ORDER BY " + filter + " " + order;
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            mapFromResultSet(luoghi, rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return luoghi;
    }

    @Override
    public void doSave(LuogoBean objectToSave) throws SQLException {
        String sql= "INSERT INTO Luogo (id, nome, indirizzo, citta, descrizione, immagine)" +
                        "VALUES (UUID(), ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getNome());
            preparedStatement.setString(2, objectToSave.getIndirizzo());
            preparedStatement.setString(3, objectToSave.getCitta());
            preparedStatement.setString(4, objectToSave.getDescrizione());
            preparedStatement.setString(5, objectToSave.getImmagine());

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
    public void doUpdate(LuogoBean objectToUpdate) throws SQLException {
        String sql = "UPDATE Luogo SET id = ?" +
                "nome = ?" +
                "indirizzo = ?" +
                "citta = ?" +
                "descrizione = ?" +
                "immagine = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToUpdate.getId().toString());
            preparedStatement.setString(2, objectToUpdate.getNome());
            preparedStatement.setString(3, objectToUpdate.getIndirizzo());
            preparedStatement.setString(4, objectToUpdate.getCitta());
            preparedStatement.setString(5, objectToUpdate.getDescrizione());
            preparedStatement.setString(6, objectToUpdate.getImmagine());
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    @Override
    public void doDelete(LuogoBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM Luogo WHERE id = ?";
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

    private void mapFromResultSet(List<LuogoBean> luoghi, ResultSet rs) throws SQLException {
        while (rs.next()) {
            luoghi.add(new LuogoBean(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("nome"),
                    rs.getString("indirizzo"),
                    rs.getString("citta"),
                    rs.getString("descrizione"),
                    rs.getString("immagine")
            ));
        }
    }

    private LuogoBean mapFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()){
            return new LuogoBean(
                    UUID.fromString(rs.getString("id")),
                    rs.getString("nome"),
                    rs.getString("indirizzo"),
                    rs.getString("citta"),
                    rs.getString("descrizione"),
                    rs.getString("immagine")
            );
        }
        return new LuogoBean();
    }
}
