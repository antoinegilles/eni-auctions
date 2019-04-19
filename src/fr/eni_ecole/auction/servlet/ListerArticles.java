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
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.bll.CategorieManager;
import fr.eni_ecole.auction.dal.DALException;


/**
 * Lister les articles - l'affichage gerer dans la servlet
 * @author moujdari2018
 *
 */
@WebServlet("/ListerArticles")

 public class ListerArticles extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   	private CategorieManager categorieManager;
   	private ArticleManager articleManager;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{
			categorieManager = new CategorieManager();
			articleManager = new ArticleManager();
			
			// Lister les catégories dans la page d'accueil
				List<Categorie> listeCategories =  categorieManager.listerLesCategories();
				request.setAttribute("liste", listeCategories);
				
				String categorie = (request.getParameter("categorie") == null)? "%%" : "%" + request.getParameter("categorie") + "%";
				String article = (request.getParameter("article") == null)? "%%" : "%" + request.getParameter("article") + "%";
				
			// Liste des articles dans la page d'accueil
			// List<ArticleVendu> listeDesArticles = articleManager.listerLesArticles();
				
			// Placer des articles dans le contexte de requete			
			//	request.setAttribute("listeDesArticles", listeDesArticles);
				
			// Liste des articles enchères en cours
				List<ArticleVendu> listeArticlesEncheresCours = articleManager.listerLesArticlesEncheresEnCours(categorie, article);
				
			// Placer des articles enchères en cours dans le contexte de requete			
				request.setAttribute("listeArticlesEncheresCours", listeArticlesEncheresCours);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request,response);
		
		}catch (DALException e){
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request,response);
		}
		
	} 
 }