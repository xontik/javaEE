<%--
    Document   : header
    Created on : Dec 20, 2017, 8:42:24 PM
    Author     : xontik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!--Import Google Icon Font-->
        <link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!--Import materialize.css-->
        <link type="text/css" rel="stylesheet" href="assets/css/materialize.min.css"  media="screen,projection"/>
        <link type="text/css" rel="stylesheet" href="assets/css/main.css"  media="screen,projection"/>

        <!--Let browser know website is optimized for mobile-->
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    </head>
    <body>
        <header>
            <nav class="nav-extended">
                <div class="nav-wrapper">
                    <a href="home" class="brand-logo">Logo</a>
                    <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                    <ul id="nav-mobile" class="right hide-on-med-and-down">
                        <li><a href="#!">Panier</a></li>

                        <% if(session.getAttribute("logged") != null && ((Boolean)session.getAttribute("logged")==true)){ %>
                            <li><a href="login">Se connecter</a></li>
                        <% } else {%>
                            <li><a href="login">Mon compte</a></li>
                        <% }%>


                    </ul>
                    <ul class="side-nav" id="mobile-demo">
                        <% if(session.getAttribute("logged") != null && ((Boolean)session.getAttribute("logged")==true)){ %>
                            <li><a href="login">Se connecter</a></li>
                        <% } else {%>
                            <li><a href="login">Mon compte</a></li>
                        <% }%>
                        <li><a href="#!">Panier</a></li>
                    </ul>
                </div>
                <% if(request.getAttribute("cats") != null) { %>
                <div class="nav-content">
                    <ul class="tabs tabs-transparent">
                        <li class="tab " ><a class="${empty param.categorie ? 'active' : ''}" href="#!">All</a></li>        
                        <li class="tab " ><a class="${param.categorie eq cats[0].idCategorie ? 'active' : ''}" href="#!"> ${cats[0].nom} </a></li>        
                        <li class="tab " ><a class="${param.categorie eq cats[1].idCategorie ? 'active' : ''}" href="#!"> ${cats[1].nom}</a></li>
                        <li class="tab " ><a class="${param.categorie eq cats[2].idCategorie ? 'active' : ''}" href="#!"> ${cats[2].nom}</a></li>
                        <li class="tab " ><a class="${param.categorie eq cats[3].idCategorie ? 'active' : ''}" href="#!"> ${cats[3].nom}</a></li>
                        <li class="tab " ><a class="${param.categorie eq cats[4].idCategorie ? 'active' : ''}" href="#!"> ${cats[4].nom}</a></li>
                    </ul>
                </div>
                <% } %>
            </nav>

        </header>
        <main class="container">
