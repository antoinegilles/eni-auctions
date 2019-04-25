
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page import="fr.eni_ecole.auction.beans.Enchere" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    request.setAttribute("title", "Détail vente");
%>

<%@include file="../../fragments/Head.jspf"%>

<%
    if(request.getAttribute("erreur") != null) { %>
<div class="error-box">
    <p><i class="fas fa-exclamation-circle"></i> <%=request.getAttribute("erreur").toString() %></p>
</div>
<% } %>

<h2>Remporter une vente</h2>

    Nom : ${detailArticle.nomArticle}<br>
    Description : ${detailArticle.description}<br>
    Cat�gorie : ${detailArticle.categorie.libelle}<br>
    Meilleur offre : <br>
    Mise � prix: ${detailArticle.misAPrix} euros<br>
    Fin de l'ench�re : ${detailArticle.dateFinEncheres}<br> <br>
    Retrait : ${detailArticle.utilisateur.rue},<br>
    ${detailArticle.utilisateur.codePostal}<br>

    Vendeur : ${detailArticle.utilisateur.pseudo}<br>
    <br>
    <a href="${pageContext.request.contextPath}/"><button type="submit">Retour � l'accueil</button></a>



<%@include file="../../fragments/Bottom.jspf"%>