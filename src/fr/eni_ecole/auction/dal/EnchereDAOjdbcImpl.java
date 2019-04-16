package fr.eni_ecole.auction.dal;

/**
 * Reference toutes les methodes <strong>CRUD</strong> du metier Enchere
 * <ul><li>lister : <em>lister toutes les encheres</em></li>
 * <li>rechercher : <em>rechercher une enchere par son identifiant</em></li>
 * <li>ajouter : <em>ajouter une enchere</em></li>
 * <li>modifier : <em>modifier une enchere</em></li>
 * <li>supprimer : <em>supprimer une enchere</em></li></ul>
 * @author moujdari2018
 * @version 1.0
 *
 */

public class EnchereDAOjdbcImpl {
	
	private static final String LISTER="SELECT * FROM formations;";
	private static final String RECHERCHER="SELECT * FROM formations WHERE id = ?;";
	private static final String AJOUTER="INSERT INTO formations (libelle, debut, fin, description) VALUES (?,?,?,?);";
	private static final String MODIFIER="UPDATE formations SET libelle = ?, debut = ?, fin = ?, description = ? WHERE id = ?;";
	private static final String SUPPRIMER="DELETE FROM formations WHERE id = ?;";

}
