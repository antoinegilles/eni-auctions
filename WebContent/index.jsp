<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>

<%@page import="fr.eni_ecole.auction.beans.Categorie"%>
<%@ page import="java.util.*, java.text.*"%>

<%
    request.setAttribute("title", "Liste des enchères");
%>

<%@include file="fragments/Head.jspf"%>
<form class="horizontal colorized sticky titled" method="get"
	action="${pageContext.request.contextPath}/">
	<div class="search-filters">
		<div class="input-group search">
			<div>
				<i class="fas fa-search search-icon"></i> <input type="text"
					id="article" name="article"
					placeholder="Le nom de l'article contient">
			</div>
		</div>
		<div class="input-group">
			<label for="categorie">Catégorie :</label> <select
				placeholder="Le nom de l'article contient" id="categorie"
				name="categorie">
				<option value="">TOUTES</option>
				<c:forEach var="categorie" items="${liste}">
					<option value="${categorie.noCategorie}">${categorie.libelle}</option>
				</c:forEach>
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
                <input type="radio" name="radio-choice" id="radio-sells-choice"> <label for="radio-sells-choice">Ventes</label>
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

    <c:forEach var="unarticle" items="${listeArticlesEncheresCours}">
        <article onclick="window.location.assign('DetailVente?id=${unarticle.noArticle}')">
        
        <c:choose>
            <c:when test="unarticle.getImagePath() != null">
                <img src="uploads?img=${unarticle.getImagePath()}" alt="image article">
            </c:when>
            <c:otherwise>
                <img src="theme/img/no-image.jpg" alt="image article">
            </c:otherwise>
        </c:choose>

		<div class="article-body">
			<p class="article-title"> Nom de l'aticle : ${unarticle.nomArticle} </p>
			<p class="article-seller">Vendeur : Jojo45</p>
			<p> Prix : ${unarticle.misAPrix}</p>
			<p> Catégorie : ${unarticle.categorie.libelle}</p>
			<p> Début de l'enchere : ${unarticle.dateDebutEncheres}</p>
			<p> Fin de l'enchere : ${unarticle.dateFinEncheres}</p>
		</div>
	</article>
	</c:forEach>
</div>
<%@include file="fragments/Bottom.jspf"%>