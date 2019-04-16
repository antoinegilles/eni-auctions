package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni_ecole.jee.bean.Animateur;
import fr.eni_ecole.jee.util.AccesBase;

/**
 * Reference toutes les methodes <strong>CRUD</strong> du metier Animateur
 * <ul><li>rechercher : <em>rechercher un animateur par son email et son mot de passe</em></li></ul>
 * @author thierry
 * @version 1.0
 */
public class AnimateurDAOJdbcImpl {
	private static final String RECHERCHER="select id, nom, prenom from animateurs where email=? and motdepasse=?;";

	/**
	 * Methode permettant de rechercher l'existence de l'email et du mot de passe d'un animateur 
	 * souhaitant s'authentifier
	 * @param mail : email servant de login
	 * @param password : mot de passe
	 * @return un objet de type Animateur (avec seulement le nom et le prénom)
	 * @throws DALException : propage une exception de type DALException
	 */
	public static Animateur rechercher(String mail, String password) throws DALException{
		Connection cnx = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Animateur animateur=null;

		cnx = AccesBase.getConnection();
		try{
			pstmt = cnx.prepareStatement(RECHERCHER);
			pstmt.setString(1, mail);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if (rs.next()){
				animateur = new Animateur();
				animateur.setId(rs.getInt("id"));
				animateur.setNom(rs.getString("nom"));
				animateur.setPrenom(rs.getString("prenom"));
			}
		}catch (SQLException e){
			throw new DALException("probleme methode rechercher()",e);
		}finally{
			AccesBase.seDeconnecter(pstmt, cnx);
		}
		return animateur;
	}

}
