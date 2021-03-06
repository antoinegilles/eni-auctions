package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.bll.UserManager;
import fr.eni_ecole.auction.dal.BusinessException;
import fr.eni_ecole.auction.dal.DALException;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Encherir")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ArticleManager articleManager;
	private UserManager userManager;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("UserConnecte")==null) {
			request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/connexion");
		}else {
		
			try {
			articleManager = new ArticleManager();
			ArticleVendu detailArticleEncheri = null;

			userManager = new UserManager();

			Utilisateur unUtilisateur = null;
			Utilisateur unUtilisateurRecherche = null;

			unUtilisateur = (Utilisateur) request.getSession().getAttribute("UserConnecte");

			unUtilisateurRecherche = userManager.selectUser(unUtilisateur.getEmail(), unUtilisateur.getMotDePasse());

			int proposition = Integer.parseInt(request.getParameter("proposition"));
			int numeroArticle = Integer.parseInt(request.getParameter("numeroArticle"));

			// Liste de l'articles de l'ench�res choisi
			detailArticleEncheri = articleManager.detailVente(numeroArticle);

			// Si le prix (en points) est supèrieur au tarif actuel
			// TODO ET que la date de début d'enchères est inférieure à la date du jour
			// ET que la date de fin d'enchères est toujours en cours
			if (proposition >= detailArticleEncheri.getMisAPrix()) {

				// Si le compte de points ne devient pas négatif
				if (unUtilisateurRecherche.getCredit() >= proposition) {

					// Si l'enchère est possible, mon crédit de points est débité du montant
					// proposé. [CREDIT]
					int creditRestant = unUtilisateurRecherche.getCredit() - proposition;
					unUtilisateurRecherche.setCredit(creditRestant);
					
					userManager.updateUserCredit(unUtilisateurRecherche);
					
					// TODO Débiter la somme au vendeur de l'enchère [DEBIT]
					
					int debit = detailArticleEncheri.getUtilisateur().getCredit() + proposition;
					
					detailArticleEncheri.getUtilisateur()
							.setCredit(debit);
					
					
					userManager.updateUserDebit(detailArticleEncheri);

					
					// TODO Le meilleur enchèrisseur précèdent si il existe est re-crédité de son
					// offre.

					
					articleManager.ajouterUneEnchere(unUtilisateurRecherche.getNoUtilisateur(), numeroArticle,
							proposition);

					request.setAttribute("proposition", proposition);
					
					request.getSession().setAttribute("UserConnecte", unUtilisateurRecherche);

					response.sendRedirect(request.getContextPath() + "/");
				}
			}
		} catch (DALException e) {
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request, response);
		}
	}
	}
}
