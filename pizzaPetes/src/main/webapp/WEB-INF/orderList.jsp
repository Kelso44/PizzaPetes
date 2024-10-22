<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Your Orders</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Your Orders</h1>

        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">${success}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">${error}</div>
        </c:if>

        <c:if test="${not empty ordersWithPizzas}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Pizzas</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderWithPizzas" items="${ordersWithPizzas}">
                        <tr>
                            <td>${orderWithPizzas.order.id}</td>
                            <td>
                                <c:forEach var="pizza" items="${orderWithPizzas.pizzas}">
                                    ${pizza.size} - $${pizza.price} <br/>
                                    Crust: ${pizza.crust} <br/> 
                                    Toppings: 
                                    <c:forEach var="topping" items="${pizza.toppings}">
                                        ${topping}<br/>
                                    </c:forEach>
                                    <br/>
                                </c:forEach>
                            </td>
                            <td>
                                <form action="/orders/delete/${orderWithPizzas.order.id}" method="post" style="display:inline;">
                                    <button type="submit" class="btn btn-danger">Delete</button>
                                </form>
                                <a href="/orders/reorder/${orderWithPizzas.order.id}" class="btn btn-info">Reorder</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <c:if test="${empty ordersWithPizzas}">
            <p>You have no orders yet.</p>
        </c:if>

        <div class="text-center mt-4">
            <a href="/pizzas/cart" class="btn btn-primary">Back to Cart</a>
            <a href="/homepage" class="btn btn-secondary">Go to Homepage</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
