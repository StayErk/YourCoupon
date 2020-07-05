package control;

import model.BackendValidation;
import model.Popolamento;
import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import static model.BackendValidation.*;

@WebServlet({"/ClienteServlet", "/user/ClienteServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
         // 10MB maximum size allowed for uploaded files
        maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class ClienteServlet extends HttpServlet {

    static String SAVE_DIR = "img";
    ClienteDAO modelDAO = new ClienteDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action != null){
            switch (action) {
                case "signup":
                    try {
                        String nome = request.getParameter("nome");
                        String cognome = request.getParameter("cognome");
                        String email = request.getParameter("email");
                        String password = request.getParameter("password");
                        if(checkTesto(nome, 15) && checkTesto(cognome, 15) && checkEmail(email) && checkPwd(password)) {
                            String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR + File.separator + "user";
                            ClienteBean bean = new ClienteBean(nome, cognome, 0, email, password, false, savePath);
                            modelDAO.doSave(bean);
                            request.setAttribute("registrato", modelDAO.retrieveByKey(email));
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/signup.jsp");
                            requestDispatcher.forward(request, response);
                        } else {
                            request.setAttribute("errore", true);
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/signup.jsp");
                            requestDispatcher.forward(request, response);
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                        request.setAttribute("errore", true);
                        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/signup.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    break;

                case "login":
                    try {
                        String email = request.getParameter("email");
                        String pass = request.getParameter("password");
                        if (email == null || !checkPwd(pass)) {
                            badInput(request, response, "non-esistente");
                        }
                        ClienteBean bean = null;
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
                        if (bean != null) {
                            if (bean.getPassword() != null || bean.getEmail() != null) {
                                String s = new String(password, StandardCharsets.UTF_8);
                                String s1 = new String(bean.getPassword(), StandardCharsets.UTF_8);
                                if (s1.equals(s)) {
                                    session.setAttribute("user", bean);
                                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
                                    requestDispatcher.forward(request, response);
                                } else {
                                    badInput(request, response, "errore-login");
                                }
                            } else {
                                badInput(request, response, "non-esistente");
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case "uploadPhoto":
                    String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;

                    File fileSaveDir = new File(savePath);
                    if (!fileSaveDir.exists()) {
                        fileSaveDir.mkdir();
                    }

                    if (request.getParts() != null && request.getParts().size() > 0) {
                        for (Part part : request.getParts()) {
                            if (part.getSize() > 1024 * 1024 * 10) {
                                response.setStatus(500);
                                request.setAttribute("fotoerr", true);
                                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/profile.jsp");
                                requestDispatcher.forward(request, response);
                            } else {
                                String fileName = extractFileName(part);
                                if (fileName != null && !fileName.equals("")) {
                                    String path = savePath + File.separator + fileName;
                                    part.write(path);

                                    ClienteDAO clienteDAO = new ClienteDAO();
                                    ClienteBean clienteBean = new ClienteBean();
                                    HttpSession session = request.getSession(false);
                                    clienteBean = (ClienteBean) session.getAttribute("user");
                                    clienteBean.setImmagine(path);
                                    try {
                                        clienteDAO.doUpdate(clienteBean);
                                    } catch (SQLException throwables) {
                                        response.setStatus(500);
                                        request.setAttribute("fotoerr", true);
                                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/profile.jsp");
                                        requestDispatcher.forward(request, response);
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        }
                    }

                    response.sendRedirect(request.getContextPath() + "/user/profile.jsp");
                    break;
                case "changepwd":
                    ClienteDAO clienteDAO = new ClienteDAO();
                    String newPwd = request.getParameter("password");
                    HttpSession session = request.getSession();
                    ClienteBean utente = (ClienteBean) session.getAttribute("user");
                    if(utente != null && checkPwd(newPwd)) {
                        utente.setPassword(newPwd);
                        try {
                            clienteDAO.doUpdate(utente);
                            request.setAttribute("cambiopwd", true);
                            session.invalidate();
                            RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
                            requestDispatcher.forward(request, response);
                        } catch (SQLException throwables) {
                            request.setAttribute("errore", true);
                            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/profile.jsp");
                            requestDispatcher.forward(request, response);
                            response.setStatus(500);
                            throwables.printStackTrace();
                        }
                    } else{
                        response.setStatus(500);
                        request.setAttribute("errore", true);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/profile.jsp");
                        requestDispatcher.forward(request, response);

                    }
                    break;
            }
        }
    }

    private void badInput(HttpServletRequest request, HttpServletResponse response, String s2) throws ServletException, IOException {
        request.setAttribute(s2, true);
        RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/login.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action.equals("signup") || action.equals("login") || action.equals("changepwd")) {
            doPost(request, response);
        } else if (action.equals("logout")){
                HttpSession session = request.getSession();
                ClienteBean utente = (ClienteBean) session.getAttribute("user");

                if(utente != null){

                    session.invalidate();
                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    session.invalidate();
                    RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(request, response);
                }
        }

    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
