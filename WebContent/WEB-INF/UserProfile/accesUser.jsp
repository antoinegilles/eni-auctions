<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		 

<%
	request.setAttribute("title", "Profil");
%>

<%@include file="../../fragments/Head.jspf"%>

<c:if test="${requestScope['erreur'] != null }">
	<div class="error-box">
		<p><i class="fas fa-exclamation-circle"></i> ${requestScope['erreur'].toString()}</p>
	</div>
</c:if>


<div class="centered titled infos">
	<p class="title">Votre Profil</p>

	<table>
		<tr>
			<td><b>Prenom :</b></td>
			<td>${sessionScope.UserConnecte.prenom}</td>
		</tr>
		<tr>
			<td><b>Nom :</b></td>
			<td>${sessionScope.UserConnecte.nom}</td>
		</tr>
		<tr>
			<td><b>Pseudo :</b></td>
			<td>${sessionScope.UserConnecte.pseudo}</td>
		</tr>
		<tr>
			<td><b>Email :</b></td>
			<td>${sessionScope.UserConnecte.email}</td>
		</tr>
		<tr>
			<td><b>Telephone :</b></td>
			<td>${sessionScope.UserConnecte.telephone}</td>
		</tr>
		<tr>
			<td><b>Rue :</b></td>
			<td>${sessionScope.UserConnecte.rue}</td>
		</tr>
		<tr>
			<td><b>Code Postal :</b></td>
			<td>${sessionScope.UserConnecte.codePostal}</td>
		</tr>
		<tr>
			<td><b>Ville :</b></td>
			<td>${sessionScope.UserConnecte.ville}</td>
		</tr>
	</table>
	<div class="buttons">
		<a href="${pageContext.request.contextPath }/updateProfil"><button>Modifier</button></a>
	</div>
</div>

<%@include file="../../fragments/Bottom.jspf"%>
