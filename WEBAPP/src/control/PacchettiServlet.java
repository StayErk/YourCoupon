package control;

import model.pacchetto.PacchettoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/PacchettiServlet")
public class PacchettiServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PacchettoDAO modelDAO = new PacchettoDAO();
        String action = request.getParameter("action");
        System.out.println(action);
        switch (action){
            case "retrieve":
                try {
                    request.setAttribute("pacchetti", modelDAO.retrieveAll("", ""));
                    System.out.println(modelDAO.retrieveAll("", ""));
                } catch (SQLException e) {
                    request.setAttribute("errore", e.toString());
                }
                break;
        }

        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);

    }
}
