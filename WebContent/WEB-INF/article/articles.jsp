	<%@page import="fr.eni_ecole.auction.beans.Categorie"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page import ="java.util.*, java.text.*" %>


	<form method="post" action="<%=request.getContextPath() %>/ListerArticles">
	
		<%List<Categorie> listeCategories = (ArrayList<Categorie>) request.getAttribute("liste");%>
	
	Catégories :		
<span class="custom-dropdown custom-dropdown--white">
    <select class="custom-dropdown__select custom-dropdown__select--white" id="categorie" name="categorie">
  <% 	for(Categorie categorie : listeCategories) {
	%>
     <option value="<%=categorie.getNoCategorie() %>" ><%=categorie.getLibelle() %></option>
    <% } %>
     </select>	
  </span>
	
	
	
	<%List<ArticleVendu> listeArticlesVendus = (ArrayList<ArticleVendu>) request.getAttribute("listeArticlesVendus");%>
	Article :		
<span class="custom-dropdown custom-dropdown--white">
    <select class="custom-dropdown__select custom-dropdown__select--white" id="article" name="article">	
	<% for(ArticleVendu unarticle : listeArticlesVendus) {
		%>
		<option value="<%=unarticle.getNomArticle() %>" ><%=unarticle.getNomArticle() %></option>
	<% } %>
	</select>	
  </span>
  
  <button type="submit" class="btn btn-default">Enregistrer</button>
				</form>
