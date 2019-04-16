package fr.eni_ecole.auction.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni_ecole.auction.dal.DALException;

/**
 * Classe gerant la connexion et deconnexion à la BDD
 * @author thierry
 *
 */
public class AccesBase {
	
	/**
	 * Methode permettant d'obtenir une connexion à la BDD
	 * @return une connexion
	 * @throws DALException : propage une exception de type DALException
	 */
	public static Connection getConnection() throws DALException{
		
		String uri = Parametre.lire("dbUrl");
		String user = Parametre.lire("dbUser");
		String password = Parametre.lire("dbPassword");
		Connection connexion = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//DriverManager.registerDriver(new SQLServerDriver());
			connexion =  DriverManager.getConnection(uri, user, password);
		} catch (ClassNotFoundException e) {
			throw new DALException("Probleme chargement driver "+e.getMessage());
		} catch (SQLException e1){
			throw new DALException("Probleme connexion "+e1.getMessage());
		}
		return connexion;		
	}
	
	/**
	 * methode permettant de se deconnecter de la BDD
	 * @param stmt : le statement
	 * @param cnx : la connexion
	 * @throws DALException : propage une exception de type DALException
	 */
	public static void seDeconnecter(Statement stmt, Connection cnx) throws DALException{
		try {
			if (stmt!=null) stmt.close();
		} catch (SQLException e) {
			throw new DALException("Probleme de fermeture du statement",e);
		}

		try {
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
			throw new DALException("Probleme de fermeture de la connexion",e);
		}

	}

	/**
	 * methode permettant de se deconnecter de la BDD
	 * @param pstmt : le preparedStatement
	 * @param cnx : la connexion
	 * @throws DALException : propage une exception de type DALException
	 */
	public static void seDeconnecter(PreparedStatement pstmt, Connection cnx) throws DALException{
		try {
			if (pstmt!=null) pstmt.close();
		} catch (SQLException e) {
			throw new DALException("Probleme de fermeture du preparedStatement",e);
		}

		try {
			if (cnx!=null) cnx.close();
		} catch (SQLException e) {
			throw new DALException("Probleme de fermeture de la connexion",e);
		}

	}

}
