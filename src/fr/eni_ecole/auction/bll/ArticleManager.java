package fr.eni_ecole.auction.bll;

import java.util.Date;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.dal.ArticleDAO;
import fr.eni_ecole.auction.dal.BusinessException;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.DAOFactory;

public class ArticleManager {
	private ArticleDAO articleDAO;
	
	public ArticleManager() {
		articleDAO = DAOFactory.getArticleDAO();
	}
	
	public List<ArticleVendu> listerLesArticles() throws DALException, BusinessException
	{
		return articleDAO.listerLesArticles();
	}

	public List<ArticleVendu> listerLesArticlesEncheresEnCours(String categorie, String article) throws DALException, BusinessException
	{
		return articleDAO.listerLesEncheresEnCours(categorie, article);
	}
	
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere) throws DALException, BusinessException
	{
		articleDAO.ajouterUnArticle(nomArticle, description, categorie, misePrix, debutEnchere, finEnchere);
	}
	
	public ArticleVendu detailVente(int detailVente) throws DALException, BusinessException
	{
		return articleDAO.detailVente(detailVente);
	}
	
	public void ajouterUneEnchere(int no_utilisateur, int no_article, int montant_enchere) throws DALException, BusinessException
	{
		
		//Enchère sur un article si je propose un prix (en points) supérieur au tarif actuel
				//if(proposition >= detailArticleEncheri.getMisAPrix()) {
					//si mon compte de points ne devient pas négatif.
					//if(si mon compte de points ne devient pas négatif.)
					
		//Si l’enchère est possible, mon crédit de points est débité du montant proposé. 
		//Le meilleur enchérisseur précédent si il existe est re-crédité de son offre.
		articleDAO.ajouterUneEnchere(no_utilisateur, no_article, montant_enchere);
	}
	

	
}
