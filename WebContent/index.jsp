<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="fr.eni_ecole.auction.beans.Categorie"%>
<%@ page import ="java.util.*, java.text.*" %>
<%
    request.setAttribute("title", "Liste des enchères");
    String[] searchCategories = {"Test", "gandalf", "420"};
%>
<%@include file="fragments/Head.jspf" %>

<form method="get" action="<%=request.getContextPath() %>/">
    <div class="search-filters">
        <div class="input-group search">
            <label for="search-filter">Filtres :</label>
            <div>
            <i class="fas fa-search search-icon"></i>
            <input type="text" id="article" name="article" placeholder="Le nom de l'article contient" ></div>
        </div>
        <div class="input-group">
        <%List<Categorie> listeCategories = (ArrayList<Categorie>) request.getAttribute("liste");%>
            <label for="categorie">Catégorie :</label>
            <select placeholder="Le nom de l'article contient" id="categorie" name="categorie">
                <option value="">TOUTES</option>
                <% for (Categorie categorie : listeCategories) { %>
                    <option value="<%=categorie.getNoCategorie() %>" ><%=categorie.getLibelle() %></option>
                <% } %>
            </select>
        </div>

        <div class="filter-type-choice">
            <div class="radio-checkbox-filters">
                <input type="radio" name="radio-choice" id="radio-achats-choice"> <label for="radio-achats-choice">Achats</label>
                <div class="checkbox-list">
                    <div>
                        <input type="checkbox" id="achat-open" name="achats" value="open">
                        <label for="achat-open">enchères ouvertes</label>
                    </div>
                    <div>
                        <input type="checkbox" id="achat-ongoing" name="achats" value="ongoing">
                        <label for="achat-ongoing">mes enchères en cours</label>
                    </div>
                    <div>
                        <input type="checkbox" id="achat-won" name="achats" value="won">
                        <label for="achat-won">mes enchères remportées</label>
                    </div>
                </div>
            </div>
            <div class="radio-checkbox-filters">
                <input type="radio" name="radio-choice" id="radio-sells-choice"> <label for="radio-sells-choice">Achats</label>
                <div class="checkbox-list">
                    <div>
                        <input type="checkbox" id="sells-ongoing" name="ventes" value="ongoing">
                        <label for="sells-ongoing">mes ventes en cours</label>
                    </div>
                    <div>
                        <input type="checkbox" id="sells-open" name="ventes" value="waiting">
                        <label for="sells-open">enchères non débutés</label>
                    </div>
                    <div>
                        <input type="checkbox" id="sells-won" name="ventes" value="finished">
                        <label for="sells-won">mes ventes terminées</label>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="submit-search">
        <button type="submit">Rechercher</button>
    </div>
</form>


<div class="shop-view">
    <%List<ArticleVendu> listeArticlesEncheresCours = (ArrayList<ArticleVendu>) request.getAttribute("listeArticlesEncheresCours");%>
	<h2>Les Enchères en cours :</h2>	
	
	<% for(ArticleVendu unarticle : listeArticlesEncheresCours) { %>
    
    <form method="get" action="<%=request.getContextPath() %>/DetailVente">
    <article>
        <!--  <img src="https://upload.wikimedia.org/wikipedia/commons/b/b9/Llama_lying_down.jpg"
             alt="image article">-->

        <div class="article-body">
            <button type="submit" id="detailVente" name="detailVente">Nom de l'aticle : <%=unarticle.getNomArticle() %></button>
            <p class="article-seller">Vendeur : Jojo45</p>
            <p>Prix : <%=unarticle.getMisAPrix() %></p>
            <p>Début de l'enchere : <%=unarticle.getDateDebutEncheres() %></p>
            <p>Fin de l'enchere : <%=unarticle.getDateFinEncheres() %></p>
        </div>
    </article>
    	</form>
	<% } %>
</div>
<%@include file="fragments/Bottom.jspf"%>











