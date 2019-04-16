package fr.eni_ecole.auction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.util.AccesBase;

/**
 * Reference toutes les méthodes <strong>CRUD</strong> du metier Enchere
 * <li>ajouter : <em>ajouter une enchere</em></li>

 * @author moujdari2018
 * @version 1.0
 *
 */

public class ArticleDAOjdbcImpl {
	
//	En tant qu’utilisateur, je peux vendre un article sur la plateforme ENI-Enchères. 
//	Pour cela je donne les informations sur l’article vendu : 
//		nom, description et catégorie j’indique un prix de départ (en points), 
//		une date et une heure d’ouverture de l’enchère, 
//		une date et une heure de fin d’enchères 
//		et les modalités du retrait : adresse (par défaut celle du vendeur).
	
	private static final String LISTER="SELECT nom_article, description, date_debut_encheres, date_fin_encheres FROM articles_vendus;";
	private static final String AJOUTER_ARTICLE="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) \r\n" + 
			"VALUES ('PC','PC occassion','1776-7-4 04:13:54','1776-7-4 04:13:54',150,0,3,4);";
	
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut être vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public static ArrayList<ArticleVendu> lister() throws DALException {
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<ArticleVendu> listeArticlesVendus = new ArrayList<ArticleVendu>();

		cnx=AccesBase.getConnection();
		try{
			stmt=cnx.createStatement();			
			rs=stmt.executeQuery(LISTER);
			ArticleVendu article;
			while (rs.next()){
				article = new ArticleVendu(
									rs.getString("nom_article"),
									rs.getString("description"),
									rs.getDate("date_debut_encheres"),
									rs.getDate("date_fin_encheres")
									
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
	 * Méthode permettant d'ajouter une article
	 * @param articleVendu : un objet de type ArticleVendu
	 * @throws DALException : propage une exception de type DALException
	 */
	public static void ajouter(ArticleVendu articleVendu) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;

		cnx=AccesBase.getConnection();
		try{
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(AJOUTER_ARTICLE);
			pstmt.setString(1, articleVendu.getNomArticle());
			pstmt.setString(2, articleVendu.getDescription());
			pstmt.setDate(3, new java.sql.Date(articleVendu.getDateDebutEncheres().getTime()));
			pstmt.setDate(4, new java.sql.Date(articleVendu.getDateFinEncheres().getTime()));
			pstmt.setInt(5, articleVendu.getMisAPrix());
			pstmt.setInt(6, articleVendu.getPrixVente());
			pstmt.setInt(7, 3);
			pstmt.setInt(8, 1);
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

}
