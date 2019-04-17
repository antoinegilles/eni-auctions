package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.dal.ArticleDAOjdbcImpl;
import fr.eni_ecole.auction.dal.CategorieDAOjdbcImpl;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.util.ManipDate;


/**
 * Lister les articles - l'affichage gerer dans la servlet
 * @author moujdari2018
 *
 */
@WebServlet("/VendreArticle")

 public class VendreArticle extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		// lister les categories
					try {
						List<Categorie> listeCategories =  CategorieDAOjdbcImpl.lister();
						request.setAttribute("liste", listeCategories);
					} catch (DALException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/article/vendreArticle.jsp");
		dispatcher.forward(request,response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ArticleVendu unArticleVendu =null;
		try{ 
			
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			String categorie = request.getParameter("categorie"); 
			int misePrix = Integer.parseInt(request.getParameter("misePrix")); 
			Date debutEnchere = ManipDate.stringVersUtil(request.getParameter("debutEnchere")); 
			Date finEnchere = ManipDate.stringVersUtil(request.getParameter("finEnchere"));
			
			// Ajoute un article
			ArticleDAOjdbcImpl.ajouter(nomArticle, description, categorie, misePrix, debutEnchere, finEnchere);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirmationVendreArticles");
			dispatcher.forward(request,response);
		
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