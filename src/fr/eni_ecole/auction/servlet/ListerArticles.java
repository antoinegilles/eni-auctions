package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.dal.ArticleDAOjdbcImpl;
import fr.eni_ecole.auction.dal.CategorieDAOjdbcImpl;
import fr.eni_ecole.auction.dal.DALException;


/**
 * Lister les articles - l'affichage gerer dans la servlet
 * @author moujdari2018
 *
 */
@WebServlet("/ListerArticles")

 public class ListerArticles extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{ 
			
			// lister les categories
				List<Categorie> listeCategories =  CategorieDAOjdbcImpl.lister();
				request.setAttribute("liste", listeCategories);
			
			// Récupérer la liste des articles en BDD
				List<ArticleVendu> articles = ArticleDAOjdbcImpl.lister();
			
			// Placer les articlesVendus dans le contexte de requete			
			request.setAttribute("listeArticlesVendus", articles);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/articlesPage");
			dispatcher.forward(request,response);
		
		}catch (DALException e){
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request,response);
		}
		
	} 
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ArticleVendu unArticleVendu =null;
		try{ 
			
			String categorie = request.getParameter("categorie");
			String article = request.getParameter("article");
			
			// Ajoute un article
			List<ArticleVendu> listeArticlesEncheresCours = ArticleDAOjdbcImpl.listerEncheresEnCours(categorie, article);
			
			// Placer les articlesVendus dans le contexte de requete			
			request.setAttribute("listeArticlesEncheresCours", listeArticlesEncheresCours);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirmationEncheresCours");
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