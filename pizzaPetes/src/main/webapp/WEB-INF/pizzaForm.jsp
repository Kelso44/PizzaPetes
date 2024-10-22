<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Pizza</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Create Pizza</h1>

        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">
                ${success}
            </div>
        </c:if>

        <form:form action="/pizzas" method="post" modelAttribute="pizza">
            <div class="form-group">
                <label for="size">Size:</label>
                <select name="size" id="size" class="form-control" required>
                    <option value="Small">Small</option>
                    <option value="Medium">Medium</option>
                    <option value="Large">Large</option>
                </select>
            </div>

            <div class="form-group">
                <label for="crust">Crust:</label>
                <select name="crust" id="crust" class="form-control" required>
                    <option value="Thin Crust">Thin Crust</option>
                    <option value="Thick Crust">Thick Crust</option>
                    <option value="Stuffed Crust">Stuffed Crust</option>
                </select>
            </div>

            <div class="form-group">
                <label>Toppings:</label><br>
                <c:forEach var="topping" items="${availableToppings}">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="toppings" value="${topping}" id="topping-${topping}">
                        <label class="form-check-label" for="topping-${topping}">${topping}</label>
                    </div>
                </c:forEach>
            </div>

            <button type="submit" class="btn btn-success btn-block">Create Pizza</button>
        </form:form>

        <div class="text-center mt-4">
            <a href="/pizzas/cart" class="btn btn-secondary">Back to Cart</a>
        </div>
    </div>
</body>
</html>
