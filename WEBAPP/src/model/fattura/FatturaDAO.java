package model.fattura;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FatturaDAO implements ComponentCRUD<FatturaBean, String> {
    @Override
    public FatturaBean retrieveByKey(String key) throws SQLException {
       String sql = "SELECT * FROM Fattura WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        FatturaBean fattura;


        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1 , key);
            ResultSet rs = preparedStatement.executeQuery();

            fattura = mapFromResultSet(rs);
        } finally {
            try{
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return fattura;
    }

    @Override
    public List<FatturaBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM Fattura";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<FatturaBean> fatture = new ArrayList<>();

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            mapFromResultSet(fatture, rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return fatture;
    }

    @Override
    public void doSave(FatturaBean objectToSave) throws SQLException {
        String sql = "INSERT INTO Fattura (id, id_carrello, totale) VALUES (?, ?, ?)"; //id, id_carrello, totale
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getId());
            preparedStatement.setString(2, objectToSave.getId_carrello());
            preparedStatement.setDouble(3, objectToSave.getTotale());
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
    public void doUpdate(FatturaBean objectToUpdate) throws SQLException {

    }

    @Override
    public void doDelete(FatturaBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM Fattura WHERE id = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToDelete.getId());

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

    private FatturaBean mapFromResultSet(ResultSet rs) throws SQLException{
        if(rs.next()){
            return new FatturaBean(
              rs.getString("id"),
              rs.getString("id_carrello"),
              rs.getDouble("totale")
            );
        }
        return new FatturaBean();
    }

    private void mapFromResultSet(List<FatturaBean> fatture, ResultSet rs) throws SQLException{
        while(rs.next()){
            fatture.add(new FatturaBean(rs.getString("id"),
                    rs.getString("id_carrello"),
                    rs.getDouble("totale")));
        }
    }
}

