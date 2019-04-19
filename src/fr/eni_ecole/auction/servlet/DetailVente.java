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
import fr.eni_ecole.auction.dal.DAOFactory;


/**
 * Lister les articles - l'affichage gerer dans la servlet
 * @author moujdari2018
 *
 */
@WebServlet("/DetailVente")

 public class DetailVente extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
   	private ArticleManager articleManager;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{
			articleManager = new ArticleManager();
			ArticleVendu detailArticle = null;
	
				String detailVente = request.getParameter("detailVente");
				
				//TODO if le string article est null
				
				// Liste des articles enchères en cours
				detailArticle = articleManager.DetailVente(detailVente);
				
				// Placer des articles enchères en cours dans le contexte de requete			
				request.setAttribute("detailArticle", detailArticle);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/detailVente");
			dispatcher.forward(request,response);
		
		}catch (DALException e){
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request,response);
		}
		
	} 
 }