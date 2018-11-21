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
    <h3>Menu</h3>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Dish</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <c:forEach items="${menu}" var="menu">
            <jsp:useBean id="menu" class="ru.gekov.model.MenuDish"/>
            <tr>
                <td>${menu.dish.name}</td>
                <td>${menu.dish.cost}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/restaurants/menu/${menu.id}/update">Update</a>
                    | <a href="${pageContext.request.contextPath}/restaurants/${menu.restaurant.id}/menu/${menu.id}/delete">Delete</a>
                    | <a href="${pageContext.request.contextPath}/restaurants/${menu.restaurant.id}/menu/add">Add menu dish</a>
                </td>
                <%--<td><a href="${pageContext.request.contextPath}/restaurants/${menu.restaurant.id}/menu/${menu.id}/delete">Delete</a></td>--%>
                <%--<td><a href="${pageContext.request.contextPath}/restaurants/${menu.restaurant.id}/menu/add">Add menu dish</a></td>--%>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>