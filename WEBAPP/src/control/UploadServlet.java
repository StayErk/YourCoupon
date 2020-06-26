package control;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/user/UploadServlet")

public class UploadServlet extends HttpServlet {
    static String Save_Dir = "img";

    private String extractFileName(Part part) {
        // content-disposition: form-data; name="file"; filename="file.txt"
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        System.out.println("Selvlet chiamata");

        String savePath = request.getServletContext().getRealPath("") + Save_Dir;
        System.out.println(savePath);

        File cartella = new File(savePath);
        if(!cartella.exists()) {
            cartella.mkdir();
            System.out.println("cartella creata");
        }

        if (request.getParts() != null && request.getParts().size() > 0) {
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && !fileName.equals("")) {
                    part.write(savePath + File.separator + fileName);
                    System.out.println(savePath + File.separator + fileName);
                }
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
