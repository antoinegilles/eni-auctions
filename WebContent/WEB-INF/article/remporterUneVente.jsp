<%@page import="java.util.Date"%>
<%@page import="com.sun.org.glassfish.gmbal.Description"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remporter une vente</title>
</head>
<body>
<h2>Remporter une vente</h2>

 <%ArticleVendu unArticle = (ArticleVendu) request.getAttribute("detailArticle");  %>
          
       
		<%=unArticle.getNomArticle() %><br>
		 Description : <%=unArticle.getDescription() %><br> 
		Catégorie : <%=unArticle.getCategorie().getLibelle() %><br> 
		Meilleur offre : <br> 
		Mise à prix: <%=unArticle.getMisAPrix() %> euros<br>
        Fin de l'enchère : <%=unArticle.getDateFinEncheres() %><br> <br> 
        Retrait : <%=unArticle.getUtilisateur().getRue() %><br>
        	<%=unArticle.getUtilisateur().getCodePostal() %> <%=unArticle.getUtilisateur().getVille() %><br> 
        
        Vendeur : <%=unArticle.getUtilisateur().getPseudo() %> <br>
        <br> 
        <input value="<%=unArticle.getNoArticle() %>" type="text" id="numeroArticle" name="numeroArticle" style="visibility: hidden;">
       <button type="submit">Back</button>
    </div> --%>
          
</body>
</html>