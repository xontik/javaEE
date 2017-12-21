<%--
    Document   : header
    Created on : Dec 20, 2017, 8:42:24 PM
    Author     : xontik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="assets/css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="assets/css/main.css"  media="screen,projection"/>
        <c:forEach items="${css}" var="cssMedia">
            <link type="text/css" rel="stylesheet" href="assets/css/${cssMedia}.css"  media="screen,projection"/>
        </c:forEach>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <header>
            <nav class="nav-extended">
                <div class="nav-wrapper">
                    <a href="home" ><img  src="assets/images/logo.png" alt="Ice cube logo"/></a>
                    <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down">
                        <li><a href="#!">Panier</a></li>

                        <% if (session.getAttribute("logged") != null && ((Boolean) session.getAttribute("logged") == true)) { %>
                        <li><a href="login">Se connecter</a></li>
                            <% } else {%>
                        <li><a href="login">Mon compte</a></li>
                            <% }%>


                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <% if (session.getAttribute("logged") != null && ((Boolean) session.getAttribute("logged") == true)) { %>
                        <li><a href="login">Se connecter</a></li>
                            <% } else {%>
                        <li><a href="login">Mon compte</a></li>
                            <% }%>
                        <li><a href="#!">Panier</a></li>
                    </ul>
                </div>
                <div class="nav-content">

                    <ul class="tabs tabs-transparent">
                            <li class="tab " ><a class="${empty param.categorie ? 'active' : ''}" target="_self" href="home">Accueil</a></li>
                            <c:forEach items="${cats}" var="cat" >
                            <li class="tab " ><a class="${param.categorie eq cat.idCategorie ? 'active' : ''}" target="_self" href="<c:url value="products?categorie=${cat.idCategorie}"/>" > ${cat.nom}</a></li>
                            <c:if test="${param.categorie eq cat.idCategorie}" >
                                <c:set var = "nomCat" scope = "request" value = "${cat.nom}"/>
                            </c:if>
                            </c:forEach>
                    </ul>
                </div>
            </nav>

        </header>
        <main class="container">
