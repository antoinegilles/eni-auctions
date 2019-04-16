package fr.eni_ecole.auction.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.eni_ecole.auction.dal.DALException;


/**
 * Classe gerant la connexion et deconnexion à la BDD via un pool de connexion
 * @author thierry
 *
 */
public class AccesBase {
	private static InitialContext jndi;
	private static DataSource ds;
	
	static {
		// Charger l'annuaire JNDI
		try {
			jndi = new InitialContext();
			// Chercher le pool de connexions dans l'annuaire
			ds=(DataSource) jndi.lookup("java:comp/env/jdbc/auction");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Methode permettant d'obtenir une connexion à la BDD
	 * @return un objet de type Connection
	 * @throws DALException : propage une exception de type DALException
	 */
	public static Connection getConnection() throws DALException {
		Connection cnx=null;
		// Activer une connexion du pool
		if (ds != null)
			try {
				cnx=ds.getConnection();
			} catch (SQLException e) {
				throw new DALException("Probleme connexion "+e.getMessage());
			}
		
		return cnx;
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
