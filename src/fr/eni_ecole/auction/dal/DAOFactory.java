package fr.eni_ecole.auction.dal;

public abstract class DAOFactory {
	
	private static ArticleDAOjdbcImpl articleDAO;
	private static CategorieDAOjdbcImpl categorieDAO;
	
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
}
	