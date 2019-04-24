<%@page import="fr.eni_ecole.auction.beans.Utilisateur"%>
<%@page import="fr.eni_ecole.auction.beans.ArticleVendu"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    request.setAttribute("title", "Détail vente");
%>

<%@include file="../../fragments/Head.jspf"%>

<%
    if(request.getAttribute("erreur") != null) { %>
<div class="error-box">
    <p><i class="fas fa-exclamation-circle"></i> <%=request.getAttribute("erreur").toString() %></p>
</div>
<% } %>

<%
    // rï¿½cuperer l'attribut animateur placï¿½s dans le contexte de session
    Utilisateur a = (Utilisateur) request.getSession().getAttribute("UserConnecte");
%>


<%ArticleVendu unArticle = (ArticleVendu) request.getAttribute("detailArticle");
  SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEEE dd MMMMM yyyy", Locale.FRANCE);
  int minPrice = ( unArticle.getMisAPrix() > unArticle.getPrixVente())? unArticle.getMisAPrix() : unArticle.getPrixVente();
  minPrice++;
%>
<div class="divider">
    <div class="shop-view detail-image">
        <article>
            <div class="preview-img" id="preview-img">
                <img src="<%=request.getAttribute("image")%>">
            </div>
        </article>
    </div>

    <form  class="titled" method="get" action="<%=request.getContextPath() %>/Encherir">
        <p class="title">Article</p>
        <table>
            <tr>
                <td><b>Nom :</b></td>
                <td><%=unArticle.getNomArticle() %></td>
            </tr>
            <tr>
                <td><b>Description :</b></td>
                <td><%=unArticle.getDescription() %></td>
            </tr>
            <tr>
                <td><b>Catégorie :</b></td>
                <td><%=unArticle.getCategorie().getLibelle() %></td>
            </tr>
            <tr>
                <td><b>Meilleur offre :</b></td>
                <td><%=unArticle.getPrixVente() %></td>
            </tr>
            <tr>
                <td><b>Mise à prix :</b></td>
                <td><%=unArticle.getMisAPrix() %></td>
            </tr>
            <tr>
                <td><b>Fin de l'enchère :</b></td>
                <td><%=dateFormatter.format(unArticle.getDateFinEncheres()) %></td>
            </tr>
            <tr>
                <td><b>Retrait :</b></td>
                <td><%=unArticle.getUtilisateur().getRue() %>,
                    <%=unArticle.getUtilisateur().getCodePostal() %> <%=unArticle.getUtilisateur().getVille() %></td>
            </tr>
            <tr>
                <td><b>Vendeur :</b></td>
                <td><%=unArticle.getUtilisateur().getPseudo() %></td>
            </tr>
        </table>
        <p class="title">Ma proposition</p>
        <table>
            <tr>
                <td><input type="number" id="proposition" name="proposition" min="<%=minPrice %>" value="<%=minPrice %>" required></td>
                <td><button type="submit">Enchérir</button></td>
            </tr>
        </table>
    </form>
</div>

<%@include file="../../fragments/Bottom.jspf"%>