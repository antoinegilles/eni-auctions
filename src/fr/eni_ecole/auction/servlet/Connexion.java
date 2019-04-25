package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.security.Init;

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
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur user = null;
		String mail = null;
		String motdepasse = null;
		UserManager userManager;
		Cookie[] cookies = null;
		Cookie unCookie = null;
		boolean trouve = false;

		cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("yo")) {
					unCookie = cookies[i];
					trouve = true;
					System.out.println("j'ai trouvé le cookie ");

				}
			}
		}

		
		
		
		
		// Controle des informations en recup�ration :
		// si tous les champs ne sont pas renseign�s, revenir sur la page du formulaire
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
				// Si l'authentification est r�ussie...
				if (user != null) {
					// Invalider la session en cours dans le cas o� c'est un autre profil qui est d�j� connect�
					// request.getSession().invalidate();
					// Placer le bean dans le contexte de session
					request.getSession().setAttribute("UserConnecte", user);
					
					if (!trouve) {
						// genere un identifiant unique pour chaque poste client
						unCookie = new Cookie("yo",user.getPseudo());
						unCookie.setMaxAge(60*10); // temps en secondes de la durÃ©e de vie du cookie

						// Ajouter le cookie Ã  l'entÃªte de la reponse
						response.addCookie(unCookie);

					}
					if(trouve) {
						System.out.println("cookie trouve");
					}
					//redirection vers l'espace animateur
					response.sendRedirect(request.getContextPath()+"/");
				}
				// ...sinon
				else {
					// animateur n'est pas trouv� dans la BDD, on place l'erreur dans le contexte de requete 
					// pour pouvoir afficher le message d'erreur sur la page login
					request.setAttribute("erreur", "mail et/ou mot de passe incorrect(s). Veuillez corriger ...");	
					this.getServletContext().getRequestDispatcher("/connexion").forward(request, response);

				}
			} catch (DALException e) {
				// Placer l'objet repr�sentant l'exception dans le contexte de requete
				request.setAttribute("erreur", e);
				// Passer la main � la page de pr�sentation des erreurs
				//this.getServletContext().getRequestDispatcher("/erreurPage").forward(request, response);
			}
		}
	}

}
