<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login and Registration</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>Authentication</h1>

		<!-- Registration -->
		<h2>Register</h2>
		<form:form action="/register/user" method="post"
			modelAttribute="newUser">
			<div class="form-group">
				<form:label path="firstName">First Name</form:label>
				<form:errors class="text-danger" path="firstName" />
				<form:input class="form-control" path="firstName" />
			</div>
			<div class="form-group">
				<form:label path="lastName">Last Name</form:label>
				<form:errors class="text-danger" path="lastName" />
				<form:input class="form-control" path="lastName" />
			</div>
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:errors class="text-danger" path="email" />
				<form:input class="form-control" path="email" />
			</div>
			<div class="form-group">
				<form:label path="password">Password</form:label>
				<form:errors class="text-danger" path="password" />
				<form:password class="form-control" path="password" />
			</div>
			<div class="form-group">
				<form:label path="confirm">Confirm Password</form:label>
				<form:errors class="text-danger" path="confirm" />
				<form:password class="form-control" path="confirm" />
			</div>

			<div class="form-group">
				<form:label path="gender">Gender</form:label>
				<form:radiobutton path="gender" value="Male" />
				Male
				<form:radiobutton path="gender" value="Female" />
				Female
				<form:radiobutton path="gender" value="Other" />
				Other
				<form:errors class="text-danger" path="gender" />
			</div>

			<div class="form-group">
				<form:label path="preferences">Preferences</form:label>
				<br>
				<form:checkbox path="preferences" value="News" />
				News
				<form:checkbox path="preferences" value="Updates" />
				Updates
				<form:checkbox path="preferences" value="Offers" />
				Offers
				<form:errors class="text-danger" path="preferences" />
			</div>

			<div class="form-group">
				<form:label path="birthday">Birthday</form:label>
				<form:input class="form-control" path="birthday" type="date" />
				<form:errors class="text-danger" path="birthday" />
			</div>

			<div class="form-group">
				<button type="submit" class="btn btn-primary">Register</button>
			</div>
		</form:form>

		<!-- Login  -->
		<h2>Login</h2>
		<form:form action="/login/user" method="post"
			modelAttribute="newLogin">
			<div class="form-group">
				<form:label path="email">Email</form:label>
				<form:errors class="text-danger" path="email" />
				<form:input class="form-control" path="email" />
			</div>
			<div class="form-group">
				<form:label path="password">Password</form:label>
				<form:errors class="text-danger" path="password" />
				<form:password class="form-control" path="password" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Login</button>
			</div>
		</form:form>
	</div>
</body>
</html>
