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

@WebServlet("/user/UploadServlet")
public class UploadServlet extends HttpServlet {
    static String Save_Dir = "img";


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

        Part part = request.getPart("");
        String fileName = extractFileName(part);
        if(fileName != null && !fileName.equals("")) {
            part.write(savePath + File.separator + fileName);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
