<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <title>TP Web - Accueil</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/WebContent/theme/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/WebContent/theme/css/style.css">
  <script src="<%=request.getContextPath() %>/WebContent/theme/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="jumbotron text-center">
		<h1>TP Web - Accueil</h1>
		<p>Centre de formation</p>
	</div>
	  
	<div class="container">
		<div class="row">
			<div class="col-sm-9">
				<h3>TP Web - Accueil</h3>
				<div class="erreur">
					<h2>Erreur</h2>
					<% Exception e = (Exception) request.getAttribute("erreur"); %>
					<p><font color=red>
						Une erreur s'est produite : <%=e.getMessage() %><br>
						Cause de l'erreur : <%=e.getCause() %><br>
						Détail : <%=e.getStackTrace()[0].getClassName() %>+ " - "+<%=e.getStackTrace()[0].getMethodName() %>+ " - "+<%=e.getStackTrace()[0].getLineNumber() %>
					</font></p>
					<p><%=e.getLocalizedMessage() %>
				</div>
			</div>
		    <div class="col-sm-3">
				<%@ include file="../structure/menu.jspf" %>
		  	</div>
		</div>
		<%@ include file="../structure/footer.jspf" %>
