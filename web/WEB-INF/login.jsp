<%--
    Document   : homepage
    Created on : Dec 20, 2017, 8:41:39 PM
    Author     : xontik
--%>

<%@include file="header.jsp" %>

        <h1>Page de connection</h1>
        ${error}
        <form action="login" method="post">
                    <div class="input-field">
                        <i class="material-icons prefix">account_circle</i>
                        <input id="email" name="email" type="text">
                        <label for="email" class="">Identifiant</label>
                    </div>
                    <div class="input-field">
                        <i class="material-icons prefix">lock</i>
                        <input id="password" name="password" type="password">
                        <label for="password">Mot de passe</label>
                    </div>

                    <button class="btn waves-effect waves-light" type="submit">Se connecter</button>
                </form>



<%@include file="footer.jsp" %>
