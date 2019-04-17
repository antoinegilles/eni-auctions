<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    request.setAttribute("title", "Liste des enchères");
    String[] searchCategories = {"Test", "gandalf", "420"};
%>
<%@include file="fragments/Head.jspf" %>

<form>
    <div class="search-filters">
        <div class="input-group search">
            <label for="search-filter">Filtres :</label>
            <div>
            <i class="fas fa-search search-icon"></i>
            <input type="text" id="search-filter" placeholder="Le nom de l'article contient" ></div>
        </div>
        <div class="input-group">
            <label for="category-filter">Catégorie :</label>
            <select id="category-filter" placeholder="Le nom de l'article contient" >
                <option value="">TOUTES</option>
                <% for (String category : searchCategories) { %>
                    <option value="<%=category %>"><%=category %></option>
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
    <% for (int i = 0; i < 5; i++) {%>
    <article>
        <img src="https://upload.wikimedia.org/wikipedia/commons/b/b9/Llama_lying_down.jpg"
             alt="image article">

        <div class="article-body">
            <p class="article-title">Pc pour Gamer pour travailler</p>
            <p class="article-seller">Vendeur : Jojo45</p>
            <p>Prix : 30 euros</p>
            <p>Fin de l'enchere : 10/25/6354</p>
        </div>
    </article>
    <% } %>
</div>
<%@include file="fragments/Bottom.jspf"%>











