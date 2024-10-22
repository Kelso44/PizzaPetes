<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">

        <!-- Flash message display -->
        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">
                ${success}
            </div>
        </c:if>

        <h1 class="text-center">Welcome, ${user.firstName}!</h1>
        <div class="text-center mb-4">
            <a href="/logout" class="btn btn-danger">Logout</a>
            <a href="/profile/edit/${user.id}" class="btn btn-warning">Edit Profile</a>
            <!-- Add Home Button -->
            <a href="/homepage" class="btn btn-primary">Home</a>
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

    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
