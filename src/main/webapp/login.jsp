<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 1/30/17
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <%@include file="partials/stylesheet.jsp"%>
    </head>
    <body>
        <%@include file="partials/navbar.jsp"%>
        <div class="container">
            <form method="post" action="/login">
                <label>Username: </label>
                <input id="username" name="username" type="text">
                <label>Password: </label>
                <input id="password" name="password" type="password">
                <button type="submit">Login</button>
            </form>
        </div>
        <%@include file="partials/footer.jsp"%>
    </body>
</html>
