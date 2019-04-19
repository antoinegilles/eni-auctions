package fr.eni_ecole.auction.dal;

import java.util.List;

import fr.eni_ecole.auction.beans.Categorie;

public interface CategorieDAO {
	public List<Categorie> listerLesCategories() throws DALException;
}
