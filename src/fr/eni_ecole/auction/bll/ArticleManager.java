package fr.eni_ecole.auction.bll;

import java.util.Date;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Utilisateur;
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

	public List<ArticleVendu> listerLesEncheresEnCours(String categorie, String article) throws DALException, BusinessException
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
	
	public List<ArticleVendu> listeEncheres(String categorie, String article, String open, String ongoing, String won, Utilisateur unUtilisateur) throws DALException, BusinessException
	{
		return articleDAO.listeEncheres(categorie, article, open, ongoing, won, unUtilisateur);
	}
	
	public void ajouterUneEnchere(int no_utilisateur, int no_article, int montant_enchere) throws DALException, BusinessException
	{
		
		//Ench�re sur un article si je propose un prix (en points) sup�rieur au tarif actuel
				//if(proposition >= detailArticleEncheri.getMisAPrix()) {
					//si mon compte de points ne devient pas n�gatif.
					//if(si mon compte de points ne devient pas n�gatif.)
					
		//Si l�ench�re est possible, mon cr�dit de points est d�bit� du montant propos�. 
		//Le meilleur ench�risseur pr�c�dent si il existe est re-cr�dit� de son offre.
		articleDAO.ajouterUneEnchere(no_utilisateur, no_article, montant_enchere);
	}
	
	public List<ArticleVendu> listeArticleVendus(String categorie, String article, String sellsOngoing, String sellsOpen, String sellsWon, Utilisateur unUtilisateur) throws DALException, BusinessException
	{
		return articleDAO.listeArticleVendus(categorie, article, sellsOngoing, sellsOpen, sellsWon, unUtilisateur);
	}
	

	
}
