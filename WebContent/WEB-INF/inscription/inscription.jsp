<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	request.setAttribute("title", "Inscription");
%>

<%@include file="../../fragments/Head.jspf"%>

<%
	if(request.getAttribute("erreur") != null) { %>
	<div class="error-box">
		<p><i class="fas fa-exclamation-circle"></i> <%=request.getAttribute("erreur").toString() %></p>
	</div>
<% } %>

<form class="titled" action="<%=request.getContextPath() %>/Inscription" method="post">
	<p class="title">S'inscrire</p>
	<div class="divider">

		<table>
			<tr class="input-group">
				<td><label for="pseudo">Pseudo : </label></td>
				<td><input type="text" id="pseudo" name="pseudo"></td>
			</tr>
			<tr class="input-group">
				<td><label for="prenom">Prénom : </label>
				<td><input type="text" id="prenom" name="prenom"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="telephone">Téléphone : </label></td>
				<td><input type="text" id="telephone" name="telephone"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="codePostal">Code postal : </label></td>
				<td><input type="text" id="codePostal" name="codePostal"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="mdp">Mot de passe : </label></td>
				<td><input type="password" id="mdp" name="mdp"></td>
			</tr>
		</table>
		<table>
			<tr class="input-group ">
				<td><label for="nom">Nom : </label></td>
				<td><input type="text" id="nom" name="nom"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="email">Mail : </label></td>
				<td><input type="email" id="email" name="email"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="rue">Rue : </label></td>
				<td><input type="text" id="rue" name="rue"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="ville">Ville : </label></td>
				<td><input type="text" id="ville" name="ville"></td>
			</tr>
			<tr class="input-group ">
				<td><label for="confirmation">Confirmation : </label></td>
				<td><input type="password" id="confirmation" name="confirmation"></td>
			</tr>
		</table>
	</div>
	<div class="buttons">

		<button  type="submit" class="btn btn-dark">Créer</button>
		<a href="<%=request.getContextPath() %>/"><button type="button" class="btn btn-dark">Annuler</button></a>

	</div>
</form>



<%@include file="../../fragments/Bottom.jspf"%>