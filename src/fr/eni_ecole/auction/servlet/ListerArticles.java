package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
 public class ListerArticles extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{ 
			
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

}