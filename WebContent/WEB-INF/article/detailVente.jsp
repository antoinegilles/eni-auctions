<%@page import="java.util.Date"%>
<%@page import="com.sun.org.glassfish.gmbal.Description"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>D�tail de la Vente</title>
</head>
<body>
<h2>D�tail vente</h2>


<form method="get" action="<%=request.getContextPath() %>/Encherir">
       
         <%ArticleVendu unArticle = (ArticleVendu) request.getAttribute("detailArticle");  %>
            
		<%=unArticle.getNomArticle() %><br> 
		Description : <%=unArticle.getDescription() %><br> 
		Cat�gorie : <%=unArticle.getCategorie().getLibelle() %><br> 
		Meilleur offre : <br> 
		Mise � prix: <%=unArticle.getPrixVente() %><br> <br> 
        Fin de l'ench�re : <%=unArticle.getDateFinEncheres() %><br> <br> 
        Retrait : <%=unArticle.getUtilisateur().getRue() %><br>
        	<%=unArticle.getUtilisateur().getCodePostal() %> <%=unArticle.getUtilisateur().getVille() %><br> 
        
        Vendeur : <%=unArticle.getUtilisateur().getPseudo() %> <br>
        
        Ma proposition : <input type="text" id="proposition" name="proposition"></div> <br> 
        <br> 
       <button type="submit">Ench�rir</button>
    </div>
</form>
          
</body>
</html>