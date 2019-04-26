package fr.eni_ecole.auction.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Local;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.dal.BusinessException;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.util.ManipDate;


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
		if (request.getSession().getAttribute("UserConnecte")==null) {
			request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+"/connexion");
		}else {

		try{
			articleManager = new ArticleManager();
			ArticleVendu detailArticle = null;
	
				int id = Integer.parseInt(request.getParameter("id"));
				
				// Liste des articles enchères en cours
				detailArticle = articleManager.detailVente(id);
				
				int minPrice = ( detailArticle.getMisAPrix() > detailArticle.getPrixVente())? detailArticle.getMisAPrix() : detailArticle.getPrixVente();
				  minPrice++;

				  // Date du Jour
				  Date dateDuJour = new Date();
				  
				  if (detailArticle.getDateFinEncheres().after(dateDuJour)) {
				  
				// Placer des articles enchères en cours dans le contexte de requete
				  request.setAttribute("minPrice", minPrice);
				  request.setAttribute("detailArticle", detailArticle);
			
				request.setAttribute("detailArticle", detailArticle);

				String imagePath = "theme/img/no-image.jpg";
				if (detailArticle.getImagePath() != null && !detailArticle.getImagePath().equals("")) {
					imagePath = "uploads?img=" + detailArticle.getImagePath();
				}

				request.setAttribute("image", imagePath);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/detailVente");
			dispatcher.forward(request,response);
				  }
				  else {
					// Liste des articles enchères en cours
						detailArticle = articleManager.detailVente(id);
						
						request.setAttribute("detailArticle", detailArticle);
						
					  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/remporterUneVente");
						dispatcher.forward(request, response);
				  }
		}catch (DALException e){
			request.setAttribute("erreur", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreurPage");
			dispatcher.forward(request,response);
		}		
	}
	}

	
 }