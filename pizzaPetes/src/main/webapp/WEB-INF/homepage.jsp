<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizza Pete's - Home</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">
                ${success}
            </div>
        </c:if>
        <h1 class="text-center">Welcome, ${user.firstName}!</h1>
        <div class="text-center mb-4">
            <a href="/logout" class="btn btn-danger">Logout</a>
            <a href="/profile" class="btn btn-warning">View Profile</a>
        </div>

        <h2>Account Info</h2>
        
        <div class="card mb-4">
            <div class="card-body">
                <p><strong>First Name:</strong> ${user.firstName}</p>
                <p><strong>Last Name:</strong> ${user.lastName}</p>
                <p><strong>Email:</strong> ${user.email}</p>
                <p><strong>Address:</strong> ${user.address}, ${user.city}, ${user.state}</p>
            </div>
        </div>

        <h2 class="mt-4">Quick Options</h2>
        <div class="row">
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Create a Pizza</h5>
                        <p class="card-text">Start creating your own pizza.</p>
                        <a href="/pizzas/new" class="btn btn-primary">Create Pizza</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">View Cart</h5>
                        <p class="card-text">See the pizzas you've added to your cart.</p>
                        <a href="/pizzas/cart" class="btn btn-info">View Cart</a>
                    </div>
                </div>
            </div>
           <div class="col-md-4">
    <div class="card text-center">
        <div class="card-body">
            <h5 class="card-title">View Orders</h5>
            <p class="card-text">Check your past orders.</p>
            <a href="/orders/list" class="btn btn-secondary">View Orders</a>
        </div>
    </div>
</div>

        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
