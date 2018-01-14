<%--
Document   : homepage
Created on : Dec 20, 2017, 8:41:39 PM
Author     : xontik
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.jsp" %>

<section class="row">
    <h2>Détails d'un article</h2>
    <div class="col m12 l6 ">
        <div class="card-panel fill product-image">
            <img src="assets/images/${article.image}"/>
        </div>
    </div>
    <div class="col m12 l6">
        <div class="card">
            <div class="card-content">
                <h3>${catArticle.nom} - ${article.nomArticle}</h3>

            </div>
            <div class="card-action">
                <form class="" action="addtocart" method="post">
                    <input type="hidden" value="${article.idArticle}" name="idArticle" required/>
                    <div class="row">
                    <div class="input-field col s5">
                        <select name="taille">
                            <option value="" disabled selected>Taille</option>
                            <option value="1">XS</option>
                            <option value="2">S</option>
                            <option value="3">M</option>
                            <option value="4">L</option>
                            <option value="5">XL</option>
                            <option value="6">XXL</option>
                        </select>
                        <label>Materialize Select</label>
                    </div>
                    <div class="input-field col s3 ">
                        <input id="qte" name="qte" value="1" type="number" >
                            <label for="qte">Qte.</label>
                        </div>

                        <button class="btn-floating pink ligthen-2 right btn-large" type="submit" name="add_to_cart"><i class="material-icons ">add_shopping_cart</i></button>
                    </form>
                </div>

            </div>
        </section>





        <%@include file="footer.jsp" %>
