<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Pizza</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Edit Pizza</h1>
        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">${success}</div>
        </c:if>

        <form:form action="/pizzas/update/${pizza.id}" method="post" modelAttribute="pizza">
            <div class="form-group">
                <label for="size">Size:</label>
                <select name="size" id="size" class="form-control" required>
                    <option value="Small" <c:if test="${pizza.size == 'Small'}">selected</c:if>>Small</option>
                    <option value="Medium" <c:if test="${pizza.size == 'Medium'}">selected</c:if>>Medium</option>
                    <option value="Large" <c:if test="${pizza.size == 'Large'}">selected</c:if>>Large</option>
                </select>
            </div>

            <div class="form-group">
                <label for="crust">Crust:</label>
                <select name="crust" id="crust" class="form-control" required>
                    <option value="Thin Crust" <c:if test="${pizza.crust == 'Thin Crust'}">selected</c:if>>Thin Crust</option>
                    <option value="Thick Crust" <c:if test="${pizza.crust == 'Thick Crust'}">selected</c:if>>Thick Crust</option>
                    <option value="Stuffed Crust" <c:if test="${pizza.crust == 'Stuffed Crust'}">selected</c:if>>Stuffed Crust</option>
                </select>
            </div>

            <div class="form-group">
                <label>Toppings:</label><br>
                <c:forEach var="topping" items="${availableToppings}">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="toppings" value="${topping}" 
                            id="topping-${topping}" 
                            <c:if test="${fn:contains(pizza.toppings, topping)}">checked</c:if> />
                        <label class="form-check-label" for="topping-${topping}">${topping}</label>
                    </div>
                </c:forEach>
            </div>

            <button type="submit" class="btn btn-success btn-block">Update Pizza</button>
        </form:form>

        <div class="text-center mt-4">
            <a href="/pizzas/cart" class="btn btn-secondary">Back to Cart</a>
        </div>
    </div>
</body>
</html>
