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

        <c:if test="${sessionScope.UserConnecte != null}">
             <div class="filter-type-choice">
                <div class="radio-checkbox-filters">
                <input type="radio" name="radio-choice" class="radio-choice" id="radio-achats-choice" value="achats" checked> <label for="radio-achats-choice">Achats</label>
                <div class="checkbox-list active" id="achat-checkbox">
                        <div>
                            <input type="checkbox" id="open" name="open" value="open">
                            <label for="achat-open">enchères ouvertes</label>
                        </div>
                        <div>
                            <input type="checkbox" id="ongoing" name="ongoing" value="ongoing">
                            <label for="achat-ongoing">mes enchères en cours</label>
                        </div>
                        <div>
                            <input type="checkbox" id="won" name="won" value="won">
                            <label for="achat-won">mes enchères remportées</label>
                        </div>
                    </div>
                </div>
                <div class="radio-checkbox-filters">
                <input type="radio" name="radio-choice" id="radio-ventes-choice" value="ventes"> <label for="radio-ventes-choice">Ventes</label>
                <div class="checkbox-list" id="vente-checkbox">
                        <div>
                            <input type="checkbox" id="sellsOngoing" name="sellsOngoing" value="ongoing">
                            <label for="sells-ongoing">mes ventes en cours</label>
                        </div>
                        <div>
                            <input type="checkbox" id="sellsOpen" name="sellsOpen" value="open">
                            <label for="sells-open">enchères non débutés</label>
                        </div>
                        <div>
                            <input type="checkbox" id="sellsWon" name="sellsWon" value="won">
                            <label for="sells-won">mes ventes terminées</label>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

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

    <c:forEach var="unarticle" items="${listeArticlesEncheresCours}">
        <article onclick="window.location.assign('DetailVente?id=${unarticle.noArticle}')">

        <c:choose>
            <c:when test="${unarticle.imagePath != null}">
                <img src="uploads?img=${unarticle.imagePath}" alt="image article">
            </c:when>
            <c:otherwise>
                <img src="theme/img/no-image.jpg" alt="image article">
            </c:otherwise>
        </c:choose>

		<div class="article-body">
			<p class="article-title"> Nom de l'aticle : ${unarticle.nomArticle} </p>
			<p class="article-seller">Vendeur : ${unarticle.utilisateur.pseudo}</p>
			<p> Prix : ${unarticle.misAPrix}</p>
			<p> Catégorie : ${unarticle.categorie.libelle}</p>
			<p> Début de l'enchere : ${unarticle.dateDebutEncheres}</p>
			<p> Fin de l'enchere : ${unarticle.dateFinEncheres}</p>
		</div>
	</article>
	</c:forEach>
</div>
<%@include file="fragments/Bottom.jspf"%>