package fr.eni_ecole.auction.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.InscriptionDAOjdbclmpl;


@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Inscription() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ArticleVendu unArticleVendu =null;
		try{ 
			
			String pseudo = request.getParameter("pseudo");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String email = request.getParameter("email"); 
			String telephone =request.getParameter("telephone"); 
			String rue = request.getParameter("rue"); 
			String codePostal = request.getParameter("codePostal");
			String ville = request.getParameter("ville"); 
			String mdp = request.getParameter("mdp"); 
			String confirmation = request.getParameter("confirmation"); 



		
			if ((request.getParameter("pseudo").length()== 0)  
					|| (request.getParameter("pseudo").isEmpty())){
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "pseudo non renseign�. Veuillez le saisir ...");
				//redirection vers la page de login
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			} else if ((request.getParameter("mdp").length() == 0) 
					|| (request.getParameter("mdp").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "mot de passe non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			} else if ((request.getParameter("nom").length() == 0) 
					|| (request.getParameter("nom").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "nom non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}else if ((request.getParameter("prenom").length() == 0) 
					|| (request.getParameter("prenom").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "prenom non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}else if ((request.getParameter("email").length() == 0) 
					|| (request.getParameter("email").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "email non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}else if ((request.getParameter("telephone").length() == 0) 
					|| (request.getParameter("telephone").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "telephone non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}else if ((request.getParameter("rue").length() == 0) 
					|| (request.getParameter("rue").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "rue non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}else if ((request.getParameter("codePostal").length() == 0) 
					|| (request.getParameter("codePostal").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "Code Postal non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}else if ((request.getParameter("ville").length() == 0) 
					|| (request.getParameter("ville").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "Ville non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}else if ((request.getParameter("confirmation").length() == 0) 
					|| (request.getParameter("confirmation").isEmpty())) {
				// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
				request.setAttribute("erreur", "Confirmation du mot de passe non renseign�. Veuillez le saisir ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}
			else if (confirmation.equals(mdp)){
			// Ajoute un article
			InscriptionDAOjdbclmpl.inscrire(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, 0, true);
			//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index");
			//dispatcher.forward(request,response);
			}else {
				request.setAttribute("erreur", "les deux mot de passe ne sont pas renseign�s. Veuillez les re-saisir :O ...");
				this.getServletContext().getRequestDispatcher("/inscription").forward(request, response);
			}
			
		
		}catch (DALException e){
			request.setAttribute("erreur", e);
			System.out.println("erreur");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
