package model.pacchetto;

import datasource.DriverManagerConnectionPool;
import model.ComponentCRUD;

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
        //String sql = "INSERT INTO "
    }

    @Override
    public void doUpdate(PacchettoBean objectToUpdate) throws SQLException {

    }

    @Override
    public void doDelete(PacchettoBean objectToDelete) throws SQLException {

    }

    private PacchettoBean mapFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return new PacchettoBean(
                   UUID.fromString(rs.getString("id")),
                   rs.getDouble("costo"),
                   rs.getString("id_cliente"),
                   UUID.fromString(rs.getString("id_struttura")),
                   rs.getInt("durata"),
                   rs.getBoolean("predefinito")

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
                    rs.getBoolean("predefinito")
            ));
        }
    }
}
