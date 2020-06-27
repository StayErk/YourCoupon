package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

    private static List<Connection> freeDBConnections = new ArrayList<>();


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("Driver del databse non trovati:" + e.getMessage());
        }
    }

    public DriverManagerConnectionPool() {
        freeDBConnections = new LinkedList<Connection>();
    }

    private synchronized static Connection createDBConnection() throws SQLException {
        Connection newConnetcion = null;
        String ip = "localhost";
        String port = "3306";
        String db = "yourcoupon?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = loginDB.username;
        String password = loginDB.password;


        newConnetcion = DriverManager.getConnection(db, username, password);
        newConnetcion.setAutoCommit(false);
        return newConnetcion;
    }


    public synchronized static Connection getConnection() throws SQLException {
        Connection connection = null;
        if (!freeDBConnections.isEmpty()) {
            connection = (Connection) freeDBConnections.get(0);
            freeDBConnections.remove(0);
            try {
                if (connection.isClosed())
                    connection = getConnection();
            } catch (SQLException e) {
                connection.close();
                connection = getConnection();
            }
        } else {
            connection = createDBConnection();
        }
        return connection;
    }
    public synchronized static void releaseConnection(Connection connection) throws SQLException {
        if(connection != null) freeDBConnections.add(connection);
    }


}
