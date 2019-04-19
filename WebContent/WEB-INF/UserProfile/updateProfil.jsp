<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifier le profil</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>


<% 
// récuperer l'attribut animateur placés dans le contexte de session
Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte"); 
%>

<form action="./updateProfilUser" method="get">
	<div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  prenom : 
	  <input type="text" class="form-control" name="prenom" value="<%= a.getPrenom()%>" placeholder= "<%= a.getPrenom()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div>
	<div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  Nom : 
	  <input type="text" name="nom" class="form-control" value="<%= a.getNom()%>" placeholder="<%= a.getNom()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div><div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  Pseudo : 
	  <input type="text" name="pseudo" class="form-control" placeholder="<%= a.getPseudo()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div><div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  email : 
	  <input type="email" name="email" class="form-control" placeholder="<%= a.getEmail()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div><div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  Telephone : 
	  <input type="text" name="telephone" class="form-control" placeholder=" <%= a.getTelephone()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div><div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  Rue : 
	  <input type="text" name="rue" class="form-control" placeholder="<%= a.getRue()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div><div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  Code Postal : 
	  <input type="text" name="codePostal" class="form-control" placeholder="<%= a.getCodePostal()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div><div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  Ville : 
	  <input type="text" name="ville" class="form-control" placeholder="<%= a.getVille()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div>
	<div class="input-group flex-nowrap">
	  <div class="input-group-prepend">
	  </div>
	  MDP : 
	  <input type="password" name="mdp" class="form-control" placeholder= "<%= a.getMotDePasse()%>" aria-label="Username" aria-describedby="addon-wrapping">
	</div>
	<button type="submit">Modifier</button>
</form>






		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>		<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
		<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>