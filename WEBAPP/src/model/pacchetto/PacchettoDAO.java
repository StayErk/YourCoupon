package model.pacchetto;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;
import model.restaurant.RestaurantBean;
import model.restaurant.RestaurantDAO;
import model.tour.TourBean;
import model.tour.TourDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PacchettoDAO implements ComponentCRUD<PacchettoBean, UUID> {

    @Override
    public PacchettoBean retrieveByKey(UUID key) throws SQLException {
        String sql = "SELECT * FROM Pacchetto WHERE id=?";
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
        String sql = "SELECT * FROM Pacchetto";
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
                "((SELECT costoNotte FROM StrutturaAlberghiera where id =  ?) * ? * ?), " + //id_struttura, durata, persone
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
                "((costo " +
                "/ durata " +
                "/ persone) * ? * ?),";// objectToUpdate.getPersone, objectToUpdate.getDurata

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



             preparedStatement.setInt(1, objectToUpdate.getPersone());
             preparedStatement.setInt(2, objectToUpdate.getDurata());
             preparedStatement.setInt(3, objectToUpdate.getDurata());
             preparedStatement.setInt(4, objectToUpdate.getPersone());
             preparedStatement.setString(5, objectToUpdate.getId().toString());

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
        String sql = "SELECT quantita FROM Pacchetto_Ristorante WHERE id_pacchetto = ? and id_ristorante = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bean.getId().toString());
            preparedStatement.setString(2, rBean.getId().toString());
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println(rs.next());
            try {
                String sql2 = "INSERT INTO Pacchetto_Ristorante (id_pacchetto, id_ristorante, quantita) VALUES (?, ?, 1)";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, bean.getId().toString());
                preparedStatement.setString(2, rBean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();



            }
            catch (SQLIntegrityConstraintViolationException e){
                //Coppia pacchetto ristorante esistente, incremento il campo quantita
                String sql2 = "UPDATE Pacchetto_Ristorante SET quantita = quantita + 1 WHERE id_pacchetto = ? and id_ristorante = ?";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, bean.getId().toString());
                preparedStatement.setString(2, rBean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();
            }

            String sql3 = "UPDATE Pacchetto SET " +
                    "costo = costo + ((SELECT costo FROM StruttureRistorative WHERE id = ?) * persone) " +
                    "WHERE id = ?";



            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1, rBean.getId().toString());
            preparedStatement.setString(2, bean.getId().toString());;
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

    public void removeRestaurant(PacchettoBean bean, RestaurantBean rBean) throws  SQLException{
        String sql = "SELECT quantita FROM Pacchetto_Ristorante WHERE id_pacchetto = ? and id_ristorante = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bean.getId().toString());
            preparedStatement.setString(2, rBean.getId().toString());
            ResultSet rs = preparedStatement.executeQuery();

            int quantita = rs.getInt("quantita");
            if(quantita > 1) {
                String sql2 = "UPDATE Pacchetto_Ristorante SET quantita = quantita - 1 WHERE id_pacchetto = ? and id_ristorante = ?";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, bean.getId().toString());
                preparedStatement.setString(2, rBean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();

                String sql3 = "UPDATE Pacchetto SET " +
                        "costo = (((SELECT costoNotte from StrutturaAlberghiera where id = ?) * persone * durata) + " +
                        "((SELECT costo FROM StruttureRistorative WHERE id = ?) * (SELECT quantita FROM Pacchetto_Ristorante WHERE id_pacchetto = ? and id_ristorante = ?) * persone)) " +
                        "WHERE id = ?";


                preparedStatement = connection.prepareStatement(sql3);
                preparedStatement.setString(1, bean.getId_struttura().toString());
                preparedStatement.setString(2, rBean.getId().toString());
                preparedStatement.setString(3, bean.getId().toString());
                preparedStatement.setString(4, rBean.getId().toString());
                preparedStatement.setString(5, bean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();
            }
            else if(quantita == 0) {
                String sql2 = "DELETE FROM Pacchetto_Ristorante WHERE id_pacchetto = ? and id_ristorante = ?";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, bean.getId().toString());
                preparedStatement.setString(2, rBean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();

                String sql3 = "UPDATE Pacchetto SET" +
                        " costo = costo - " +
                        "((SELECT costo FROM StruttureRistorative WHERE id = ?) " +
                        "* persone) WHERE  id = ?";
                preparedStatement = connection.prepareStatement(sql3);
                preparedStatement.setString(1, rBean.getId().toString());
                preparedStatement.setString(2, bean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();
            }

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
    public void addTour(PacchettoBean bean, TourBean tBean) throws  SQLException{
        String sql = "SELECT quantita FROM Pacchetto_Visita WHERE id_pacchetto = ? and id_visita = ?";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, bean.getId().toString());
            preparedStatement.setString(2, tBean.getId().toString());
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println(rs.next());
            try {
                String sql2 = "INSERT INTO Pacchetto_Visita (id_pacchetto, id_visita, quantita) VALUES (?, ?, 1)";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, bean.getId().toString());
                preparedStatement.setString(2, tBean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();

            }
            catch (SQLIntegrityConstraintViolationException e){
                //Coppia pacchetto ristorante esistente, incremento il campo quantita
                String sql2 = "UPDATE Pacchetto_Visita SET quantita = quantita + 1 WHERE id_pacchetto = ? and id_visita = ?";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setString(1, bean.getId().toString());
                preparedStatement.setString(2, tBean.getId().toString());
                preparedStatement.executeUpdate();
                connection.commit();
            }

            String sql3 = "UPDATE Pacchetto SET " +
                    "costo = costo + ((SELECT costo FROM VisitaGuidata WHERE id = ?) * persone) " +
                    "WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setString(1, tBean.getId().toString());
            preparedStatement.setString(2, bean.getId().toString());
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

    public void removeTour(PacchettoBean bean, TourBean tBean) throws  SQLException{
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Pacchetto_Visita WHERE id_pacchetto = ? AND id_visita = ?"; //bean.getID() tBean.getId()

        String updatePrice = "UPDATE Pacchetto SET" +
                " costo = costo - " +
                "((SELECT costo FROM VisitaGuidata WHERE id = ?) " + //tBean.getId()
                "* persone) WHERE  id = ?"; //bean.getID()

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, bean.getId().toString());
            preparedStatement.setString(2, tBean.getId().toString());
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(updatePrice);
            preparedStatement.setString(1, tBean.getId().toString());
            preparedStatement.setString(2, bean.getId().toString());
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
     * Questo metodo recupera tutti i possibli ristoranti legati ad un singolo pacchetto
     * @param key chiave del pacchetto
     * @return una lista vuota se il pacchetto non ha ristoranti legati, una lista piena altrimenti
     * @throws SQLException
     */
    public ArrayList<RestaurantBean> retrieveExtraRistoranti(UUID key) throws SQLException{
        RestaurantDAO dao = new RestaurantDAO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<RestaurantBean> toReturn = new ArrayList<>();
        ArrayList<UUID> ids = new ArrayList<>();
        String sql = "SELECT id_ristorante FROM Pacchetto_Ristorante WHERE id_pacchetto = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key.toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ids.add(UUID.fromString(resultSet.getString("id_ristorante")));
            }
            for(UUID id : ids) {
                toReturn.add(dao.retrieveByKey(id));
            }
        } finally {
            try {
                if(preparedStatement != null ) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return  toReturn;
    }

    public ArrayList<TourBean> retrieveExtraTour(UUID key) throws SQLException{
        ArrayList<TourBean> toReturn = new ArrayList<>();
        TourDAO dao = new TourDAO();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ArrayList<UUID> ids = new ArrayList<>();

        String sql = "SELECT id_visita FROM Pacchetto_Visita WHERE id_pacchetto = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, key.toString());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Id in result set: " + UUID.fromString(resultSet.getString("id_visita")));
                ids.add(UUID.fromString(resultSet.getString("id_visita")));
            }
            for(UUID id : ids) {
                toReturn.add(dao.retrieveByKey(id));
            }
        } finally {
            try {
                if(preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return toReturn;
    }

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
