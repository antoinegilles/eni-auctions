package fr.eni_ecole.auction.dal;

import java.util.Date;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Utilisateur;

public interface ArticleDAO {
	public List<ArticleVendu> listerLesArticles() throws DALException;
	public List<ArticleVendu> listerLesEncheresEnCours(String categorie, String article) throws DALException;
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere) throws DALException;
	public ArticleVendu detailVente(int detailVente) throws DALException;
	public void ajouterUneEnchere(int no_utilisateur, int no_article, int montant_enchere) throws DALException;
	public List<ArticleVendu> listeEncheres(String categorie, String article, String open, String ongoing, String won, Utilisateur unUtilisateur) throws DALException;
	public List<ArticleVendu> listeArticleVendus(String categorie, String article, String sellsOngoing, String sellsOpen, String sellsWon, Utilisateur unUtilisateur) throws DALException;
}
