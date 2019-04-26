package fr.eni_ecole.auction.dal;

public abstract class DAOFactory {
	
	private static ArticleDAO articleDAO;
	private static CategorieDAO categorieDAO;
	private static UserDAO userDAO;
	private static InscriptionDAO inscriptionDAO;
	
	public static ArticleDAO getArticleDAO()
	{
		if(articleDAO == null) {
		articleDAO =  new ArticleDAOjdbcImpl();
		}
		return articleDAO;
	}
	
	public static CategorieDAO getCategorieDAO()
	{
		if(categorieDAO == null) {
			categorieDAO =  new CategorieDAOjdbcImpl();
		}
		return categorieDAO;
	}
	
	public static UserDAO getUserDAO()
	{
		if(userDAO == null) {
			userDAO =  new UserDAOjdbclmpl();
		}
		return userDAO;
	}
	public static InscriptionDAO getInscriptionDAO()
	{
		if(inscriptionDAO == null) {
			inscriptionDAO =  new InscriptionDAOjdbclmpl();
		}
		return inscriptionDAO;
	}
	
}
	