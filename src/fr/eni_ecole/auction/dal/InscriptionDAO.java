package fr.eni_ecole.auction.dal;

import fr.eni_ecole.auction.beans.Utilisateur;

public interface InscriptionDAO {

	public  Utilisateur inscrire(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp, int Credit, boolean Administrateur) throws DALException;
}
