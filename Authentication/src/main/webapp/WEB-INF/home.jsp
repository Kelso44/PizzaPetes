<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
</head>
<body>
	<div class="container">
		<h1>Welcome Home</h1>
		<p>
			Hello,
			<c:out value="${user.firstName}" />
			! You have logged in.
		</p>

		<p>
			First Name:
			<c:out value="${user.firstName}" />
		</p>
		<p>
			Last Name:
			<c:out value="${user.lastName}" />
		</p>
		<p>
			Email:
			<c:out value="${user.email}" />
		</p>
		<p>
			Gender:
			<c:out value="${user.gender}" />
		</p>

		<p>
			Preferences:
			<c:forEach var="preference" items="${user.preferences.split(',')}"
				varStatus="status">
				<c:out value="${preference.trim()}" />
				<c:if test="${!preference.trim().isEmpty() && !status.last}">, </c:if>
			</c:forEach>
		</p>

		<p>
			Birthday:
			<c:out value="${formattedBirthday}" />
		</p>

		<a href="/logout">Logout</a>
	</div>
</body>
</html>
