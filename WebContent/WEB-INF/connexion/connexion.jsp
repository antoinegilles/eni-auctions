<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connexion</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container">
	<div class="container">
  		<div class="row justify-content-start">
			<h5 class="col-4">
		      ENI - Encheres
		    </h5>
		    <h1 class="col-4">
		      Connexion
		    </h1>
		</div>
	</div>
	<div class="row justify-content-around">
		<form action="<%=request.getContextPath() %>/Connexion" method="post">
			<div class="form-group">
				<label for="identifiant">Email :</label>
				<input type="email" class="form-control" id="identifiant" name="identifiant">
			</div>
			<div class="form-group">
				<label for="motdepasse">Mot de passe :</label>
				<input type="password" class="form-control" id="motdepasse" name="motdepasse">
			</div>
			<button type="submit" class="btn btn-default">se connecter</button>
		</form>
	</div>
</div>	
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>

<style>

h3{
text-align: center;
}
</style>
</html>