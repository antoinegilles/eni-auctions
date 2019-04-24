package fr.eni_ecole.auction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.util.AccesBase;
import fr.eni_ecole.auction.util.ManipDate;

/**
 * Reference toutes les mï¿½thodes <strong>CRUD</strong> du metier Enchere
 * <li>ajouter : <em>ajouter une enchere</em></li>

 * @author moujdari2018
 * @version 1.0
 *
 */

public class ArticleDAOjdbcImpl implements ArticleDAO {
	
//	En tant quï¿½utilisateur, je peux vendre un article sur la plateforme ENI-Enchï¿½res. 
//	Pour cela je donne les informations sur lï¿½article vendu : 
//		nom, description et catï¿½gorie jï¿½indique un prix de dï¿½part (en points), 
//		une date et une heure dï¿½ouverture de lï¿½enchï¿½re, 
//		une date et une heure de fin dï¿½enchï¿½res 
//		et les modalitï¿½s du retrait : adresse (par dï¿½faut celle du vendeur).

	
	private static final String LISTER="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial FROM articles_vendus";
	private static final String SELECT_BY_ID_ARTICLE="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, pseudo, rue, code_postal, ville, libelle, c.no_categorie \r\n" + 
			"FROM articles_vendus av LEFT JOIN utilisateurs u ON av.no_utilisateur = u.no_utilisateur \r\n" + 
			"INNER JOIN categories c ON c.no_categorie = av.no_categorie \r\n" + 
			"WHERE no_article=?";
	private static final String LISTER_ENCHERES_COURS="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, pseudo FROM articles_vendus av LEFT JOIN utilisateurs u ON av.no_utilisateur = u.no_utilisateur WHERE av.no_utilisateur = u.no_utilisateur AND no_categorie LIKE ? AND nom_article LIKE ? AND GETDATE() > date_debut_encheres;";
	private static final String AJOUTER_ARTICLE="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";
	private static final String AJOUTER_ENCHERE="INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,GETDATE(),?);";
	private static final String MAX_ENCHERE="select max(montant_enchere) from ENCHERES where no_article=?;";

	
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut ï¿½tre vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public List<ArticleVendu> listerLesArticles() throws DALException {
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();

		cnx=AccesBase.getConnection();
		try{
			stmt=cnx.createStatement();			
			rs=stmt.executeQuery(LISTER);
			ArticleVendu article;
			while (rs.next()){
				article = new ArticleVendu(
									rs.getInt("no_article"),
									rs.getString("nom_article"),
									rs.getString("description"),
									rs.getDate("date_debut_encheres"),
									rs.getDate("date_fin_encheres"),
									rs.getInt("prix_initial")
						);
				listeArticlesVendus.add(article);
			}
		}catch (SQLException e){
			throw new DALException("probleme methode listerLesArticles()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		
		return listeArticlesVendus;
	}
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut ï¿½tre vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public List<ArticleVendu> listerLesEncheresEnCours(String categorie, String article) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ArticleVendu> listeArticlesEncheresCours = new ArrayList<ArticleVendu>();

		cnx=AccesBase.getConnection();
		try{
			pstmt=cnx.prepareStatement(LISTER_ENCHERES_COURS);
			ArticleVendu unarticle;
			pstmt.setString(1, categorie);
			pstmt.setString(2, article);
			rs=pstmt.executeQuery();
			while (rs.next()){
				unarticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial")
						
			);
					listeArticlesEncheresCours.add(unarticle);
		}
		}catch (SQLException e){
			throw new DALException("probleme methode listerLesEncheresEnCours()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		
		return listeArticlesEncheresCours;
	}
	
	/**
	 * Mï¿½thode permettant d'ajouter une article
	 * @param articleVendu : un objet de type ArticleVendu
	 * @return 
	 * @throws DALException : propage une exception de type DALException
	 */
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;

		cnx=AccesBase.getConnection();
		try{
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(AJOUTER_ARTICLE);
			
			pstmt.setString(1, nomArticle); //nomArticle
			pstmt.setString(2, description); //description
			pstmt.setDate(3, ManipDate.dateUtilVersSQL(debutEnchere)); //debutEnchere
			pstmt.setDate(4, ManipDate.dateUtilVersSQL(finEnchere)); //finEnchere
			pstmt.setInt(5, misePrix); //misePrix
			pstmt.setInt(6, 0); //prixVente
			pstmt.setInt(7, 8); //utilisateur inconnu en base
			pstmt.setString(8, categorie); //categorie
			
			pstmt.executeUpdate();
			cnx.commit();
		}catch(SQLException e){
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				throw new DALException("probleme rollback methode ajouterUnArticle()",e1);
			}
			throw new DALException("probleme methode ajouterUnArticle()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
	}
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut ï¿½tre vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public ArticleVendu detailVente(int idArticle) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		cnx=AccesBase.getConnection();
		try{
			pstmt=cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			
			ArticleVendu unArticle;
			Utilisateur unUtilisateur;
			Categorie uneCategorie;
			
			pstmt.setInt(1, idArticle);
			rs=pstmt.executeQuery();
			while (rs.next()){
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"));
						
				unUtilisateur = new Utilisateur(
						rs.getString("pseudo"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"));
				
				uneCategorie = new Categorie(
						rs.getString("libelle"));
				
				unArticle.setUtilisateur(unUtilisateur);
				unArticle.setCategorie(uneCategorie);
				return unArticle;
		}
		}catch (SQLException e){
			throw new DALException("probleme methode detailVente()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		return null;
	}
	
	/**
	 * Méthode permettant d'ajouter une enchère dans la table ENCHERE
	 * @throws DALException : propage une exception de type DALException
	 */
	public void ajouterUneEnchere(int no_utilisateur, int no_article, int montant_enchere) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;

		cnx=AccesBase.getConnection();
		try{
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(AJOUTER_ENCHERE);
			
			pstmt.setInt(1, no_utilisateur); //no_utilisateur
			pstmt.setInt(2, no_article); //no_article
			pstmt.setInt(3, montant_enchere); //montant_enchere
			
			pstmt.executeUpdate();
			cnx.commit();
		}catch(SQLException e){
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				throw new DALException("probleme rollback methode ajouterUneEnchere()",e1);
			}
			throw new DALException("probleme methode ajouterUneEnchere()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
	}
	
	/**
	 * Méthode permettant de retrouner le montant max pour une enchère d'un article
	 * @throws DALException : propage une exception de type DALException
	 */
	public void maxUneEnchere(int no_article) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;

		cnx=AccesBase.getConnection();
		try{
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(MAX_ENCHERE);
			
			pstmt.setInt(1, no_article); //no_article
			
			pstmt.executeUpdate();
			cnx.commit();
		}catch(SQLException e){
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				throw new DALException("probleme rollback methode maxUneEnchere()",e1);
			}
			throw new DALException("probleme methode maxUneEnchere()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
	}
}
