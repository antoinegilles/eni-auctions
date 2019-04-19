package fr.eni_ecole.auction.dal;

public interface InscriptionDAO {

	public  void inscrire(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp, int Credit, boolean Administrateur) throws DALException;
}
