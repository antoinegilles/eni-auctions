package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni_ecole.auction.bll.UserManager;


@WebServlet("/Deconnexion")
public class Deconnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public Deconnexion() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie[] cookies = null;
		Cookie unCookie = null;
		Cookie unCookie2 = null;
		boolean trouve = false;
		UserManager userManager;


		cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				
				unCookie = new Cookie("email","");
				unCookie2 = new Cookie("password","");
				unCookie.setMaxAge(0);
				unCookie2.setMaxAge(0);// temps en secondes de la durÃ©e de vie du cookie

				// Ajouter le cookie Ã  l'entÃªte de la reponse
				response.addCookie(unCookie);
				response.addCookie(unCookie2);
					
				}
		}
		
		HttpSession session = request.getSession(false);
		if (session!=null)
			session.invalidate();
		response.sendRedirect(request.getContextPath()+"/");
	}

}
