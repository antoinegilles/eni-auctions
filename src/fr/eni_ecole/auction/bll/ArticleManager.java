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
	
	public List<ArticleVendu> listerLesArticles() throws DALException
	{
		return articleDAO.listerLesArticles();
	}

	public List<ArticleVendu> listerLesEncheresEnCours(String categorie, String article) throws DALException
	{
		return articleDAO.listerLesEncheresEnCours(categorie, article);
	}
	
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere, int noUtilisateur, String imagePath) throws DALException
	{
		articleDAO.ajouterUnArticle(nomArticle, description, categorie, misePrix, debutEnchere, finEnchere, noUtilisateur, imagePath);
	}
	
	public ArticleVendu detailVente(int detailVente) throws DALException
	{
		return articleDAO.detailVente(detailVente);
	}

	public List<ArticleVendu> listeEncheres(String categorie, String article, String open, String ongoing, String won, String radioChoice, Utilisateur unUtilisateur) throws DALException
	{
		return articleDAO.listeEncheres(categorie, article, open, ongoing, won, radioChoice, unUtilisateur);
	}

	public void ajouterUneEnchere(int no_utilisateur, int no_article, int montant_enchere) throws DALException
	{

		//Ench�re sur un article si je propose un prix (en points) sup�rieur au tarif actuel
				//if(proposition >= detailArticleEncheri.getMisAPrix()) {
					//si mon compte de points ne devient pas n�gatif.
					//if(si mon compte de points ne devient pas n�gatif.)

		//Si l�ench�re est possible, mon cr�dit de points est d�bit� du montant propos�.
		//Le meilleur ench�risseur pr�c�dent si il existe est re-cr�dit� de son offre.
		articleDAO.ajouterUneEnchere(no_utilisateur, no_article, montant_enchere);
	}

	public List<ArticleVendu> listeArticleVendus(String categorie, String article, String sellsOngoing, String sellsOpen, String sellsWon, String radioChoice, Utilisateur unUtilisateur) throws DALException, BusinessException
	{
		return articleDAO.listeArticleVendus(categorie, article, sellsOngoing, sellsOpen, sellsWon, radioChoice, unUtilisateur);
	}


	public int countArticlesForUser(Utilisateur user) throws DALException {
		return articleDAO.countArticlesForUser(user);
	}
}
