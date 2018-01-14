<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<h1>Panier</h1>

<c:choose>
  <c:when test="${nbArticle==0 || nbArticle==null}">
    Votre panier est vide !
  </c:when>
  <c:otherwise>
    <ul class="collection">
    <c:forEach items="${sespanier}" var="lap">
        <li class="collection-item avatar">
            <a href="product?id=${lap.article.idArticle}" ><img src="assets/images/${lap.article.image}" class="circle"/></a>
        <span class="title">${lap.article.nomArticle} - <fmt:formatNumber value = "${lap.article.prix}" type = "currency"/></span>
        <p>Taille : ${lap.taille.nom} - Qte: ${lap.quantite} <br>
            <a href="deletefromcart?idArticle=${lap.article.idArticle}&idTaille=${lap.taille.idTaille}">Supprimer</a>
        </p>
        <span  class="secondary-content"><fmt:formatNumber value = "${lap.article.prix * lap.quantite}" type = "currency"/></span>
    </li>
    </c:forEach>
</ul>
    <div class="row ">
        <ul class="collection col offset-s9 s3">
            <li class="collection-item">Sous-total : <span class="secondary-content"><fmt:formatNumber value = "${total*0.8}" type = "currency"/></span></li>  
            <li class="collection-item">TVA :<span class="secondary-content"><fmt:formatNumber value = "${total * 0.2}" type = "currency"/></span></li>  
            <li class="collection-item">Total : <span class="secondary-content"><fmt:formatNumber value = "${total}" type = "currency"/></span></li>  
        </ul>
    </div>
    <div class="row ">
       
        <a href="#!" class="btn col offset-s9 s3">Commander</a>
    </div> 
  </c:otherwise>
</c:choose>

</div>


<%@include file="footer.jsp" %>
