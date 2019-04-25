package fr.eni_ecole.auction.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.dal.BusinessException;
import fr.eni_ecole.auction.dal.DALException;

/**
 * Servlet implementation class RemporterUneVente
 */
@WebServlet("/RemporterUneVente")
public class RemporterUneVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleManager articleManager;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("UserConnecte")==null) {
			request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/connexion");
		}else {
		
		articleManager = new ArticleManager();
		ArticleVendu detailArticle = null;
		
		int numeroArticle = Integer.parseInt(request.getParameter("numeroArticle"));
		
		// Liste des articles ench�res en cours
		try {
			detailArticle = articleManager.detailVente(numeroArticle);
		} catch (DALException | BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Placer des articles ench�res en cours dans le contexte de requete			
		request.setAttribute("detailArticle", detailArticle);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/article/remporterUneVente.jsp");
		dispatcher.forward(request,response);
	}
	}
}
