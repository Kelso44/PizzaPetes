<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pizza Pete's</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Welcome to Pizza Pete's</h1>

        <c:if test="${not empty success}">
            <div class="alert alert-success text-center">
                ${success}
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger text-center">
                <strong>Error:</strong> ${error}
            </div>
        </c:if>

        <div class="row">
            <div class="col-md-6 offset-md-3">
                <!-- Registration Form -->
                <h2 class="mt-4">Register</h2>
                <form:form action="/register/user" method="post" modelAttribute="newUser">
                    <form:errors path="*" cssClass="text-danger" />
                    <div class="form-group">
                        <form:label path="firstName">First Name</form:label>
                        <form:input path="firstName" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <form:label path="lastName">Last Name</form:label>
                        <form:input path="lastName" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <form:label path="address">Address</form:label>
                        <form:input path="address" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <form:label path="city">City</form:label>
                        <form:input path="city" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <form:label path="state">State</form:label>
                        <form:select path="state" class="form-control" required="required">
                            <option value="" disabled selected>Select your state</option>
                            <option value="AL">Alabama</option>
                            <option value="AK">Alaska</option>
                            <option value="AZ">Arizona</option>
                            <option value="AR">Arkansas</option>
                            <option value="CA">California</option>
                            <option value="CO">Colorado</option>
                            <option value="CT">Connecticut</option>
                            <option value="DE">Delaware</option>
                            <option value="DC">District of Columbia</option>
                            <option value="FL">Florida</option>
                            <option value="GA">Georgia</option>
                            <option value="HI">Hawaii</option>
                            <option value="ID">Idaho</option>
                            <option value="IL">Illinois</option>
                            <option value="IN">Indiana</option>
                            <option value="IA">Iowa</option>
                            <option value="KS">Kansas</option>
                            <option value="KY">Kentucky</option>
                            <option value="LA">Louisiana</option>
                            <option value="ME">Maine</option>
                            <option value="MD">Maryland</option>
                            <option value="MA">Massachusetts</option>
                            <option value="MI">Michigan</option>
                            <option value="MN">Minnesota</option>
                            <option value="MS">Mississippi</option>
                            <option value="MO">Missouri</option>
                            <option value="MT">Montana</option>
                            <option value="NE">Nebraska</option>
                            <option value="NV">Nevada</option>
                            <option value="NH">New Hampshire</option>
                            <option value="NJ">New Jersey</option>
                            <option value="NM">New Mexico</option>
                            <option value="NY">New York</option>
                            <option value="NC">North Carolina</option>
                            <option value="ND">North Dakota</option>
                            <option value="OH">Ohio</option>
                            <option value="OK">Oklahoma</option>
                            <option value="OR">Oregon</option>
                            <option value="PA">Pennsylvania</option>
                            <option value="RI">Rhode Island</option>
                            <option value="SC">South Carolina</option>
                            <option value="SD">South Dakota</option>
                            <option value="TN">Tennessee</option>
                            <option value="TX">Texas</option>
                            <option value="UT">Utah</option>
                            <option value="VT">Vermont</option>
                            <option value="VA">Virginia</option>
                            <option value="WA">Washington</option>
                            <option value="WV">West Virginia</option>
                            <option value="WI">Wisconsin</option>
                            <option value="WY">Wyoming</option>
                        </form:select>
                    </div>

                    <div class="form-group">
                        <form:label path="email">Email</form:label>
                        <form:input path="email" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <form:label path="password">Password</form:label>
                        <form:password path="password" class="form-control" required="required" />
                    </div>
                    <div class="form-group">
                        <form:label path="confirm">Confirm Password</form:label>
                        <form:password path="confirm" class="form-control" required="required" />
                    </div>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form:form>

                <!-- Login Form -->
                <h2 class="mt-4">Login</h2>
                <form:form action="/login/user" method="post" modelAttribute="newLogin">
                    <form:errors path="*" cssClass="text-danger" />
                    <div class="form-group">
                        <form:label path="email">Email</form:label>
                        <form:input path="email" class="form-control" placeholder="Email" required="required" />
                    </div>
                    <div class="form-group">
                        <form:label path="password">Password</form:label>
                        <form:password path="password" class="form-control" placeholder="Password" required="required" />
                    </div>
                    <button type="submit" class="btn btn-success btn-block">Login</button>
                </form:form>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
