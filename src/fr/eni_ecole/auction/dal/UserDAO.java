package fr.eni_ecole.auction.dal;

import fr.eni_ecole.auction.beans.Utilisateur;

public interface UserDAO {

	public 	Utilisateur selectUser( String email, String password) throws DALException;
	public  Utilisateur selectPseudo( String pseudo) throws DALException;
	public  Utilisateur selectPrenom( String prenom) throws DALException;
	public  Utilisateur updateUser(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp) throws DALException;
	public Utilisateur deleteUser(String pseudo, String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String mdp,int credit)  throws DALException ;


}
