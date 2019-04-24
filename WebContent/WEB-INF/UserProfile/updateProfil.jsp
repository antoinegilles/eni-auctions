<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	request.setAttribute("title", "Profil");
%>

<%@include file="../../fragments/Head.jspf"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<c:if test="${requestScope['erreur'] != null }">
	<div class="error-box">
		<p><i class="fas fa-exclamation-circle"></i> ${requestScope['erreur'].toString()}</p>
	</div>
</c:if>


<form class="titled" action="${pageContext.request.contextPath }/updateProfilUser" method="get">
	<p class="title">Modifier le profil</p>
	<div class="divider">

		<table>
			<tr class="input-group">
				<td><label for="pseudo">Pseudo : </label></td>
				<td><input value="${sessionScope.UserConnecte.pseudo}" type="text" id="pseudo" name="pseudo"></td>
			</tr>
			<tr class="input-group">
				<td><label for="prenom">Prénom : </label>
				<td><input value="${sessionScope.UserConnecte.prenom}" type="text" id="prenom" name="prenom"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="telephone">Téléphone : </label></td>
				<td><input value="${sessionScope.UserConnecte.telephone}" type="text" id="telephone" name="telephone"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="codePostal">Code postal : </label></td>
				<td><input value="${sessionScope.UserConnecte.codePostal}" type="text" id="codePostal" name="codePostal"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="mdp">Nouveau mot de passe : </label></td>
				<td><input value="${sessionScope.UserConnecte.motDePasse}" type="password" id="mdp" name="mdp"></td>
			</tr>
		</table>
		<table>
			<tr class="input-group ">
				<td><label for="nom">Nom : </label></td>
				<td><input value="${sessionScope.UserConnecte.nom}" type="text" id="nom" name="nom"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="email">Mail : </label></td>
				<td><input value="${sessionScope.UserConnecte.email}" type="email" id="email" name="email"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="rue">Rue : </label></td>
				<td><input value="${sessionScope.UserConnecte.rue}" type="text" id="rue" name="rue"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="ville">Ville : </label></td>
				<td><input value="${sessionScope.UserConnecte.ville}" type="text" id="ville" name="ville"></td>
			</tr>
			<tr class="input-group" style="visibility: hidden">
				<td><label></label></td>
				<td><input value="" type="text" ></td>
			</tr>
		</table>
	</div>
	<div class="buttons">

		<button  type="submit" class="btn btn-dark">Modifier</button>
		<a href="${pageContext.request.contextPath }/suppression"><button type="button" class="red">Supprimer le compte</button></a>

	</div>
</form>



<%@include file="../../fragments/Bottom.jspf"%>
