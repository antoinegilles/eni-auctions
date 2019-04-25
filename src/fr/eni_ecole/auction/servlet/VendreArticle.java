package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.bll.CategorieManager;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.util.ManipDate;


/**
 * Lister les articles - l'affichage gerer dans la servlet
 * @author moujdari2018
 *
 */
@WebServlet("/VendreArticle")

 public class VendreArticle extends HttpServlet {
   static final long serialVersionUID = 1L;
   
   private ArticleManager articleManager;
   private CategorieManager categorieManager;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		if (request.getSession().getAttribute("UserConnecte")==null) {
			request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/connexion");
		}else {
		// lister les categories
					try {
						categorieManager = new CategorieManager();
						List<Categorie> listeCategories =  categorieManager.listerLesCategories();
						request.setAttribute("liste", listeCategories);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/article/vendreArticle.jsp");
		dispatcher.forward(request,response);
		
	}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("UserConnecte")==null) {
			request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/connexion");
		}else {
		try{ 
			articleManager = new ArticleManager();
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie"); 
			int misePrix = Integer.parseInt(request.getParameter("misePrix")); 
			Date debutEnchere = ManipDate.stringVersUtil(request.getParameter("debutEnchere")); 
			Date finEnchere = ManipDate.stringVersUtil(request.getParameter("finEnchere"));
			
			// Ajoute un article
			articleManager.ajouterUnArticle(nomArticle, description, categorie, misePrix, debutEnchere, finEnchere);
			
			
			response.sendRedirect(request.getContextPath() + "/");
		
		}catch (DALException e){
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	}
	

}