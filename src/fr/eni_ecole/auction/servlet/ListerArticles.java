package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

				String radioAchatsChoice = request.getParameter("radio-achats-choice");

				String radioSellsChoice = request.getParameter("radio-sells-choice");

				if (radioAchatsChoice != null) {
					// Bouton Achats
					String open = request.getParameter("open");

					String ongoing = request.getParameter("ongoing");

					String won = request.getParameter("won");

					// Lister les enchères (Achats)
					ListerEncheres = articleManager.listeEncheres(categorie, article, open, ongoing, won, unUtilisateurRecherche);
				} else if (radioSellsChoice != null) {
					// Bouton Ventes
					String sellsOngoing = request.getParameter("sells-ongoing");

					String sellsOpen = request.getParameter("sells-open");

					String sellsWon = request.getParameter("sells-won");

					// Lister les ventes (Ventes)
					ListerEncheres = articleManager.listeArticleVendus(categorie, article, sellsOngoing, sellsOpen, sellsWon,
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