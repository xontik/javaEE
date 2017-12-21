<%-- 
    Document   : homepage
    Created on : Dec 20, 2017, 8:41:39 PM
    Author     : xontik
--%>

<%@include file="header.jsp" %>

        <h1>Page article</h1>
        <c:forEach items="${articles}" var="article">
            <c:out value="${article.nomArticle}" />

        </c:forEach>

<%@include file="footer.jsp" %>
