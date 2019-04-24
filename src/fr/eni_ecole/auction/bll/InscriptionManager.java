package fr.eni_ecole.auction.bll;


import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.DAOFactory;
import fr.eni_ecole.auction.dal.InscriptionDAO;

public class InscriptionManager {

	private InscriptionDAO inscriptionDAO;

	public InscriptionManager() {
		inscriptionDAO = DAOFactory.getInscriptionDAO();
	}
	
	
	public Utilisateur inscrire(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp, int Credit, boolean Administrateur) throws DALException{
		return inscriptionDAO.inscrire(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, Credit, Administrateur);
	}
}
