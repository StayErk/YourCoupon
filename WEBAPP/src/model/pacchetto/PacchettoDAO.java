package model.pacchetto;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;
import model.restaurant.RestaurantBean;
import model.tour.TourBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacchettoDAO implements ComponentCRUD<PacchettoBean, UUID> {

    @Override
    public PacchettoBean retrieveByKey(UUID key) throws SQLException {
        String sql = "SELECT * FROM PAcchetto WHERE id=?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PacchettoBean bean;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key.toString());
            ResultSet rs = preparedStatement.executeQuery();
            bean = mapFromResultSet(rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return bean;
    }

    @Override
    public List<PacchettoBean> retrieveAll(String filter, String order) throws SQLException {
        String sql = "SELECT * FROM pacchetto";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<PacchettoBean> beans = new ArrayList<>();

        if(filter != null && !filter.equals("")){
            sql += " ORDER BY " + filter;
            if (order != null && !order.equals("")) sql += " " + order;
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            mapFromResultSet(beans, rs);
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return beans;
    }

    @Override
    public void doSave(PacchettoBean objectToSave) throws SQLException {
        String sql = "INSERT INTO Pacchetto (id, costo, id_cliente, id_struttura, durata, predefinito, persone) VALUES " +
                "(?, " + //id pacchetto
                "((SELECT costo_notte FROM StrutturaAlberghiera where id =  ?) * ? * ?), " + //id_struttura, durata, persone
                " ?, " + //id_cliente
                " ?,  " + //id_struttura
                " ?, " + //durata
                " ?, " + //predefinito
                " ? " + //persone
                ") ";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, objectToSave.getId().toString());
            preparedStatement.setString(2, objectToSave.getId_struttura().toString());
            preparedStatement.setInt(3, objectToSave.getDurata());
            preparedStatement.setInt(4, objectToSave.getPersone());
            preparedStatement.setString(5, objectToSave.getId_cliente());
            preparedStatement.setString(6, objectToSave.getId_struttura().toString());
            preparedStatement.setInt(7, objectToSave.getDurata());
            preparedStatement.setBoolean(8, objectToSave.isPredefinito());
            preparedStatement.setInt(9, objectToSave.getPersone());

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
    public void doUpdate(PacchettoBean objectToUpdate) throws SQLException {
        String priceUpdate = " costo = " +
                "((SELECT costo FROM Pacchetto where id = ?) " + //id_pacchetto
                "/ (SELECT durata FROM Pacchetto where id = ?) " + //id_pacchetto
                "/ (SELECT persone FROM Pacchetto where id = ?)) * ? * ?";//id_pacchetto, objectToUpdate.getPersone, objectToUpdate.getDurata

        String sql = "UPDATE Pacchetto " +
                " SET " +
                priceUpdate +
                " durata = ?, " + //objectToUpdate.getDurata
                " persone = ? " + //objectToUpdate.getPersone
                " WHERE id = ? "; //id_pacchetto

        Connection connection = null;
        PreparedStatement preparedStatement = null;
         try {
             connection = DriverManagerConnectionPool.getConnection();
             preparedStatement = connection.prepareStatement(sql);
             preparedStatement.setString(1, objectToUpdate.getId().toString());
             preparedStatement.setString(2, objectToUpdate.getId().toString());
             preparedStatement.setString(3, objectToUpdate.getId().toString());
             preparedStatement.setInt(4, objectToUpdate.getPersone());
             preparedStatement.setInt(5, objectToUpdate.getDurata());
             preparedStatement.setInt(6, objectToUpdate.getDurata());
             preparedStatement.setInt(7, objectToUpdate.getPersone());
             preparedStatement.setString(8, objectToUpdate.getId().toString());

             preparedStatement.executeUpdate();
             connection.commit();
         } finally {
             try{
                 if(preparedStatement != null) preparedStatement.close();
             } finally {
                 DriverManagerConnectionPool.releaseConnection(connection);
             }
         }
    }

    @Override
    public void doDelete(PacchettoBean objectToDelete) throws SQLException {
        String sql = "DELETE FROM Pacchetto WHERE id = ?";
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
                if (preparedStatement != null) preparedStatement.close();
            }finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    /**
     * Aggiunge un ristorante ad un pacchetto aggiornandone il costo
     * @param bean Pacchetto a cui aggiungere il ristorante
     * @param rBean Ristorante da aggiungere al paccchetto
     */
    public void addRestaurant(PacchettoBean bean, RestaurantBean rBean) throws  SQLException{
        String sql = "INSERT INTO Pacchetto_Ristorante (id_pacchetto, id_ristorante) " +
                "VALUES (?, ?)";

        String updatePrice = "UPDATE Pacchetto SET " +
                "costo = ((SELECT costo from pacchetto where id = ?) + " + //id pacchetto
                "((SELECT costo from StrutturaRistorativa WHERE id = ?)" + //rBean.getID
                "* (SELECT durata FROM pacchetto where id=?) * (SELECT persone FROM pacchetto WHERE id=?)))" + //id pacchetto id_pacchetto
                " WHERE id = ?"; //id pacchetto

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bean.getId().toString());
            preparedStatement.setString(2, rBean.getId().toString());

            preparedStatement.executeUpdate();
            connection.commit();

            preparedStatement = connection.prepareStatement(updatePrice);
            preparedStatement.setString(1, bean.getId().toString());
            preparedStatement.setString(2, rBean.getId().toString());
            preparedStatement.setString(3, bean.getId().toString());
            preparedStatement.setString(4, bean.getId().toString());
            preparedStatement.setString(5, bean.getId().toString());

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
     * Aggiunge un Tour ad un pacchetto aggiornandone il costo
     * @param bean Pacchetto a cui aggiungere il tour
     * @param tBean  Tour da aggiungere al paccchetto
     * @throws SQLException
     */
    public void addTour(PacchettoBean bean, TourBean tBean) throws  SQLException{}

    private PacchettoBean mapFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new PacchettoBean(
                   UUID.fromString(rs.getString("id")),
                   rs.getDouble("costo"),
                   rs.getString("id_cliente"),
                   UUID.fromString(rs.getString("id_struttura")),
                   rs.getInt("durata"),
                   rs.getBoolean("predefinito"),
                   rs.getInt("persone")
            );
        }
        return new PacchettoBean();
    }

    private void mapFromResultSet(List<PacchettoBean> beans, ResultSet rs) throws SQLException {
        while (rs.next()) {
            beans.add(new PacchettoBean(
                    UUID.fromString(rs.getString("id")),
                    rs.getDouble("costo"),
                    rs.getString("id_cliente"),
                    UUID.fromString(rs.getString("id_struttura")),
                    rs.getInt("durata"),
                    rs.getBoolean("predefinito"),
                    rs.getInt("persone")
            ));
        }
    }
}
