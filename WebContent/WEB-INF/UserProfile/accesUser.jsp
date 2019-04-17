<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
		

		<% 
		// récuperer l'attribut animateur placé dans le contexte de session
		Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte"); 
		%>
		
		<p>User : <%= a.getPrenom()%>&nbsp;<%=a.getNom()%></p>

		