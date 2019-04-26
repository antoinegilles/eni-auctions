package fr.eni_ecole.auction.servlet;

import fr.eni_ecole.auction.util.ImageLoader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/Uploads")
public class Uploads extends HttpServlet {

	private static final long serialVersionUID = 1L;
    

	 public Uploads() {
	     super();
	 }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File image = ImageLoader.getImg(request.getParameter("img"));
        if (image == null || !image.exists()) {
            response.sendRedirect(request.getContextPath() + "/theme/img/no-image.jpg");
        } else {
            try {
                response.setContentType(getFileType(request.getQueryString()));
                OutputStream out = response.getOutputStream();
                FileInputStream in = new FileInputStream(image);

                // Copy the contents of the file to the output stream
                byte[] buf = new byte[1024];
                int count = 0;
                while ((count = in.read(buf)) >= 0) {
                    out.write(buf, 0, count);
                }
                in.close();
                out.close();

            } catch (Exception e) {
                request.setAttribute("erreur", e);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
                dispatcher.forward(request, response);
            }
        }
    }

    private String getFileType(String queryString) throws Exception {
        String fileType = queryString.substring(queryString.lastIndexOf('.') + 1);
        String MimeType = "image/";

        switch (fileType) {
            case "png":
                MimeType += "png";
                break;
            case "jpg":
            case "jpeg":
                MimeType += "jpeg";
                break;
            default:
                throw new Exception("le fichier n'est pas supporté");
        }

        return MimeType;
    }
}
