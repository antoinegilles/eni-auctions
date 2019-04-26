
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

<div class="divider">
    <div class="shop-view detail-image">
        <article>
            <div class="preview-img" id="preview-img">
                <c:choose>
                    <c:when test="${detailArticle.getImagePath() != null}">
                        <img src="uploads?img=${detailArticle.getImagePath()}" alt="image article">
                    </c:when>
                    <c:otherwise>
                        <img src="theme/img/no-image.jpg" alt="image article">
                    </c:otherwise>
                </c:choose>
            </div>
        </article>
    </div>
    <div class="infos titled">
        <p class="title">Remporter une vente</p>
        <table>

            <tr>
                <td><p>Nom :</p></td>
                <td><p>${detailArticle.nomArticle}</p></td>
            </tr>

            <tr>
                <td><p>Description :</p></td>
                <td><p>${detailArticle.description}</p></td>
            </tr>

            <tr>
                <td><p>Catégorie : </p></td>
                <td><p>${detailArticle.categorie.libelle}</p></td>
            </tr>

            <tr>
                <td><p>Meilleur offre :</p></td>
                <td><p>${detailArticle.prixVente} euros</p></td>
            </tr>

            <tr>
                <td><p>Mise à prix:</p></td>
                <td><p>${detailArticle.misAPrix} euros</p></td>
            </tr>

            <tr>
                <td><p>Fin de l'enchère :</p></td>
                <td><p>${detailArticle.dateFinEncheres}</p></td>
            </tr>

            <tr>
                <td><p>Retrait : </p></td>
                <td><p>${detailArticle.utilisateur.rue},</p></td>
                <td><p>${detailArticle.utilisateur.codePostal} ${detailArticle.utilisateur.ville}</p></td>
            </tr>

            <tr>
                <td><p>Vendeur :</p></td>
                <td><p>${detailArticle.utilisateur.pseudo}</p></td>
            </tr>
        </table>

        <div class="buttons">
            <a href="${pageContext.request.contextPath}/"><button type="submit">Retour à l'accueil</button></a>
        </div>

    </div>
</div>

<%@include file="../../fragments/Bottom.jspf"%>