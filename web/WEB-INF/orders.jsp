<%--
    Document   : homepage
    Created on : Dec 20, 2017, 8:41:39 PM
    Author     : xontik
--%>

<%@include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
        <h1>
            Les commandes 
        </h1>
<ul class="collapsible" data-collapsible="accordion">

         <c:forEach items="${paniers}" var="panier" varStatus="status">
            
                <li>
                    <div class="collapsible-header">
                        <c:choose>
                            <c:when test="${panier.livre}">
                                <i class="material-icons left">card_giftcard</i>
                            </c:when> 
                            <c:otherwise>
                                <i class="material-icons left">local_shipping</i>
                                <span>(Livraison : <fmt:formatDate type = "date" value = "${panier.dateLivraison}" /> ) <br></span>
                            </c:otherwise>
                        </c:choose>
                         <span>
                            N°CMD-${panier.idPanier}-IC   |   Date commande : <fmt:formatDate type = "date" value = "${panier.dateCommande}" /> |   Total : <fmt:formatNumber value = "${totaux[status.index]}" type="currency" />
                         </span>
                    </div>
                    <div class="collapsible-body">
                        <ul class="collection">
                        <c:forEach items="${commandes[status.index]}" var="lap" varStatus="statusA">

        <li class="collection-item avatar">
            <a href="product?id=${lap.article.idArticle}" ><img src="assets/images/${lap.article.image}" class="circle"/></a>
        <span class="title">${lap.article.nomArticle} - <fmt:formatNumber value = "${lap.article.prix}" type = "currency"/></span>
        <p>Taille : ${lap.taille.nom} - Qte: ${lap.quantite} <br>
        </p>
        <span  class="secondary-content"><fmt:formatNumber value = "${lap.article.prix * lap.quantite}" type = "currency"/></span>
    </li>
    </c:forEach>
</ul>
                    </div>
                  </li>
                
        </c:forEach>
                  <div class="card">
                

            </div>

  </ul>



<%@include file="footer.jsp" %>
