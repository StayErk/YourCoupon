package control;

import model.pacchetto.PacchettoDAO;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "PacchettiServlet")
public class PacchettiServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PacchettoDAO modelDAO = new PacchettoDAO();
        String action = request.getParameter("action");
        switch (action){
            case "retrieve":
                try {
                    request.setAttribute("pacchetti", modelDAO.retrieveAll("costo", "ASC"));
                } catch (SQLException e) {
                    response.setStatus(500);
                }
                break;
        }

    }
}
