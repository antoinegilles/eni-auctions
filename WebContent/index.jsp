<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
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
	<br>
	<a href="<%=request.getContextPath() %>/connexion">Se connecter</a>
	<br>
	<a href="<%=request.getContextPath() %>/ListerArticles">Liste les enchères en cours</a>
	<br>
	<a href="<%=request.getContextPath() %>/VendreArticle">Vendre un article</a><br />
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
	<div class="col-sm">
		<div class="article1">
			<div class="container">
		  <div class="row">
		    <div class="col-sm">
		  		<img src="images/dinosaur.jpg" alt="image article" class="left">
			</div>
		    <div class="col-sm">
				<p>Pc pour Gamer pour travailler</p>
				<p>Prix : 30 euros</p>
				<p>Fin de l'enchere : 10/25/6354</p>
				<p>Vendeur : Jojo45</p>
		    </div>
		  </div>
		</div>
		</div>
	</div>
<div class="col-sm">
		<div class="article1">
			<div class="container">
		  <div class="row">
		    <div class="col-sm">
		  		<img src="images/dinosaur.jpg" alt="image article" class="left">
			</div>
		    <div class="col-sm">
				<p>Pc pour Gamer pour travailler</p>
				<p>Prix : 30 euros</p>
				<p>Fin de l'enchere : 10/25/6354</p>
				<p>Vendeur : Jojo45</p>
		    </div>
		  </div>
		</div>
		</div>
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
