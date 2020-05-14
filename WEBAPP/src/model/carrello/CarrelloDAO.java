package model.carrello;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;
import model.cliente.ClienteBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrelloDAO implements ComponentCRUD<CarrelloBean, String> {
    @Override
    public CarrelloBean retrieveByKey(String key) throws SQLException {
        return null;
    }

    @Override
    public List<CarrelloBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM Carrello";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<CarrelloBean> carrelli = new ArrayList<>();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            mapFromResultSet(carrelli, rs);
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
    public void doSave(CarrelloBean objectToSave) throws SQLException {
        String sql = "INSERT INTO Carrello (id_carrello, totale)" +
                "VALUES (?, 0)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getId());

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
    public void doUpdate(CarrelloBean objectToUpdate) throws SQLException {

    }

    @Override
    public void doDelete(CarrelloBean objectToDelete) throws SQLException {

    }

    private void mapFromResultSet(List<CarrelloBean> carrelli, ResultSet rs) throws SQLException {
        while(rs.next()) {
            carrelli.add(new CarrelloBean(rs.getString("id_carrello"),
                    rs.getDouble("totale")));
        }
    }


}
