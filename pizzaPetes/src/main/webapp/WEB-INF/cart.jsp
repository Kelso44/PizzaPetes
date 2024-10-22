<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Your Cart</h1>

        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">
                ${success}
            </div>
        </c:if>

        <c:if test="${not empty cartPizzas}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Size</th>
                        <th>Crust</th>
                        <th>Toppings</th>
                        <th>Price</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pizza" items="${cartPizzas}">
                        <tr>
                            <td>${pizza.id}</td>
                            <td>${pizza.size}</td>
                            <td>${pizza.crust}</td>
                            <td>
                                <c:if test="${not empty pizza.toppings}">
                                    <c:forEach var="topping" items="${pizza.toppings}" varStatus="status">
                                        <c:out value="${topping}"/>
                                        <c:if test="${!status.last}">, </c:if>
                                    </c:forEach>
                                </c:if>
                            </td>
                            <td>${pizza.price}</td>
                            <td>
                                <a href="/pizzas/edit/${pizza.id}" class="btn btn-warning">Edit</a>
                                <form action="/pizzas/delete/${pizza.id}" method="post" style="display:inline;">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <c:set var="pizzaIds" value="" />
            <c:forEach var="pizza" items="${cartPizzas}" varStatus="status">
                <c:set var="pizzaIds" value="${pizzaIds}${pizza.id}"/>
                <c:if test="${!status.last}">
                    <c:set var="pizzaIds" value="${pizzaIds}," />
                </c:if>
            </c:forEach>

            <form action="/orders" method="post">
                <input type="hidden" name="userId" value="${sessionScope.userId}"/>
                <input type="hidden" name="pizzaIds" value="${pizzaIds}"/> 
                <button type="submit" class="btn btn-success mt-3">Checkout</button>
            </form>
        </c:if>
        <c:if test="${empty cartPizzas}">
            <p>Your cart is empty.</p>
        </c:if>

        <div class="text-center mt-4">
            <a href="/pizzas/new" class="btn btn-primary">Build Another Pizza</a>
            <a href="/homepage" class="btn btn-secondary">Go to Homepage</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
