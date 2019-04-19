package fr.eni_ecole.auction.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.util.AccesBase;

public class CategorieDAOjdbcImpl implements CategorieDAO {
	
	private static final String LISTER="SELECT no_categorie, libelle FROM categories;";
	
	
	
	/**
	 * Methode permettant d'obtenir une liste des categories
	 * @return <font color="green">La liste peut être vide mais jamais <font color="red"><code>null</code></font></font>
	 * @throws DALException : propage une exception de type DALException
	 */
	public List<Categorie> listerLesCategories() throws DALException {
		Connection cnx=null;
		Statement stmt=null;
		ResultSet rs=null;
		List<Categorie> listeCategories = new ArrayList<Categorie>();

		cnx=AccesBase.getConnection();
		try{
			stmt=cnx.createStatement();			
			rs=stmt.executeQuery(LISTER);
			Categorie categorie;
			while (rs.next()){
				categorie = new Categorie(
									rs.getInt("no_categorie"),
									rs.getString("libelle"));
				listeCategories.add(categorie);
			}
		}catch (SQLException e){
			throw new DALException("probleme methode lister()",e);
		}finally{
			AccesBase.seDeconnecter(stmt, cnx);
		}
		
		return listeCategories;
	}

}
