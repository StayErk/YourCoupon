package model.carrello;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;
import model.cliente.ClienteBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CarrelloDAO implements ComponentCRUD<CarrelloBean, String> {
    @Override
    public CarrelloBean retrieveByKey(String key) throws SQLException {
        String sql ="SELECT * FROM Carrello WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        CarrelloBean carrello;

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,key);
            ResultSet rs = preparedStatement.executeQuery();

            carrello = mapFromResultSet(rs);
        }
        finally {
            try{
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return carrello;
    }

    @Override
    public List<CarrelloBean> retrieveAll(String filter, String order) throws SQLException {
        return null;
    }

    @Override
    public void doSave(CarrelloBean objectToSave) throws SQLException {

    }

    @Override
    public void doUpdate(CarrelloBean objectToUpdate) throws SQLException {
        String sql = "";
    }

    @Override
    public void doDelete(CarrelloBean objectToDelete) throws SQLException {

    }

    private CarrelloBean mapFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new CarrelloBean(
              rs.getString("id_carrello"),
              rs.getDouble("totale")
            );
        }
        return new CarrelloBean();
    }
}
