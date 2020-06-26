package model.carrello;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;
import model.cliente.ClienteBean;
import model.pacchetto.PacchettoBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;
import java.util.UUID;

public class CarrelloDAO implements ComponentCRUD<CarrelloBean, String> {
    @Override
    public CarrelloBean retrieveByKey(String key) throws SQLException {
        String sql ="SELECT * FROM Carrello WHERE id_carrello=?";
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
        String sql = "SELECT * FROM Carrello";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<CarrelloBean> carrelloBeans = new ArrayList<>();
        if(filter != null && !filter.equals("")){
            sql += " " + filter;
            if(order != null && !order.equals("")) {
                sql += " " + order;
            }
        }
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            mapFromResultSet(carrelloBeans, rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return carrelloBeans;
    }



    @Override
    public void doSave(CarrelloBean objectToSave) throws SQLException {
        String sql = "INSERT INTO Carrello (id_carrello, totale) VALUES (?, 0)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
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

    public void addPacchetto(CarrelloBean carrello, PacchettoBean pacchetto) throws SQLException{

        String sql = "insert into Carrello_Pacchetto (id_carrello, id_pacchetto) values (?, ?)"; //id_carrello, id_pacchetto
        String sql2 = "update Carrello set totale = totale + (select costo from Pacchetto where id = ?) where id_carrello = ?"; //id_carrello

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carrello.getId());
            preparedStatement.setString(2, pacchetto.getId().toString());
            preparedStatement.executeUpdate();
            connection.commit();

            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, pacchetto.getId().toString());
            preparedStatement.setString(2, carrello.getId());
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
        String sql2 = "update Carrello set totale = totale - (select costo from Pacchetto where id = ?) where id_carrello = ?"; //id_pacchetto id_carrello


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, pacchetto.getId().toString());
            preparedStatement.setString(2, carrello.getId().toString());
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

    public ArrayList<UUID> vediCarrello (CarrelloBean carrello) throws SQLException {
        ArrayList<UUID> pacchetti = new ArrayList<>();
        String sql = "SELECT id_pacchetto FROM Carrello_Pacchetto WHERE id_carrello = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, carrello.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                pacchetti.add((UUID.fromString(rs.getString("id_pacchetto"))));
            }
            return pacchetti;
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } finally {
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

    private void mapFromResultSet(List<CarrelloBean> carrelloBeans, ResultSet rs) throws SQLException {
        while (rs.next()) {
            carrelloBeans.add(new CarrelloBean(rs.getString("id_carrello"),
                    rs.getDouble("totale")));
        }
    }
}
