<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile" />
    </jsp:include>
</head>
<body>
    <jsp:include page="/WEB-INF/partials/navbar.jsp" />

    <div class="container">
        <h1>Welcome, <c:out value="${sessionScope.user.username}"></c:out>!</h1>
        <h3>Your posted ads:</h3>
        <c:forEach var="ad" items="${ads}">
            <div class="col-md-6">
                <h2><c:out value="${ad.title}"></c:out></h2>
                <p><c:out value="${ad.description}"></c:out></p>
                <p>ID#: <c:out value="${ad.id}"></c:out></p>
                <a href="/update"><button class="btn btn-success">Update</button></a>
                <a href="/delete"><button class="btn btn-danger">Delete</button></a>
            </div>
        </c:forEach>
    </div>

</body>
</html>
