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

                <div class="card grey lighten-5">
                    <div class="card-image fill">
                        <img class="responsive-img" src="assets/images/${article.image}"/>
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
                </div>
            </div>
        </c:forEach>
</div>



<%@include file="footer.jsp" %>
