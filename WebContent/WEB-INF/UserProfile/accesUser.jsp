<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	request.setAttribute("title", "Profil");
%>

<%@include file="../../fragments/Head.jspf"%>

<%
	if(request.getAttribute("erreur") != null) { %>
<div class="error-box">
	<p><i class="fas fa-exclamation-circle"></i> <%=request.getAttribute("erreur").toString() %></p>
</div>
<% } %>

<%
	// rï¿½cuperer l'attribut animateur placï¿½s dans le contexte de session
	Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte");
%>

<div class="centered titled infos">
	<p class="title">Votre Profil</p>

	<table>
		<tr>
			<td><b>Prenom :</b></td>
			<td><%= a.getPrenom()%></td>
		</tr>
		<tr>
			<td><b>Nom :</b></td>
			<td><%= a.getNom()%></td>
		</tr>
		<tr>
			<td><b>Pseudo :</b></td>
			<td><%= a.getPseudo()%></td>
		</tr>
		<tr>
			<td><b>Email :</b></td>
			<td><%= a.getEmail()%></td>
		</tr>
		<tr>
			<td><b>Telephone :</b></td>
			<td><%= a.getTelephone()%></td>
		</tr>
		<tr>
			<td><b>Rue :</b></td>
			<td><%= a.getRue()%></td>
		</tr>
		<tr>
			<td><b>Code Postal :</b></td>
			<td><%= a.getCodePostal()%></td>
		</tr>
		<tr>
			<td><b>Ville :</b></td>
			<td><%= a.getVille()%></td>
		</tr>
	</table>
	<div class="buttons">
		<a href="<%=request.getContextPath()%>/updateProfilUser"><button>Modifier</button></a>
	</div>
</div>

<%@include file="../../fragments/Bottom.jspf"%>
