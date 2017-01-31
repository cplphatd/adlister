<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 1/30/17
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String name = request.getParameter("name"); %>
<% String message; %>
<% if (name != null) {
    message = "Hello, " + name + ".";
} else {
    message = "Hello world!";
}%>
<html>
    <head>
        <jsp:include page="partials/head.jsp">
            <jsp:param name="title" value="Profile"></jsp:param>
        </jsp:include>
    </head>
    <body>
        <%@include file="partials/navbar.jsp"%>
        <div class="container">
            <h1><%= message %></h1>
        </div>
        <%@include file="partials/footer.jsp"%>
    </body>
</html>
