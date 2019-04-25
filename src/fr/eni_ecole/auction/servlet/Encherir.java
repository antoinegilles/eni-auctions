package fr.eni_ecole.auction.servlet;

import java.io.IOException;

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
			ArticleVendu detailArticle = null;

			userManager = new UserManager();

			Utilisateur unUtilisateur = null;
			Utilisateur unUtilisateurRecherche = null;

			unUtilisateur = (Utilisateur) request.getSession().getAttribute("UserConnecte");

			unUtilisateurRecherche = userManager.selectUser(unUtilisateur.getEmail(), unUtilisateur.getMotDePasse());

			int proposition = Integer.parseInt(request.getParameter("proposition"));
			int numeroArticle = Integer.parseInt(request.getParameter("numeroArticle"));

			// Liste de l'articles de l'ench�res choisi
			detailArticleEncheri = articleManager.detailVente(numeroArticle);

			// Si le prix (en points) est sup�rieur au tarif actuel
			if (proposition >= detailArticleEncheri.getMisAPrix()) {

				// Si le compte de points ne devient pas n�gatif
				if (unUtilisateurRecherche.getCredit() >= proposition) {

					// Si l�ench�re est possible, mon cr�dit de points est d�bit� du montant
					// propos�.
					//unUtilisateurRecherche.setCredit(unUtilisateurRecherche.getCredit() - proposition);
					int creditRestant = unUtilisateurRecherche.getCredit() - proposition;
					unUtilisateurRecherche.setCredit(creditRestant);
							userManager.updateUserCredit(unUtilisateurRecherche);
					
					// TODO Le meilleur ench�risseur pr�c�dent si il existe est re-cr�dit� de son
					// offre.

					articleManager.ajouterUneEnchere(unUtilisateurRecherche.getNoUtilisateur(), numeroArticle,
							proposition);
					
					request.getSession().setAttribute("UserConnecte", unUtilisateurRecherche);
					
					response.sendRedirect(request.getContextPath() + "/");
				}
			}
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
}
