	<%@page import="fr.eni_ecole.auction.beans.Categorie"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page import ="java.util.*, java.text.*" %>

<body>
<h1>Confirmation ajoutée</h1>
	
	<%List<ArticleVendu> listeArticlesEncheresCours = (ArrayList<ArticleVendu>) request.getAttribute("listeArticlesEncheresCours");%>
	Article :	
	<% for(ArticleVendu unarticle : listeArticlesEncheresCours) {
		%>
		<%=unarticle.getNomArticle() %>
	<% } %>



</body>
</html>


	
		
