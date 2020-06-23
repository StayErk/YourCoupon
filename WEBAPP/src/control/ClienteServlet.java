package control;

import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
                    request.setAttribute("registrato", modelDAO.retrieveByKey(email));
                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/signup.jsp");
                    requestDispatcher.forward(request, response);

                } catch (SQLException e){
                    e.printStackTrace();
                }
                break;

            case "login":
                try {
                    String email = request.getParameter("email");
                    String pass = request.getParameter("password");
                    ClienteBean bean = null;
                    System.out.println(email + "\n" + pass);
                    HttpSession session = request.getSession(true);
                    byte[] password = null;
                    try {
                        MessageDigest digest = MessageDigest.getInstance("SHA-256");
                        byte[] hash = digest.digest(pass.getBytes("UTF-8"));

                        password = hash.clone();
                    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    bean = modelDAO.retrieveByKey(email);
                    if(bean != null){
                        System.out.println(bean.getPassword().equals(password) + "\n" + bean.getPassword() + "\t" + password);
                        String s = new String(password, StandardCharsets.UTF_8);
                        String s1 = new String(bean.getPassword(), StandardCharsets.UTF_8);
                        if (s1.equals(s)) {
                            session.setAttribute("user", bean);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
                            requestDispatcher.forward(request, response);
                        } else {
                            request.setAttribute("errore-login", true);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
                            requestDispatcher.forward(request, response);
                        }
                    }
                } catch (SQLException e) {

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
