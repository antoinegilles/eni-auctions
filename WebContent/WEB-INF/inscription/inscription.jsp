<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscription</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

	 <div class="jumbotron">
	 	<div class="container">
	  		<div class="row justify-content-start">
				<h5 class="col-4">
			      ENI - Encheres
			    </h5>
			    <h1 class="col-4">
			      Inscription
			    </h1>
			</div>
		</div>
	</div>
	
	<div class="container">
		  		<form class="row" action="<%=request.getContextPath() %>/Inscription" method="post">
			    <div class="col">
				    <div class="input-group mb-3">
	  					<input type="text" id="pseudo" name="pseudo" class="form-control" placeholder="Pseudo" aria-label="pseudo" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="text" id="prenom" name="prenom" class="form-control" placeholder="Prenom" aria-label="prenom" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="text" id="telephone" name="telephone" class="form-control" placeholder="Telephone" aria-label="telephone" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="text" id="codePostal" name="codePostal" class="form-control" placeholder="Code Postale" aria-label="codePostal" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="password" id="mdp" name="mdp" class="form-control" placeholder="Mot de passe" aria-label="mdp" aria-describedby="basic-addon1">
					</div>
			    </div>
			    <div class="col">
				    <div class="input-group mb-3">
	  					<input type="text" id="nom" name="nom" class="form-control" placeholder="Nom" aria-label="nom" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="email" id="email" name="email" class="form-control" placeholder="E-mail" aria-label="email" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="text" id="rue" name="rue" class="form-control" placeholder="Rue" aria-label="rue" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="text" id="ville" name="ville" class="form-control" placeholder="Ville" aria-label="ille" aria-describedby="basic-addon1">
					</div>
					<div class="input-group mb-3">
	  					<input type="password" id="confirmation" name="confirmation" class="form-control" placeholder="Confirmation" aria-label="confirmation" aria-describedby="basic-addon1">
					</div>
			    </div>
			    <div class="container">
  					<div class="row">
			  			<div class="col-3"></div>
			  			<div class="col">
							<button  type="submit" class="btn btn-dark">Créer</button>
					    </div>
					   <div class="col"></div>
		    			<div class="col">
							<a type="button" href="<%=request.getContextPath() %>/" class="btn btn-dark">Annuler</a>
					    </div>
  					</div>
  				</div>
  				</form>
		</div>
				<%
				String erreur = "";
				if(request.getAttribute("erreur")!= null)
					erreur = (String)request.getAttribute("erreur");
				%>
				<p><font color="red"><%=erreur %></font></p>
	

	
	
	
	
	
	
	
	

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>