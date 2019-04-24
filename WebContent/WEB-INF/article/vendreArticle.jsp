<%@ page import ="java.util.*, java.text.*" %>
<%@ page import="fr.eni_ecole.auction.beans.Categorie"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%
	request.setAttribute("title", "Nouvelle vente");
%>

<%@include file="../../fragments/Head.jspf"%>

<script>
	function updatePreview() {
		var preview  = document.querySelector('#preview-img');
		var input  = document.querySelector('#photoArticle');

		while (preview.firstChild) {
			preview.removeChild(preview.firstChild)
		}

		if (input.files.length === 0) {
			var noImg = document.createElement('img');
			noImg.src = "theme/img/no-image.jpg";
			preview.appendChild(noImg);
		} else {
			var picture = document.createElement('img');
			picture.src = window.URL.createObjectURL(input.files[0]);
			preview.appendChild(picture);
		}
	}
</script>

<div class="divider">
	<div class="shop-view detail-image">
		<article>
			<div class="preview-img" id="preview-img">
				<img src="theme/img/no-image.jpg">
			</div>

			<div class="article-body">
				<p class="article-title"><div class="false-info" style="width: 91%"></div></p>
				<p class="article-seller"><div class="false-info" style="width: 22%"></div></p>
				<p><div class="false-info" style="width: 44%"></div></p>
				<p><div class="false-info" style="width: 63%"></div></p>
			</div>
		</article>
	</div>

	<form method="post" class="titled" action="<%=request.getContextPath() %>/VendreArticle" enctype="multipart/form-data">
		<div class="title">
			<p>Informations</p>
		</div>
		<table>
			<tr class="input-group">
				<td><label for="nomArticle">Article :</label></td>
				<td><input type="text" id="nomArticle" name="nomArticle" required></td>
			</tr>


			<tr class="input-group">
				<td><label for="description">Description :</label></td>
				<td><textarea type="text" id="description" name="description" rows="5" style="width: 100%;" required></textarea></td>
			</tr>

			<tr class="input-group">
				<td><label for="categorie"> Catégories : </label></td>
				<td><select class="custom-dropdown__select custom-dropdown__select--white" id="categorie" name="categorie">
					<% 	List<Categorie> listeCategories = (ArrayList<Categorie>) request.getAttribute("liste");
						for(Categorie categorie : listeCategories) { %>
					<option value="<%=categorie.getNoCategorie() %>" ><%=categorie.getLibelle() %></option>
					<% } %>
				</select></td>
			</tr>

			<tr class="input-group">
				<td><label for="photoArticle">Photo de l'article :</label></td>
				<td class="uploadFile">
					<input type="file" id="photoArticle" name="photoArticle"
						   accept="image/png, image/jpeg" onchange="updatePreview()">
					<label for="photoArticle"><i class="fas fa-cloud-upload-alt"></i> Uploader</label>
				</td>
			</tr>

			<tr class="input-group">
				<td><label for="misePrix">Mise à prix :</label></td>
				<td><input type="text" id="misePrix" name="misePrix" required></td>
			</tr>

			<tr class="input-group">
				<td><label for="debutEnchere">Début de l'enchère :</label></td>
				<td><input type="date" id="debutEnchere" name="debutEnchere" required></td>
			</tr>

			<tr class="input-group">
				<td><label for="finEnchere">Fin de l'enchère :</label></td>
				<td><input type="date" id="finEnchere" name="finEnchere" required></td>
			</tr>

		</table>


		<div class="buttons">
			<button type="submit">Enregistrer</button>
			<a href="<%=request.getContextPath()%>/"><button type="button">Annuler</button></a>
		</div>
	</form>
</div>

<%@include file="../../fragments/Bottom.jspf"%>
