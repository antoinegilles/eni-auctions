
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

<%
    // récuperer l'attribut animateur placés dans le contexte de session
    Utilisateur uc = (Utilisateur) request.getSession().getAttribute("UserConnecte");
    ArticleVendu article = (ArticleVendu) request.getAttribute("detailArticle");


    if (uc != article.get) {
%>
<h2>Remporter une vente</h2>
          
       
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


<%@include file="../../fragments/Bottom.jspf"%>