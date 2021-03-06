package fr.eni_ecole.auction.bll;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.DAOFactory;
import fr.eni_ecole.auction.dal.UserDAO;

public class UserManager {

private UserDAO userDAO;
	
	public UserManager() {
		userDAO = DAOFactory.getUserDAO();
	}
	
	public 	Utilisateur selectUser( String email, String password) throws DALException{
		return userDAO.selectUser(email, password);

	};
	public  Utilisateur selectPseudo( String pseudo) throws DALException{
		return userDAO.selectPseudo(pseudo);
	};
	public  Utilisateur selectPrenom( String prenom) throws DALException{
		return userDAO.selectPrenom(prenom);
	};
	public Utilisateur updateUser(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String mdp) throws DALException{
		return userDAO.updateUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp);
	};

	public Utilisateur deleteUser(String pseudo, String nom,String prenom,String email,String telephone,String rue,String codePostal,String ville,String mdp,int credit)throws DALException {
		return userDAO.deleteUser(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, mdp, credit)  ;

	};

	public void updateUserCredit(Utilisateur unUtilisateur) throws DALException{
		userDAO.updateUserCredit(unUtilisateur);
	};
	
	public void updateUserDebit(ArticleVendu detailArticleEncheri) throws DALException{
		userDAO.updateUserDebit(detailArticleEncheri);
		
	};


}
