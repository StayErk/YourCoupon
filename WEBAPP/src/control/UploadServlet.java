package control;

import model.cliente.ClienteBean;
import model.cliente.ClienteDAO;

import javax.jms.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be
        maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
        maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class UploadServlet extends HttpServlet {
    static String SAVE_DIR = "img";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String savePath = request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        if (request.getParts() != null && request.getParts().size() > 0) {
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.equals("")) {
                    String path = savePath + File.separator + fileName;
                    part.write(path);
                    System.out.println(path);

                    ClienteDAO clienteDAO = new ClienteDAO();
                    ClienteBean clienteBean = new ClienteBean();
                    HttpSession session = request.getSession(false);
                    clienteBean = (ClienteBean) session.getAttribute("user");
                    clienteBean.setImmagine(path);
                    try {
                        clienteDAO.doUpdate(clienteBean);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } else {
                    request.setAttribute("error", "Errore: Bisogna selezionare almeno un file");
                }
            }
        }


        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/profile.jsp");
        dispatcher.forward(request, response);

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

