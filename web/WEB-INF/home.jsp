<%--
    Document   : homepage
    Created on : Dec 20, 2017, 8:41:39 PM
    Author     : xontik
--%>

<%@include file="header.jsp" %>

        <h1>Page d'accueil</h1>
            <section class="products">
                <c:forEach items="${cats}" var="cat" varStatus="status">
                    <div class="product">

                        <div class="card sticky-action grey lighten-5">
                            <div class="card-image fill">
                                <img src="assets/images/${catsImage[status.index]} " alt="image de la categorie ${cat.nom}" />
                            </div>
                            <div class="card-action">
                            <a href="products?categorie=${cat.idCategorie}" class="flat-btn">${cat.nom}</a>     
                            </div>
                        </div>
                    </div>
                </c:forEach>

        </div>


<%@include file="footer.jsp" %>
