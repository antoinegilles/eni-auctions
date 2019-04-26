<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<%
    request.setAttribute("title", "Détail vente");
%>

<%@include file="../../fragments/Head.jspf"%>

<c:if test="${requestScope['erreur'] != null }">
	<div class="error-box">
		<p><i class="fas fa-exclamation-circle"></i> ${requestScope['erreur'].toString()}</p>
	</div>
</c:if>


<%
	ArticleVendu unArticle = (ArticleVendu) request.getAttribute("detailArticle");
	int minPrice = (int) request.getAttribute("minPrice");
  SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEEE dd MMMMM yyyy", Locale.FRANCE);
%>

<div class="divider">
    <div class="shop-view detail-image">
        <article>
            <div class="preview-img" id="preview-img">
                <img src="<%=request.getAttribute("image")%>">
            </div>
        </article>
    </div>

    <form  class="titled" method="post" action="${pageContext.request.contextPath}/Encherir">
        <p class="title">Article</p>
        <table>
            <tr>
                <td><b>Nom :</b></td>
                <td>${detailArticle.nomArticle}</td>
            </tr>
            <tr>
                <td><b>Description :</b></td>
                <td>${detailArticle.description}</td>
            </tr>
            <tr>
                <td><b>Catégorie :</b></td>
                <td>${detailArticle.categorie.libelle}</td>
            </tr>
            <tr>
                <td><b>Meilleur offre :</b></td>
                <td>${detailArticle.prixVente}</td>
            </tr>
            <tr>
                <td><b>Mise à prix :</b></td>
                <td>${detailArticle.misAPrix}</td>
            </tr>
            <tr>
                <td><b>Fin de l'enchère :</b></td>
                <td><%=dateFormatter.format(unArticle.getDateFinEncheres()) %></td>
            </tr>
            <tr>
                <td><b>Retrait :</b></td>
                <td>${detailArticle.utilisateur.rue},
                    ${detailArticle.utilisateur.codePostal} ${detailArticle.utilisateur.ville}</td>
            </tr>
            <tr>
                <td><b>Vendeur :</b></td>
                <td>${detailArticle.utilisateur.pseudo}</td>
            </tr>
        </table>
        <c:if test="${sessionScope.UserConnecte.pseudo !=  detailArticle.utilisateur.pseudo }">
        <p class="title">Ma proposition</p>
        <table>
			<tr>
                <td><input type="number" id="proposition" name="proposition" min="${minPrice}" value="${minPrice}" required></td>
                <td><button type="submit">Enchérir</button></td>
            </tr>
        </table>
		</c:if>
            
                        <input value="${detailArticle.noArticle}" type="text" id="numeroArticle" name="numeroArticle" style="visibility: hidden;">
    </form>
</div>

<%@include file="../../fragments/Bottom.jspf"%>