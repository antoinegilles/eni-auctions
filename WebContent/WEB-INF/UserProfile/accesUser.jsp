<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
		

		<% 
		// r�cuperer l'attribut animateur plac� dans le contexte de session
		Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte"); 
		%>
		
		<p>User : <%= a.getPrenom()%>&nbsp;<%=a.getNom()%></p>

		