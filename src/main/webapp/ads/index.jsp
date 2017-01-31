<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 1/31/17
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="../partials/head.jsp">
            <jsp:param name="title" value="Ads Listings"></jsp:param>
        </jsp:include>
    </head>
    <body>
        <jsp:include page="../partials/navbar.jsp"></jsp:include>
        <div class="container">
            <c:forEach var="ad" items="${ads}">
                <h1>${ad.id}. ${ad.title}</h1>
                <p>- ${ad.description}</p>
            </c:forEach>
        </div>
    </body>
</html>
