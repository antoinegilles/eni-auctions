<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	request.setAttribute("title", "Profil");
%>

<%@include file="../../fragments/Head.jspf"%>

<%
	// rï¿½cuperer l'attribut animateur placï¿½s dans le contexte de session
	Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte");

%>
<%
	if(request.getAttribute("erreur") != null) { %>
	<div class="error-box">
		<p><i class="fas fa-exclamation-circle"></i> <%=request.getAttribute("erreur").toString() %></p>
	</div>
		Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte");
	
<% } %>

<form class="titled" action="<%=request.getContextPath() %>/updateProfilUser" method="get">
	<p class="title">Modifier le profil</p>
	<div class="divider">

		<table>
			<tr class="input-group">
				<td><label for="pseudo">Pseudo : </label></td>
				<td><input value="<%= a.getPseudo()%>" type="text" id="pseudo" name="pseudo"></td>
			</tr>
			<tr class="input-group">
				<td><label for="prenom">Prénom : </label>
				<td><input value="<%= a.getPrenom()%>" type="text" id="prenom" name="prenom"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="telephone">Téléphone : </label></td>
				<td><input value="<%= a.getTelephone() %>" type="text" id="telephone" name="telephone"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="codePostal">Code postal : </label></td>
				<td><input value="<%= a.getCodePostal() %>" type="text" id="codePostal" name="codePostal"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="mdp">Nouveau mot de passe : </label></td>
				<td><input value="<%= a.getMotDePasse()%>" type="password" id="mdp" name="mdp"></td>
			</tr>
		</table>
		<table>
			<tr class="input-group ">
				<td><label for="nom">Nom : </label></td>
				<td><input value="<%= a.getNom() %>" type="text" id="nom" name="nom"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="email">Mail : </label></td>
				<td><input value="<%= a.getEmail() %>" type="email" id="email" name="email"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="rue">Rue : </label></td>
				<td><input value="<%= a.getRue() %>" type="text" id="rue" name="rue"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="ville">Ville : </label></td>
				<td><input value="<%= a.getVille() %>" type="text" id="ville" name="ville"></td>
			</tr>
			<tr class="input-group" style="visibility: hidden">
				<td><label></label></td>
				<td><input value="" type="text" ></td>
			</tr>
		</table>
	</div>
	<div class="buttons">

		<button  type="submit" class="btn btn-dark">Modifier</button>
		<a href="<%=request.getContextPath() %>/suppression"><button type="button" class="red">Supprimer le compte</button></a>

	</div>
</form>



<%@include file="../../fragments/Bottom.jspf"%>
