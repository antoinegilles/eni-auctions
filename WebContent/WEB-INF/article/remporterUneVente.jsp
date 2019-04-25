<%@page import="java.util.Date"%>
<%@page import="com.sun.org.glassfish.gmbal.Description"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remporter une vente</title>
</head>
<body>
<h2>Remporter une vente</h2>
       
		Nom : ${detailArticle.nomArticle}<br>
		Description : ${detailArticle.description}<br> 
		Catégorie : ${detailArticle.categorie.libelle}<br> 
		Meilleur offre : <br> 
		Mise à prix: ${detailArticle.misAPrix} euros<br>
        Fin de l'enchère : ${detailArticle.dateFinEncheres}<br> <br> 
        Retrait : ${detailArticle.utilisateur.rue},<br>
        	${detailArticle.utilisateur.codePostal}<br> 
        
        Vendeur : ${detailArticle.utilisateur.pseudo}<br>
        <br>
       <a href="${pageContext.request.contextPath}/"><button type="submit">Retour à l'accueil</button></a>
          
</body>
</html>