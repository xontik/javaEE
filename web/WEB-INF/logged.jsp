<%--
    Document   : homepage
    Created on : Dec 20, 2017, 8:41:39 PM
    Author     : xontik
--%>

<%@include file="header.jsp" %>

        <h1>
        <% if (session.getAttribute("logged") != null && ((Boolean) session.getAttribute("logged") == true)) { %>
            Bienvenue ${clientObject.prenomClient} ${clientObject.nomClient} !
        <% } else {%>
        <p>Vous avez �t� correctement d�connect�. <a href="home">Cliquez-ici</a> pour revenir � la page d'accueil
        <% } %>
        </h1>



<%@include file="footer.jsp" %>
