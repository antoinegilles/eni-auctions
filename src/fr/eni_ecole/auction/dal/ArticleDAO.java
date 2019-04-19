package fr.eni_ecole.auction.dal;

import java.util.Date;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;

public interface ArticleDAO {
	public List<ArticleVendu> listerLesArticles() throws DALException;
	public List<ArticleVendu> listerLesEncheresEnCours(String categorie, String article) throws DALException;
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere) throws DALException;
	public ArticleVendu detailVente(int detailVente) throws DALException;
}
