<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 2/2/17
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Search Results" />
        </jsp:include></head></head>
    <body>
        <jsp:include page="/WEB-INF/partials/navbar.jsp" />
        <h1>Search results:</h1>
        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6">
                <h2><c:out value="${ad.title}"></c:out></h2>
                <p><c:out value="${ad.description}"></c:out></p>
            </div>
        </c:forEach>
    </body>
</html>
