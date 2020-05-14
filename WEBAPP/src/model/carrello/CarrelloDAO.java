package model.carrello;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;
import model.cliente.ClienteBean;
import model.pacchetto.PacchettoBean;

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

    }

    @Override
    public void doDelete(CarrelloBean objectToDelete) throws SQLException {

    }

    public void addPacchetto(CarrelloBean carrello, PacchettoBean pacchetto) throws SQLException{

        String sql = "insert into Carrello_Pacchetto (id_carrello, id_pacchetto) values (?, ?)"; //id_carrello, id_pacchetto
        String sql2 = "update Carrello set totale = totale + (select costo from Pacchetto where id = (select id_pacchetto from Carrello_Pacchetto AS cp where cp.id_carrello = id_carrello)) where id_carrello = ?"; //id_carrello

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carrello.getId());
            preparedStatement.setString(2, pacchetto.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, carrello.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            }
            finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    public void removePacchetto(CarrelloBean carrello, PacchettoBean pacchetto) throws SQLException{

        String sql = "delete from Carrello_Pacchetto where id_carrello = ? and id_pacchetto = ?"; //id_carrello, id_pacchetto
        String sql2 = "update Carrello set totale = totale - (select costo from Pacchetto where id = (select id_pacchetto from Carrello_Pacchetto AS cp where cp.id_carrello = id_carrello)) where id_carrello = ?"; //id_carrello

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, carrello.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carrello.getId());
            preparedStatement.setString(2, pacchetto.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();

        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            }
            finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
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
