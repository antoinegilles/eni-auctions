package fr.eni_ecole.auction.bll;

import java.util.List;

import fr.eni_ecole.auction.beans.Categorie;
import fr.eni_ecole.auction.dal.CategorieDAO;
import fr.eni_ecole.auction.dal.DALException;
import fr.eni_ecole.auction.dal.DAOFactory;

public class CategorieManager {
	private CategorieDAO categorieDAO;
	
	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();
	}
	
	public List<Categorie> listerLesCategories() throws DALException
	{
		return categorieDAO.listerLesCategories();
	}
	
	
	
}
