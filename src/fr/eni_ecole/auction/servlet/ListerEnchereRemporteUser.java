package fr.eni_ecole.auction.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.auction.beans.ArticleVendu;
import fr.eni_ecole.auction.beans.Utilisateur;
import fr.eni_ecole.auction.bll.ArticleManager;
import fr.eni_ecole.auction.bll.UserManager;
import fr.eni_ecole.auction.dal.BusinessException;
import fr.eni_ecole.auction.dal.DALException;

/**
 * Servlet implementation class ListerEnchereRemporteUser
 */
@WebServlet("/ListerEnchereRemporteUser")
public class ListerEnchereRemporteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticleManager articleManager;
	private UserManager userManager;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
