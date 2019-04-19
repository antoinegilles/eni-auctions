package fr.eni_ecole.auction.bll;

import java.util.Date;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.dal.ArticleDAO;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.DAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;
	
	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public List<ArticleVendu> listerLesArticles() throws DALException
	{
		return articleDAO.listerLesArticles();
	}

	public List<ArticleVendu> listerLesArticlesEncheresEnCours(String categorie, String article) throws DALException
	{
		return articleDAO.listerLesEncheresEnCours(categorie, article);
	}
	
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere) throws DALException
	{
		articleDAO.ajouterUnArticle(nomArticle, description, categorie, misePrix, debutEnchere, finEnchere);
	}
	
	public ArticleVendu DetailVente(String detailVente) throws DALException
	{
		return articleDAO.DetailVente(detailVente);
	}
	

	
}
