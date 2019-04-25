<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
		 

<%
	request.setAttribute("title", "Connexion");
%>

<%@include file="../../fragments/Head.jspf"%>


<c:if test="${requestScope['erreur'] != null }">
	<div class="error-box">
		<p><i class="fas fa-exclamation-circle"></i> ${requestScope['erreur'].toString()}</p>
	</div>
</c:if>

<form action="<%=request.getContextPath() %>/Connexion" class="titled centered" method="post">
	<p class="title">se connecter</p>

	<table>
		<tr class="input-group">
			<td><label for="identifiant">Email :</label></td>
			<td><input type="email" class="form-control" id="identifiant" name="identifiant"></td>
		</tr>
		<tr class="input-group">
			<td><label for="motdepasse">Mot de passe :</label></td>
			<td><input type="password" class="form-control" id="motdepasse" name="motdepasse"></td>
		</tr>
	</table>

	<div class="buttons">
		<button type="submit" class="btn btn-default">se connecter</button>
		<a href="<%=request.getContextPath()%>/"><button type="button" class="btn btn-default">annuler</button></a>
	</div>
</form>


	
<%@include file="../../fragments/Bottom.jspf"%>