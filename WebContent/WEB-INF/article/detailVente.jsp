<%@page import="java.util.Date"%>
<%@page import="com.sun.org.glassfish.gmbal.Description"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Détail de la Vente</title>
</head>
<body>
<h2>Détail vente</h2>


<form method="get" action="<%=request.getContextPath() %>/Encherir">
        <%ArticleVendu unArticle = new ArticleVendu("PC Gamer pour travailler","---------",new Date(1776-07-04),new Date(1776-07-04));%>
            
		<%=unArticle.getNomArticle() %><br> 
		Description : <%=unArticle.getDescription() %><br> 
		Catégorie : <br> 
		Meilleur offre : <br> 
		Mise à prix: <br> 
        Fin de l'enchère : <br> 
        Retrait : <br> 
        
        Vendeur : <br> 
        
        Ma proposition :  <br> 
        <br> 
       <button type="submit">Enchérir</button>
    </div>
</form>
          
</body>
</html>