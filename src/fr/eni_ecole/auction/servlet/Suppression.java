package fr.eni_ecole.auction.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.bll.UserManager;
import fr.eni_ecole.auction.dal.DALException;

@WebServlet("/Suppression")
public class Suppression extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public Suppression() {
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
		UserManager userManager2;


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
				userManager2 = new UserManager();
				Utilisateur utilisateur = userManager2.selectUser(unCookie.getValue(), unCookie2.getValue());
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
		
			UserManager userManager;
			Utilisateur user = null;
			Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte");

			String prenom =a.getPrenom();
			String nom = a.getNom();
			String ville =a.getVille() ;
			String rue = a.getRue() ;
			String codePostal = a.getCodePostal();
			String pseudo =  a.getPseudo();
			String telephone = a.getTelephone();
			String email =a.getEmail() ;
			String mdp = a.getMotDePasse() ;
			int credit = a.getCredit();
		   	 try {
		   		 System.out.println(prenom +nom+telephone);
			   	userManager = new UserManager();
				userManager.deleteUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, credit);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/deconnexion");
				dispatcher.forward(request,response);
				
			} catch (DALException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	}

}
