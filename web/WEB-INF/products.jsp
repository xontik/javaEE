<%--
Document   : homepage
Created on : Dec 20, 2017, 8:41:39 PM
Author     : xontik
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<h1>Selection : ${nomCat}</h1>
    <section class="products">
        <c:forEach items="${articles}" var="article">
            <div class="product">

                <div class="card sticky-action grey lighten-5">
                    <div class="card-image fill">
                        <a href="product?id=${article.idArticle}"><img class="activator" src="assets/images/${article.image}"/></a>
                        <a class="btn-floating halfway-fab waves-effect waves-light pink lighten-2"><i class="material-icons">add_shopping_cart</i></a>
                    </div>
                    <div class="card-content">
                        <span>${article.nomArticle}</span>
                    </div>
                    <div class="card-action">
                        <div class="row">
                            <div class="col s6">
                                4,7/5
                            </div>
                            <div class="col s6 righ">
                                <span class="right">${article.prix}€</span>
                            </div>
                        </div>
                    </div>
                    <!--<div class="card-reveal">
                        <span class="card-title grey-text text-darken-4">Détails<i class="material-icons right">close</i></span>
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </div>-->
                </div>
            </div>
        </c:forEach>
</div>



<%@include file="footer.jsp" %>
