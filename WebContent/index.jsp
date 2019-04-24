<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>

<%@page import="fr.eni_ecole.auction.beans.Categorie"%>
<%@ page import ="java.util.*, java.text.*" %>
<%
    request.setAttribute("title", "Liste des enchères");
%>
<%@include file="fragments/Head.jspf" %>

<% boolean isConnected = request.getSession().getAttribute("UserConnecte") != null; %>
<form class="horizontal colorized sticky titled"  method="get" action="<%=request.getContextPath() %>/">
    <div class="search-filters">
        <div class="input-group search">
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

        <% if (isConnected) { %>
        <div class="filter-type-choice">
            <div class="radio-checkbox-filters">
                <input type="radio" name="radio-choice" class="radio-choice" id="radio-achats-choice" value="achats" checked> <label for="radio-achats-choice">Achats</label>
                <div class="checkbox-list active" id="achat-checkbox">
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
                <input type="radio" name="radio-choice" id="radio-ventes-choice" value="ventes"> <label for="radio-ventes-choice">Ventes</label>
                <div class="checkbox-list" id="vente-checkbox">
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
        <% } %>
    </div>
    <div class="submit-search">
        <button type="submit">Rechercher</button>
    </div>
</form>

<script>
    var achatChoices = document.querySelector("#radio-achats-choice");
    var achatsCheckbox = document.querySelector("#achat-checkbox");
    var ventesChoices = document.querySelector("#radio-ventes-choice");
    var ventesCheckbox = document.querySelector("#vente-checkbox");

    achatChoices.addEventListener('change', function (event) {
        achatsCheckbox.classList.add("active");
        ventesCheckbox.classList.remove("active");
        ventesCheckbox.childNodes.forEach(function (value) {
            value.childNodes.forEach(function (input) {
                if(input instanceof HTMLInputElement) {
                    input.checked = false;
                }
            })
        })
    })

    ventesChoices.addEventListener('change', function (event) {
        ventesCheckbox.classList.add("active");
        achatsCheckbox.classList.remove("active");
        achatsCheckbox.childNodes.forEach(function (value) {
            value.childNodes.forEach(function (input) {
                if(input instanceof HTMLInputElement) {
                    input.checked = false;
                }
            })
        })
    })
</script>

<div class="shop-view">

    <%List<ArticleVendu> listeArticlesEncheresCours = (ArrayList<ArticleVendu>) request.getAttribute("listeArticlesEncheresCours");

    for(ArticleVendu unarticle : listeArticlesEncheresCours) {
    String cardLink = (isConnected) ? "DetailVente?id=" + unarticle.getNoArticle() : "connexion";
    %>
        <article onclick="window.location.assign('<%=cardLink %>')">
            <% String img = "theme/img/no-image.jpg";
                if(unarticle.getImagePath() != null && !unarticle.getImagePath().equals("")) {
                    img = "uploads?img=" + unarticle.getImagePath();
                } %>
                <img src="<%=img%>" alt="image article">

            <div class="article-body">
                <p class="article-title">Nom de l'aticle : <%=unarticle.getNomArticle() %></p>
                <p class="article-seller">Vendeur : Jojo45</p>
                <p>Prix : <%=unarticle.getMisAPrix() %></p>
                <p>Début de l'enchere : <%=unarticle.getDateDebutEncheres() %></p>
                <p>Fin de l'enchere : <%=unarticle.getDateFinEncheres() %></p>
            </div>
        </article>
	<% } %>
</div>
<%@include file="fragments/Bottom.jspf"%>