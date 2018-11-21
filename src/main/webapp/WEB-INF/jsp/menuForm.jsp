<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<section>
    <h2>Menu form</h2>
    <hr>
    <jsp:useBean id="menu" type="ru.gekov.model.MenuDish" scope="request"/>
    <form method="post" action="${pageContext.request.contextPath}/restaurants/${menu.restaurant.id}/menu/save">
        <dl>
            <dt>Restaurant:</dt>
            <dl>${menu.restaurant.name}</dl>
        </dl>
        <input type="hidden" name="id" value="${menu.id}">
        <dl>
            <dt>Date:</dt>
            <dd><input type="date" value="${menu.date}" name="date" required></dd>
        </dl>

        <dl>
            <dt>Dishes:</dt>
            <dd>
                    <select name="selectedDishId" size="${dishes.size()}" required>
                        <c:forEach items="${dishes}" var="dish">
                            <option value="${dish.id}">${dish.name} ${dish.cost/100} $</option>
                        </c:forEach>
                    </select>

            </dd>
        </dl>

        <button type="submit">Save</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>

</body>
</html>
