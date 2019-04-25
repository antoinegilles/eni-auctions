package fr.eni_ecole.auction.dal;

import java.util.Date;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Utilisateur;

public interface ArticleDAO {
	public List<ArticleVendu> listerLesArticles() throws DALException;
	public List<ArticleVendu> listerLesEncheresEnCours(String categorie, String article) throws DALException;
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere, int noUtilisateur, String imagePath) throws DALException;
	public ArticleVendu detailVente(int detailVente) throws DALException;
	public int countArticlesForUser(Utilisateur user) throws DALException;
	public void ajouterUneEnchere(int no_utilisateur, int no_article, int montant_enchere) throws DALException;
}
