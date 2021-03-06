package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.bll.UserManager;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.UserDAOjdbclmpl;


@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Profil() {
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
				
					
				
					if (cookies[i].getName().equals("email")) {
						unCookie = cookies[i];
						trouve = true;
	
					}
					else if(cookies[i].getName().equals("password")) {
						unCookie2 = cookies[i];
						trouve = true;
	
					}
				}
		}

		if(trouve) {
			try {
				userManager = new UserManager();
				Utilisateur utilisateur = userManager.selectUser(unCookie.getValue(), unCookie2.getValue());
				request.getSession().setAttribute("UserConnecte", utilisateur);

			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		if (request.getSession().getAttribute("UserConnecte")==null) {
			request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/connexion");
		}else {
			
		
	
	request.getSession().getAttribute("UserConnecte");
	request.getRequestDispatcher("/accesUser").forward(request, response);
		}
	}

}
