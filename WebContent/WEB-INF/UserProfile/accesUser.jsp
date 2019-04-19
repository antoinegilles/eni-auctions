<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
		
	<% 
		// récuperer l'attribut animateur placés dans le contexte de session
		Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte"); 
		%>
		
		
		<h1>Votre Profil</h1>
		<a href="<%=request.getContextPath() %>/updateProfil">Modifier vos informations</a>
			
		<p>Prenom : <%= a.getPrenom()%></p>		
		<p>Nom : <%= a.getNom()%></p>
		<p>Pseudo : <%= a.getPseudo()%></p>
		<p>Email : <%= a.getEmail()%></p>
		<p>Telephone : <%= a.getTelephone()%></p>
		<p>Rue : <%= a.getRue()%></p>
		<p>Code Postal : <%= a.getCodePostal()%></p>
		<p>Ville : <%= a.getVille()%></p>
		<p>Mot de passe : <%= a.getMotDePasse()%></p>
		<p>Credit : <%= a.getCredit()%></p>
	

	


		
		

		