package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.bll.UserManager;
import fr.eni_ecole.auction.dal.DALException;


@WebServlet("/UpdateProfilUser")
public class UpdateProfilUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateProfilUser() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

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
		Utilisateur utilisateurSansModification = (Utilisateur) request.getSession().getAttribute("UserConnecte");
		String pseudoOriginal = utilisateurSansModification.getPseudo();
		
		String prenom =request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String ville = request.getParameter("ville");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String pseudo = request.getParameter("pseudo");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String mdp = request.getParameter("mdp");
		
		
	   	 try {
		   	userManager = new UserManager();
		   	user = userManager.selectUser(email, mdp);
			user.setPseudo(pseudo);
			user.setNom(nom);
			user.setPrenom(prenom);
			user.setEmail(email);
			user.setTelephone(telephone);
			user.setRue(rue);
			user.setCodePostal(codePostal);
			user.setVille(ville);
			user.setMotDePasse(mdp);
			
			if(pseudo != pseudoOriginal) {
				
				if(userManager.selectPseudo(pseudo) == null) {
					System.err.println("pseudo existe pas");
					userManager.updateUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
					request.getSession().setAttribute("UserConnecte", user);
					response.sendRedirect(request.getContextPath()+"/");
				}else {
					System.out.println("pseudo existe deja");
					request.setAttribute("erreur", "Le pseudo existe deja xD ! Change le :O");
					this.getServletContext().getRequestDispatcher("/updateProfil").forward(request, response);

					}
				}else {
				userManager.updateUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
				request.getSession().setAttribute("UserConnecte", user);
			}
	
		
		
			
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}

		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
