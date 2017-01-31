<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="partials/head.jsp">
            <jsp:param name="title" value="Home Page"></jsp:param>
        </jsp:include>
    </head>
    <body>
        <div class="container">
            <%@include file="partials/navbar.jsp"%>
            <c:if test="true">
                <h1>Variable names should be very descriptive</h1>
            </c:if>
            <c:if test="false">
                <h1>single letter variable names are good</h1>
            </c:if>
        </div>
        <%@include file="partials/footer.jsp"%>
    </body>
</html>
