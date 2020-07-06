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
import java.time.Clock;

@WebServlet({"/user/LoadPhotoServlet", "/LoadPhotoServlet"})
public class LoadPhotoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String typePhoto = request.getParameter("tipo");

        switch (typePhoto){
            case "user":
                HttpSession session = request.getSession(false);
                ClienteBean user = (ClienteBean) session.getAttribute("user");
                String filename = user.getImmagine();


                File file = new File(filename);
                FileInputStream fileInputStream = new FileInputStream(file);
                byte[] bt = fileInputStream.readAllBytes();
                ServletOutputStream out = response.getOutputStream();
                out.write(bt);
                break;
        }




    }

}
