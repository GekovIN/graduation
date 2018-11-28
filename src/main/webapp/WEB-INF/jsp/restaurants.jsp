<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<section>
    <h3>Restaurants</h3>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Name</th>
            <th>Address</th>
        </tr>
        </thead>
        <c:forEach items="${restaurants}" var="rest">
            <jsp:useBean id="restaurant" class="ru.gekov.model.Restaurant"/>
            <tr>
                <td>${rest.name}</td>
                <td>${rest.address}</td>
                <td><a href="${pageContext.request.contextPath}/restaurants/update/${rest.id}">Update</a></td>
                <td><a href="${pageContext.request.contextPath}/restaurants/delete/${rest.id}">Delete</a></td>
                <td><a href="${pageContext.request.contextPath}/menus?restId=${rest.id}">Show menu</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
