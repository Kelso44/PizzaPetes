<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Profile</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Edit Profile</h1>

        <form action="/profile/update" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <div class="form-group">
                <input type="text" name="firstName" class="form-control" placeholder="First Name" value="${user.firstName}" required>
            </div>
            <div class="form-group">
                <input type="text" name="lastName" class="form-control" placeholder="Last Name" value="${user.lastName}" required>
            </div>
            <div class="form-group">
                <input type="text" name="email" class="form-control" placeholder="Email" value="${user.email}" required>
            </div>
            <div class="form-group">
                <input type="text" name="address" class="form-control" placeholder="Address" value="${user.address}" required>
            </div>
            <div class="form-group">
                <input type="text" name="city" class="form-control" placeholder="City" value="${user.city}" required>
            </div>
            <div class="form-group">
                <input type="text" name="state" class="form-control" placeholder="State" value="${user.state}" required>
            </div>
            <button type="submit" class="btn btn-success btn-block">Update Profile</button>
        </form>

        <div class="text-center mt-4">
            <a href="/profile" class="btn btn-secondary">Back to Profile</a>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
