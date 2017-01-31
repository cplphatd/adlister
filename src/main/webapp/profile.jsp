<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 1/30/17
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String name; %>
<% String message; %>
<% if (request.getParameter("name") != null) {
    name = request.getParameter("name");
    message = "Hello, " + name;
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
