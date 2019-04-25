package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.bll.CategorieManager;
import fr.eni_ecole.auction.dal.BusinessException;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.DAOFactory;


/**
 * Lister les articles - l'affichage gerer dans la servlet
 * @author moujdari2018
 *
 */
@WebServlet("/DetailVente")

 public class DetailVente extends HttpServlet {
   static final long serialVersionUID = 1L;
   
   	private ArticleManager articleManager;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try{
			articleManager = new ArticleManager();
			ArticleVendu detailArticle = null;
	
				int id = Integer.parseInt(request.getParameter("id"));
				
				// Liste des articles ench�res en cours
				detailArticle = articleManager.detailVente(id);
				
				int minPrice = ( detailArticle.getMisAPrix() > detailArticle.getPrixVente())? detailArticle.getMisAPrix() : detailArticle.getPrixVente();
				  minPrice++;
				
				// Placer des articles ench�res en cours dans le contexte de requete			
				  request.setAttribute("minPrice", minPrice);
				  request.setAttribute("detailArticle", detailArticle);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/detailVente");
			dispatcher.forward(request,response);
		
		}catch (DALException e){
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request,response);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
 }