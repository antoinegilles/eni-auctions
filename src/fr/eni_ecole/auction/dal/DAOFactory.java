package fr.eni_ecole.auction.dal;

public abstract class DAOFactory {
	
	private static ArticleDAOjdbcImpl articleDAO;
	private static CategorieDAOjdbcImpl categorieDAO;
	private static UserDAOjdbclmpl userDAO;
	private static InscriptionDAOjdbclmpl inscriptionDAO;
	
	public static ArticleDAOjdbcImpl getArticleDAO()
	{
		if(articleDAO == null) {
		articleDAO =  new ArticleDAOjdbcImpl();
		}
		return articleDAO;
	}
	
	public static CategorieDAOjdbcImpl getCategorieDAO()
	{
		if(categorieDAO == null) {
			categorieDAO =  new CategorieDAOjdbcImpl();
		}
		return categorieDAO;
	}
	
	public static UserDAOjdbclmpl getUserDAO()
	{
		if(userDAO == null) {
			userDAO =  new UserDAOjdbclmpl();
		}
		return userDAO;
	}
	public static InscriptionDAOjdbclmpl getInscriptionDAO()
	{
		if(inscriptionDAO == null) {
			inscriptionDAO =  new InscriptionDAOjdbclmpl();
		}
		return inscriptionDAO;
	}
}
	