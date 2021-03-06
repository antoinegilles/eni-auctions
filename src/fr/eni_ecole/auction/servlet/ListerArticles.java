package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.bll.CategorieManager;
import fr.eni_ecole.auction.bll.UserManager;
import fr.eni_ecole.auction.dal.BusinessException;
import fr.eni_ecole.auction.dal.DALException;

/**
 * Lister les articles - l'affichage gerer dans la servlet
 *
 * @author moujdari2018
 *
 */
@WebServlet("/ListerArticles")

 public class ListerArticles extends HttpServlet {
   static final long serialVersionUID = 1L;
   
   	private CategorieManager categorieManager;
   	private ArticleManager articleManager;
    private UserManager userManager;
   


		

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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
		
		try{
			categorieManager = new CategorieManager();
			articleManager = new ArticleManager();
			userManager = new UserManager();

			List<ArticleVendu> ListerEncheres = new ArrayList<ArticleVendu>();
			Utilisateur unUtilisateur = null;
			Utilisateur unUtilisateurRecherche = null;

			// Lister les catégories dans la page d'accueil
			List<Categorie> listeCategories = categorieManager.listerLesCategories();
			request.setAttribute("liste", listeCategories);

			String categorie = (request.getParameter("categorie") == null) ? "%%"
					: "%" + request.getParameter("categorie") + "%";

			String article = (request.getParameter("article") == null) ? "%%"
					: "%" + request.getParameter("article") + "%";

			// Si le User est Connecté
			if (request.getSession().getAttribute("UserConnecte") != null) {

				unUtilisateur = (Utilisateur) request.getSession().getAttribute("UserConnecte");
				unUtilisateurRecherche = userManager.selectUser(unUtilisateur.getEmail(),
						unUtilisateur.getMotDePasse());

				String radioChoice = request.getParameter("radio-choice");
				//String radioVentesChoice = request.getParameter("radio-ventes-choice");

				if ("achats".equals(radioChoice)) {
					// Bouton Achats
					String open = request.getParameter("open");

					String ongoing = request.getParameter("ongoing");

					String won = request.getParameter("won");

					// Lister les enchères (Achats)
					ListerEncheres = articleManager.listeEncheres(categorie, article, open, ongoing, won, radioChoice, unUtilisateurRecherche);
				} 
				
				else if ("ventes".equals(radioChoice)) {
					// Bouton Ventes
					String sellsOngoing = request.getParameter("sellsOngoing");

					String sellsOpen = request.getParameter("sellsOpen");

					String sellsWon = request.getParameter("sellsWon");

					// Lister les ventes (Ventes)
					ListerEncheres = articleManager.listeArticleVendus(categorie, article, sellsOngoing, sellsOpen, sellsWon, radioChoice,
							unUtilisateurRecherche);
				} else {
					// Liste des articles enchères en cours
					ListerEncheres = articleManager.listerLesEncheresEnCours(categorie, article);
				}
			}
			else {
				// Liste des articles enchères en cours
				ListerEncheres = articleManager.listerLesEncheresEnCours(categorie, article);
			}

			// Placer des articles enchères dans le contexte de requete
			request.setAttribute("listeArticlesEncheresCours", ListerEncheres);

			request.getSession().setAttribute("UserConnecte", unUtilisateurRecherche);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

		} catch (DALException e) {
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request, response);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}