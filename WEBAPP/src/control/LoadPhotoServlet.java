package control;

import datasource.DriverManagerConnectionPool;
import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/user/LoadPhotoServlet")
public class LoadPhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        ClienteBean user = (ClienteBean) session.getAttribute("user");
        ClienteDAO clienteDAO = new ClienteDAO();
        String filename = "";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;


        try {
            connection = DriverManagerConnectionPool.getConnection();
            String sql = "select immagine from Cliente where email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            rs = statement.executeQuery();

            if (rs.next()) {
                filename = rs.getString("immagine");
            }
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if(statement != null){
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                if(connection != null){
                    try {
                        DriverManagerConnectionPool.releaseConnection(connection);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }

        System.out.println(filename);

        File file = new File(filename);
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bt = fileInputStream.readAllBytes();
        ServletOutputStream out = response.getOutputStream();
        out.write(bt);

    }

}
