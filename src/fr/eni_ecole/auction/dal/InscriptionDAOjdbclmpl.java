package fr.eni_ecole.auction.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.util.AccesBase;

public class InscriptionDAOjdbclmpl implements InscriptionDAO  {

private static final String AJOUTER_USER="INSERT INTO UTILISATEURS (pseudo, nom, prenom,"
		+ " email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) \r\n" + 
			"VALUES (?,?,?,?,?,?,?,?,?,?,?);";


public Utilisateur inscrire(String pseudo, String nom, String prenom, String email, String telephone,
		String rue, String codePostal, String ville, String mdp, int Credit, boolean Administrateur) throws DALException{
	Connection cnx=null;
	PreparedStatement pstmt=null;
	Utilisateur utilisateur;
	
	cnx=AccesBase.getConnection();
	try{
		utilisateur = new Utilisateur();

		cnx.setAutoCommit(false);
		pstmt=cnx.prepareStatement(AJOUTER_USER);
		pstmt.setString(1,pseudo);
		pstmt.setString(2,nom);
		pstmt.setString(3,prenom);
		pstmt.setString(4,email);
		pstmt.setString(5,telephone);
		pstmt.setString(6,rue);
		pstmt.setString(7, codePostal);
		pstmt.setString(8,ville );
		pstmt.setString(9, mdp);
		pstmt.setInt(10, Credit);
		if(Administrateur) {
			pstmt.setInt(11, 1);
		}else {
			pstmt.setInt(11, 0);
		}
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
	return utilisateur;
}
}
