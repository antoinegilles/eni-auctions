package fr.eni_ecole.auction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.util.AccesBase;

public class UserDAOjdbclmpl {

	private static final String GETUSER="SELECT prenom, nom FROM UTILISATEURS where email=? and mot_de_passe=?;";

	public static Utilisateur selectUser( String email, String password) throws DALException {
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
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setNom(rs.getString("nom"));
									
						
			}
		}catch (SQLException e){
			throw new DALException("probleme methode lister()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		
		return utilisateur;
	}
}
