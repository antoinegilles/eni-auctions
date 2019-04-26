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

	
	private static final String LISTER="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, img_path FROM articles_vendus";
	private static final String SELECT_BY_ID_ARTICLE="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, img_path, pseudo, rue, code_postal, ville, libelle, c.no_categorie, credit \r\n" +
			"FROM articles_vendus av LEFT JOIN utilisateurs u ON av.no_utilisateur = u.no_utilisateur \r\n" + 
			"INNER JOIN categories c ON c.no_categorie = av.no_categorie \r\n" + 
			"WHERE no_article=?";
	private static final String LISTER_ENCHERES_COURS="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, img_path, pseudo, rue, ville, code_postal, libelle FROM articles_vendus av LEFT JOIN utilisateurs u ON av.no_utilisateur = u.no_utilisateur INNER JOIN categories c ON c.no_categorie = av.no_categorie WHERE av.no_utilisateur = u.no_utilisateur AND c.no_categorie LIKE ? AND nom_article LIKE ?;";
	private static final String AJOUTER_ARTICLE="INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie, img_path) VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String AJOUTER_ENCHERE="INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?,?,GETDATE(),?);";
    private static final String MAX_ENCHERE="select max(montant_enchere) from ENCHERES where no_article=?;";
    private static final String SELECT_COUNT_USER_ARTICLE = "SELECT COUNT(av.no_article) AS 'count' FROM articles_vendus av INNER JOIN utilisateurs u ON u.no_utilisateur = av.no_utilisateur WHERE u.no_utilisateur = ? ;";
    private static final String LISTER_ENCHERES="Select no_encheres,av.no_article,date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, montant_enchere, nom_article, description,libelle,pseudo,rue,ville,code_postal, img_path\r\n" +
            "from ENCHERES e INNER JOIN articles_vendus av ON av.no_article = e.no_article\r\n" +
            "INNER JOIN categories c ON c.no_categorie = av.no_categorie\r\n" +
            "INNER JOIN utilisateurs u ON av.no_utilisateur = u.no_utilisateur";

    private static final String LISTER_ARTICLES_VENDUS="SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, libelle, pseudo, rue,ville,code_postal, av.no_utilisateur, img_path\r\n" +
            "FROM articles_vendus av\r\n" +
            "INNER JOIN categories c ON c.no_categorie = av.no_categorie\r\n" +
            "INNER JOIN utilisateurs u ON av.no_utilisateur = u.no_utilisateur";

	
	
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
									rs.getInt("prix_initial"),
									rs.getString("img_path")
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
	 * @return <font color="green">La liste peut �tre vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public List<ArticleVendu> listerLesEncheresEnCours(String categorie, String article) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ArticleVendu> listerLesEncheresEnCours = new ArrayList<ArticleVendu>();

		cnx=AccesBase.getConnection();
		try{
			pstmt=cnx.prepareStatement(LISTER_ENCHERES_COURS);
			ArticleVendu unarticle;
			Categorie uneCategorie;
			Utilisateur unUtilisateur;

			pstmt.setString(1, categorie);
			pstmt.setString(2, article);
			rs=pstmt.executeQuery();
			while (rs.next()) {

				unarticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getString("img_path"));
				
				unUtilisateur = new Utilisateur(rs.getString("pseudo"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"));
				
				uneCategorie = new Categorie(
						rs.getString("libelle"));

				unarticle.setCategorie(uneCategorie);
				unarticle.setUtilisateur(unUtilisateur);

				listerLesEncheresEnCours.add(unarticle);
			}
		}catch (SQLException e){
			throw new DALException("probleme methode listerLesEncheresEnCours()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		
		return listerLesEncheresEnCours;
	}
	
	/**
	 * Méthode permettant d'ajouter une article
	 * @param  : un objet de type ArticleVendu
	 * @return 
	 * @throws DALException : propage une exception de type DALException
	 */
	public void ajouterUnArticle(String nomArticle, String description, String categorie, int misePrix, Date debutEnchere, Date finEnchere, int noUtilisateur, String imagePath) throws DALException {
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
			pstmt.setInt(6, misePrix); //prixVente
			pstmt.setInt(7, noUtilisateur); //utilisateur inconnu en base
			pstmt.setString(8, categorie); //categorie
			pstmt.setString(9, imagePath); //categorie

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
	 * @return <font color="green">La liste peut �tre vide mais jamais <font color="red"><code>null</code></font></font>
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
						rs.getInt("prix_initial"),
						rs.getString("img_path"));
						
				unUtilisateur = new Utilisateur(
						rs.getString("pseudo"),
						rs.getString("rue"),
						rs.getString("code_postal"),
						rs.getString("ville"),
						rs.getInt("credit"));
				
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
	 * Methode permettant d'obtenir le nombre d'article pour un utilisateur
	 * @return nombre d'articles détenus par un utilisateur
	 * @throws DALException : propage une exception de type DALException
	 */
	public int countArticlesForUser(Utilisateur user) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		cnx=AccesBase.getConnection();
		try{
			pstmt=cnx.prepareStatement(SELECT_COUNT_USER_ARTICLE);

			pstmt.setInt(1, user.getNoUtilisateur());
			rs=pstmt.executeQuery();
			while (rs.next()){
				return rs.getInt("count");
			}
		}catch (SQLException e){
			throw new DALException("probleme methode rechercher()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		return -1;
	}

	/**
	 * M�thode permettant d'ajouter une ench�re dans la table ENCHERE
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
	 * M�thode permettant de retrouner le montant max pour une ench�re d'un article
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

	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut �tre vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public List<ArticleVendu> listeEncheres(String categorie, String article, String open, String ongoing, String won, String radioChoice, Utilisateur unUtilisateur) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ArticleVendu> listeEncheres = new ArrayList<ArticleVendu>();

		cnx=AccesBase.getConnection();
		try{
			StringBuilder where = new StringBuilder();
			where.append(" WHERE 1=1");

				// mes enchères ouvertes "open" Tests OK
				if(radioChoice != null && open != null) {
					where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? ");

				}

				// mes enchères en cours Tests OK
				if(radioChoice != null && ongoing != null){
					where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? AND u.no_utilisateur="+unUtilisateur.getNoUtilisateur());
				}

				// mes enchères remportées Tests Ok
				if(radioChoice != null && won != null)  {
					where.append(" AND date_fin_encheres <= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? AND u.no_utilisateur="+unUtilisateur.getNoUtilisateur());
				}
				
				// Si le Bouton Achats, le seul a être selectioné
				if(radioChoice != null && open == null && ongoing == null && won == null) {
					where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? ");
				}

			pstmt=cnx.prepareStatement(LISTER_ENCHERES + where);
			pstmt.setString(1, categorie);
			pstmt.setString(2, article);
			ArticleVendu unArticle;
			Utilisateur unUtilisateurR;
			Categorie uneCategorie;


			rs=pstmt.executeQuery();
			while (rs.next()) {
				unArticle = new ArticleVendu(
						rs.getInt("no_article"),
						rs.getString("nom_article"),
						rs.getString("description"),
						rs.getDate("date_debut_encheres"),
						rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"),
						rs.getInt("prix_vente"),
						rs.getString("img_path")
				);

				unUtilisateurR = new Utilisateur(rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				uneCategorie = new Categorie(rs.getString("libelle"));

				unArticle.setUtilisateur(unUtilisateurR);
				unArticle.setCategorie(uneCategorie);

				listeEncheres.add(unArticle);
			}
		}catch (SQLException e){
			throw new DALException("probleme methode listerLesEncheresEnCours()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}

		return listeEncheres;
	}

	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut �tre vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public List<ArticleVendu> listeArticleVendus(String categorie, String article, String sellsOngoing, String sellsOpen, String sellsWon, String radioChoice, Utilisateur unUtilisateur) throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<ArticleVendu> listeArticleVendus = new ArrayList<ArticleVendu>();

		cnx=AccesBase.getConnection();
		try{
			StringBuilder where = new StringBuilder();
			where.append(" WHERE 1=1");
			

				// mes ventes en cours "sells-ongoing"
				if(radioChoice != null && sellsOngoing != null) {
					where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? AND av.no_utilisateur="+unUtilisateur.getNoUtilisateur());
				}

				// enchères non débutés "sells-open"
				if(radioChoice != null && sellsOpen != null){
					where.append(" AND date_debut_encheres >= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? AND av.no_utilisateur="+unUtilisateur.getNoUtilisateur());
				}

				// mes ventes terminées "sells-won"
				if(radioChoice != null && sellsWon != null)  {
					where.append(" AND date_fin_encheres >= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? AND av.no_utilisateur="+unUtilisateur.getNoUtilisateur());
				}
				
				// Si le Bouton Ventes, le seul a être selectioné
				if(radioChoice != null && sellsOngoing == null && sellsOpen == null && sellsWon == null) {
					where.append(" AND date_fin_encheres >= GETDATE() AND date_debut_encheres <= GETDATE() AND c.no_categorie LIKE ? AND av.nom_article LIKE ? AND av.no_utilisateur="+unUtilisateur.getNoUtilisateur());
				}

			pstmt=cnx.prepareStatement(LISTER_ARTICLES_VENDUS + where);
			pstmt.setString(1, categorie);
			pstmt.setString(2, article);
			ArticleVendu unArticle;
			Categorie uneCategorie;
			Utilisateur unUtilisateurBDD;


			rs=pstmt.executeQuery();
			while (rs.next()) {
				unArticle = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"),
						rs.getInt("prix_initial"), rs.getInt("prix_vente"),
						rs.getString("img_path"));

				uneCategorie = new Categorie(rs.getString("libelle"));
				
				unUtilisateurBDD = new Utilisateur(rs.getString("pseudo"), rs.getString("rue"),
						rs.getString("code_postal"), rs.getString("ville"));

				unArticle.setUtilisateur(unUtilisateurBDD);
				unArticle.setCategorie(uneCategorie);

				listeArticleVendus.add(unArticle);
			}
		}catch (SQLException e){
			throw new DALException("probleme methode listerLesEncheresEnCours()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}

		return listeArticleVendus;
	}
}
