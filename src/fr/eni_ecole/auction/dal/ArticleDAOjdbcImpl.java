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
import fr.eni_ecole.auction.util.AccesBase;
import fr.eni_ecole.auction.util.ManipDate;

/**
 * Reference toutes les m�thodes <strong>CRUD</strong> du metier Enchere
 * <li>ajouter : <em>ajouter une enchere</em></li>

 * @author moujdari2018
 * @version 1.0
 *
 */

public class ArticleDAOjdbcImpl implements ArticleDAO {
	
//	En tant qu�utilisateur, je peux vendre un article sur la plateforme ENI-Ench�res. 
//	Pour cela je donne les informations sur l�article vendu : 
//		nom, description et cat�gorie j�indique un prix de d�part (en points), 
//		une date et une heure d�ouverture de l�ench�re, 
//		une date et une heure de fin d�ench�res 
//		et les modalit�s du retrait : adresse (par d�faut celle du vendeur).

	
	private static final String LISTER="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres FROM articles_vendus";
	private static final String SELECT_BY_ID_ARTICLE="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial FROM articles_vendus WHERE no_article=?";
	private static final String LISTER_ENCHERES_COURS="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, pseudo FROM articles_vendus av LEFT JOIN utilisateurs u ON av.no_utilisateur = u.no_utilisateur WHERE av.no_utilisateur = u.no_utilisateur AND no_categorie LIKE ? AND nom_article LIKE ? AND GETDATE() > date_debut_encheres AND av.no_utilisateur= 3;";
	private static final String AJOUTER_ARTICLE="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) VALUES (?,?,?,?,?,?,?,?);";

	
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut �tre vide mais jamais <font color="red"><code>null</code></font></font>
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
			throw new DALException("probleme methode lister()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		
		return listeArticlesVendus;
	}
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut �tre vide mais jamais <font color="red"><code>null</code></font></font>
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
			throw new DALException("probleme methode rechercher()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		
		return listeArticlesEncheresCours;
	}
	
	/**
	 * M�thode permettant d'ajouter une article
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
				throw new DALException("probleme rollback methode ajouter()",e1);
			}
			throw new DALException("probleme methode ajouter()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
	}
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut �tre vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public ArticleVendu DetailVente(String detailVente) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		cnx=AccesBase.getConnection();
		try{
			pstmt=cnx.prepareStatement(SELECT_BY_ID_ARTICLE);
			ArticleVendu unarticle;
			pstmt.setString(1, detailVente);
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
				return unarticle;
		}
		}catch (SQLException e){
			throw new DALException("probleme methode rechercher()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		return null;
	}
}
