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
import fr.eni_ecole.auction.dal.UserDAOjdbclmpl;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.UserDAO;

@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public Connexion() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/ListerArticles").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = null;
		String mail = null;
		String motdepasse = null;
		UserManager userManager;
		
		// Controle des informations en recupération :
		// si tous les champs ne sont pas renseignés, revenir sur la page du formulaire
		if ((request.getParameter("identifiant").length()== 0)  
				|| (request.getParameter("identifiant").isEmpty())){
			// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
			request.setAttribute("erreur", "email non renseigné. Veuillez le saisir ...");
			//redirection vers la page de login
			this.getServletContext().getRequestDispatcher("/connexion").forward(request, response);
		} else if ((request.getParameter("motdepasse").length() == 0) 
				|| (request.getParameter("motdepasse").isEmpty())) {
			// place l'erreur dans le contexte de requete pour pouvoir afficher le message d'erreur sur la page login
			request.setAttribute("erreur", "mot de passe non renseigné. Veuillez le saisir ...");
			this.getServletContext().getRequestDispatcher("/connexion").forward(request, response);
		} else {
			//stockage de l'information saisie dans le formulaire
			mail = request.getParameter("identifiant");
			motdepasse = request.getParameter("motdepasse");
			try {
				userManager = new UserManager();
				// Valider l'authentification par rapport aux informations de la base
				user = userManager.selectUser(mail, motdepasse);
				// Si l'authentification est réussie...
				if (user != null) {
					// Invalider la session en cours dans le cas où c'est un autre profil qui est déjà connecté
					// request.getSession().invalidate();
					// Placer le bean dans le contexte de session
					request.getSession().setAttribute("UserConnecte", user);
					//redirection vers l'espace animateur
					response.sendRedirect(request.getContextPath()+"/");
				}
				// ...sinon
				else {
					// animateur n'est pas trouvé dans la BDD, on place l'erreur dans le contexte de requete 
					// pour pouvoir afficher le message d'erreur sur la page login
					request.setAttribute("erreur", "mail et/ou mot de passe incorrect(s). Veuillez corriger ...");
					// Retourner à l'écran d'authentification				
					this.getServletContext().getRequestDispatcher("/connexion").forward(request, response);
					
				}
			} catch (DALException e) {
				// Placer l'objet représentant l'exception dans le contexte de requete
				request.setAttribute("erreur", e);
				// Passer la main à la page de présentation des erreurs
				this.getServletContext().getRequestDispatcher("/erreurPage").forward(request, response);
			}
		}
	}

}
