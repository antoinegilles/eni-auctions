package fr.eni_ecole.auction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.util.AccesBase;
import fr.eni_ecole.auction.dal.DALException;

public class UserDAOjdbclmpl implements UserDAO {
  
	private static final String GETUSER="SELECT no_utilisateur, pseudo, prenom, nom, pseudo,email,rue,telephone,code_postal,ville,mot_de_passe,credit FROM UTILISATEURS where email=? and mot_de_passe=?;";

	private static final String GETPSEUDO="SELECT pseudo FROM UTILISATEURS where pseudo=?;";
	private static final String GETPRENOM="SELECT prenom FROM UTILISATEURS where prenom=?;";
	private static final String MODIFIER="UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?,"
			+ "telephone = ?, rue = ?, code_postal = ?, ville = ?,mot_de_passe =? WHERE pseudo = ?;";

	private static final String DELETE="UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?,"
			+ "telephone = ?, rue = ?, code_postal = ?, ville = ?,mot_de_passe =?, credit = ? WHERE pseudo = ?;";

	private static final String MODIFIER_CREDIT="UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ?;";


	
	public Utilisateur selectUser( String email, String password) throws DALException {
		Connection cnx=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Utilisateur utilisateur = null;

		cnx=AccesBase.getConnection();
		try{
			stmt = cnx.prepareStatement(GETUSER);
			stmt.setString(1, email);
			stmt.setString(2, password);
			rs=stmt.executeQuery();
			if (rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
        utilisateur.setCredit(rs.getInt("credit"));						
			}
		}catch (SQLException e){
			throw new DALException("probleme methode lister()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		return utilisateur;
	}


	public Utilisateur selectPseudo( String pseudo) throws DALException {
		Connection cnx=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Utilisateur utilisateur = null;
	
		cnx=AccesBase.getConnection();
		try{
			stmt = cnx.prepareStatement(GETPSEUDO);
			stmt.setString(1, pseudo);
			rs=stmt.executeQuery();
			if (rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setPrenom(rs.getString("pseudo"));
			}
		}catch (SQLException e){
			throw new DALException("probleme methode selectPseudo()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		return utilisateur;
	}

	public Utilisateur selectPrenom( String prenom) throws DALException {
		Connection cnx=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Utilisateur utilisateur = null;
	
		cnx=AccesBase.getConnection();
		try{
			stmt = cnx.prepareStatement(GETPRENOM);
			stmt.setString(1, prenom);
			rs=stmt.executeQuery();
			if (rs.next()){
				utilisateur = new Utilisateur();
				utilisateur.setPrenom(rs.getString("prenom"));
			}
		}catch (SQLException e){
			throw new DALException("probleme methode selectPrenom()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		return utilisateur;
	}

	public Utilisateur updateUser(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String mdp) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;
    	Utilisateur utilisateur;


			cnx=AccesBase.getConnection();
			try{
				utilisateur = new Utilisateur();
				cnx.setAutoCommit(false);
				pstmt=cnx.prepareStatement(MODIFIER);
				pstmt.setString(1,pseudo);
				pstmt.setString(2,nom);
				pstmt.setString(3,prenom);
				pstmt.setString(4,email);
				pstmt.setString(5,telephone);
				pstmt.setString(6,rue);
				pstmt.setString(7,codePostal);
				pstmt.setString(8,ville );
				pstmt.setString(9, mdp);
				pstmt.setString(10,pseudo);
				pstmt.executeUpdate();
				cnx.commit();
			}catch(SQLException e){
				try {
					cnx.rollback();
				} catch (SQLException e1) {
					throw new DALException("probleme rollback methode updateUser()",e1);
				}
				throw new DALException("probleme methode UpdateUser()",e);
			}finally{
				AccesBase.seDeconnecter(pstmt, cnx);
			}

			return utilisateur;
		}

	public Utilisateur deleteUser(String pseudo, String nom, String prenom, String email, String telephone, String rue,
			String codePostal, String ville, String mdp, int credit)  throws DALException {
		Connection cnx=null;
		PreparedStatement pstmt=null;
		Utilisateur utilisateur;

		cnx=AccesBase.getConnection();
		try{
			utilisateur = new Utilisateur();
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(DELETE);
			pstmt.setString(1, "");
			pstmt.setString(2,"");
			pstmt.setString(3,"");
			pstmt.setString(4,"");
			pstmt.setString(5,"");
			pstmt.setString(6,"");
			pstmt.setString(7, "");
			pstmt.setString(8,"" );
			pstmt.setString(9, "");
			pstmt.setInt(10, 0);
			pstmt.setString(11, pseudo);
			pstmt.executeUpdate();
			cnx.commit();
		}catch(SQLException e){
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				throw new DALException("probleme rollback methode updateUser()",e1);
			}
			throw new DALException("probleme methode UpdateUser()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		  }
		  return utilisateur;	
		}
	
	public void updateUserCredit(Utilisateur unUtilisateur) throws DALException {
			Connection cnx=null;
			PreparedStatement pstmt=null;

			cnx=AccesBase.getConnection();
			try{
				cnx.setAutoCommit(false);
				pstmt=cnx.prepareStatement(MODIFIER_CREDIT);
				pstmt.setInt(1, unUtilisateur.getCredit());
				pstmt.setInt(2, unUtilisateur.getNoUtilisateur());

				pstmt.executeUpdate();
				cnx.commit();
			}catch(SQLException e){
				try {
					cnx.rollback();
				} catch (SQLException e1) {
					throw new DALException("probleme rollback methode updateUserCredit()",e1);
				}
				throw new DALException("probleme methode updateUserCredit()",e);
			}finally{
				AccesBase.seDeconnecter(pstmt, cnx);
			}
		}


}
