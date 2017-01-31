<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 1/30/17
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="partials/head.jsp">
            <jsp:param name="title" value="Login"></jsp:param>
        </jsp:include>
    </head>
    <body>
        <%@include file="partials/navbar.jsp"%>
        <div class="container">
            <h1>Please login:</h1>
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
