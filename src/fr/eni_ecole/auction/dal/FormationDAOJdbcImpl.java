package fr.eni_ecole.jee.dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.eni_ecole.jee.bean.Formation;
import fr.eni_ecole.jee.util.AccesBase;

/**
 * Reference toutes les methodes <strong>CRUD</strong> du metier Formation
 * <ul><li>lister : <em>lister toutes les formations</em></li>
 * <li>rechercher : <em>rechercher une formation par son identifiant</em></li>
 * <li>ajouter : <em>ajouter une formation</em></li>
 * <li>modifier : <em>modifier une formation</em></li>
 * <li>supprimer : <em>supprimer une formation</em></li></ul>
 * @author thierry
 * @version 1.0
 *
 */
public class FormationDAOJdbcImpl {
	
	private static final String LISTER="SELECT * FROM formations;";
	private static final String RECHERCHER="SELECT * FROM formations WHERE id = ?;";
	private static final String AJOUTER="INSERT INTO formations (libelle, debut, fin, description) VALUES (?,?,?,?);";
	private static final String MODIFIER="UPDATE formations SET libelle = ?, debut = ?, fin = ?, description = ? WHERE id = ?;";
	private static final String SUPPRIMER="DELETE FROM formations WHERE id = ?;";
	
	/**
	 * Methode permettant d'obtenir une liste des formations
	 * @return <font color="green">La liste peut être vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public static ArrayList<Formation> lister() throws DALException {
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Formation> listeFormations = new ArrayList<Formation>();

		cnx=AccesBase.getConnection();
		try{
			stmt=cnx.createStatement();			
			rs=stmt.executeQuery(LISTER);
			Formation formation;
			while (rs.next()){
				formation = new Formation(
									rs.getInt("id"),
									rs.getString("libelle"),
									rs.getDate("debut"),
									rs.getDate("fin"),
									rs.getString("description")
						);
				listeFormations.add(formation);				
			}
		}catch (SQLException e){
			throw new DALException("probleme methode lister()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		
		return listeFormations;
	}
	
	/**
	 * Methode permettant de rechercher une formation par l'id
	 * @param id : identifiant de la formation
	 * @return formation : un objet de type Formation
	 * @throws DALException : propage une exception de type DALException
	 */
	public static Formation rechercher(int id) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Formation formation = null;
		cnx=AccesBase.getConnection();

		try{
			pstmt=cnx.prepareStatement(RECHERCHER);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if (rs.next()){
				if (formation==null) formation = new Formation();
				formation.setLibelle(rs.getString("libelle"));				
				formation.setDateDebut(rs.getDate("debut"));
				formation.setDateFin(rs.getDate("fin"));
				formation.setDescription(rs.getString("description"));
			}
		}catch (SQLException e){
			throw new DALException("probleme methode rechercher()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		
		return formation;
	}
	
	/**
	 * Methode permettant d'ajouter une formation
	 * @param formation : un objet de type Formation
	 * @throws DALException : propage une exception de type DALException
	 */
	public static void ajouter(Formation formation) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;

		cnx=AccesBase.getConnection();
		try{
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(AJOUTER);
			pstmt.setString(1, formation.getLibelle());
			pstmt.setDate(2, new java.sql.Date(formation.getDateDebut().getTime()));
			pstmt.setDate(3, new java.sql.Date(formation.getDateFin().getTime()));
			pstmt.setString(4, formation.getDescription());
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
	 * Methode permettant de supprimer une formation
	 * @param formation : un objet de type Formation
	 * @throws DALException : propage une exception de type DALException
	 */
	public static void supprimer(Formation formation) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;

		cnx=AccesBase.getConnection();
		try{
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(SUPPRIMER);
			pstmt.setInt(1, formation.getId());
			pstmt.executeUpdate();
			cnx.commit();
			
		}catch(SQLException e){
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				throw new DALException("probleme rollback methode supprimer()",e1);
			}
			throw new DALException("probleme methode supprimer()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
	}
	
	/**
	 * Methode permettant de modifier une formation
	 * @param formation : un objet de type Formation
	 * @throws DALException : propage une exception de type DALException
	 */
	public static void modifier(Formation formation) throws DALException{
		Connection cnx=null;
		PreparedStatement pstmt=null;

		cnx=AccesBase.getConnection();
		try{
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(MODIFIER);
			pstmt.setString(1, formation.getLibelle());
			pstmt.setDate(2, new java.sql.Date(formation.getDateDebut().getTime()));
			pstmt.setDate(3, new java.sql.Date(formation.getDateFin().getTime()));
			pstmt.setString(4,formation.getDescription());
			pstmt.setInt(5, formation.getId());
			pstmt.executeUpdate();
			cnx.commit();

		}catch(SQLException e){
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				throw new DALException("probleme rollback methode modifier()",e1);
			}
			throw new DALException("probleme methode modifier()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
	}
	
}
