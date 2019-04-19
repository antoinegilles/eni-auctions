package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.bll.UserManager;
import fr.eni_ecole.auction.dal.DALException;


@WebServlet("/UpdateProfilUser")
public class UpdateProfilUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateProfilUser() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	 UserManager userManager;
	   	 
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
		   	
			userManager.updateUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
