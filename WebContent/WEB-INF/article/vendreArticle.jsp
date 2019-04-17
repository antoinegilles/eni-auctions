<%@ page import ="java.util.*, java.text.*" %>
<%@page import="fr.eni_ecole.auction.beans.Categorie"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI-Encheres</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
</head>

<body>
<div class="jumbotron">
	<div class="container">
	  <div class="row">
	    <div class="col-sm">
	ENI-Encheres
	    </div>
	    <div class="col-sm">
	    </div>
	    <div class="col-sm">
	    </div>
	     <div class="col-sm">
	    </div>
	    <div class="col-sm">
	<a href="">S'inscrire</a>
	<a href="<%=request.getContextPath() %>/connexion">Se connecter</a>
	    </div>
	  </div>
	</div>
	
	<h2 class="title">Liste des enchères</h2>
</div>	
	
	<div>
		<form>
		  <div class="form-group">
		    <label for="exampleInputEmail1">Filtres :</label>		   
		    <input type="text" class="form-control inputSizeFiltre" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="nom de l'article" >
		  </div>
		  	<div class="container container2">
		  		<div class="row" >
		  			<div class="col-sm">
		  				Categorie : 
					</div>
					<div class="form-group col-sm">
						<input type="text" class="form-control inputCategorieSize" id="exampleInputEmail2" aria-describedby="emailHelp" placeholder="nom de l'article" >
					</div>
				<div class="col-sm">
					<button type="button" class="btn btn-light">Rechercher</button>
				</div>
			</div>
			</div>
		</form>
	</div>
	
<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<form method="post" action="<%=request.getContextPath() %>/VendreArticle">
				  	<div class="form-group">
				    	<label for="nomArticle">Article :</label>
				    	<input type="text" id="nomArticle" name="nomArticle">
				  	</div>
				  	<div class="form-group">
				    	<label for="description">Description :</label>
				    	<input type="text" id="description" name="description">
				  	</div>
				  	
				  	<%
		
		List<Categorie> listeCategories = (ArrayList<Categorie>) request.getAttribute("liste");
	%>
	
	Catégories :		
<span class="custom-dropdown custom-dropdown--white">
    <select class="custom-dropdown__select custom-dropdown__select--white" id="categorie" name="categorie">
  <% 	for(Categorie categorie : listeCategories) {
	%>
     <option value="<%=categorie.getNoCategorie() %>" ><%=categorie.getLibelle() %></option>
    <% } %>
     </select>	
  </span>
  
	
				  	<div class="form-group">
				    	<label for="photoArticle">Photo de l'article :</label>
				    	<input type="text" id="photoArticle" name="photoArticle">
				  	</div>
				  	<div class="form-group">
				    	<label for="misePrix">Mise à prix :</label>
				    	<input type="text" id="misePrix" name="misePrix">
				  	</div>
				  	<div class="form-group">
				    	<label for="debutEnchere">Début de l'enchère :</label>
				    	<input type="text" id="debutEnchere" name="debutEnchere">
				  	</div>
				  	<div class="form-group">
				    	<label for="finEnchere">Fin de l'enchère :</label>
				    	<input type="text" id="finEnchere" name="finEnchere">
				  	</div>
				  	
				  	<button type="submit" class="btn btn-default">Enregistrer</button>
				</form>
			</div>
		</div>
	  	<div class="row">
			<div class="footer col-sm-12">
		  		<p>Copyright (c) TP Eni Ecole</p>
			</div>
		</div>
	</div>
	
	

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

<style>
.title{
	text-align:center;
}


.container2
{
    display: flex;
    justify-content: flex-start;
    margin-left: inherit;
}
.inputSizeFiltre{
	width: 30%;
}
.article1{
	margin-top:5em;
	border: black 3px solid;
    width: 60%;
}
.left {
 float: left;
}
</style>
</html>
