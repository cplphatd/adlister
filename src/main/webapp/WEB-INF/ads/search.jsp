<%--
  Created by IntelliJ IDEA.
  User: David
  Date: 2/2/17
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <jsp:include page="/WEB-INF/partials/head.jsp">
            <jsp:param name="title" value="Search Ads" />
        </jsp:include></head>
    <body>
        <div class="container">
            <h1>Search the Ads by:</h1>
            <form action="/ads/search" method="post">
                <div class="form-group">
                    <label for="id">ID #</label>
                    <input id="id" name="id" class="form-control" type="text">
                    <label for="title">Title</label>
                    <input id="title" name="title" class="form-control" type="text">
                </div>
                <input type="submit" class="btn btn-block btn-primary">
            </form>
        </div>
    </body>
</html>
