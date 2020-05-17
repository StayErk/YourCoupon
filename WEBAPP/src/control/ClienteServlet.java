package control;

import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {

    ClienteDAO modelDAO = new ClienteDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        switch (action){
            case "signup":
                try {
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    ClienteBean bean = new ClienteBean(nome, cognome, 0, email, password, false, "");
                    modelDAO.doSave(bean);
                    System.out.println("saved " + bean);

                } catch (SQLException e){
                    e.printStackTrace();
                }
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("signup") || action.equals("login")) {
            doPost(request, response);
        }

    }
}
