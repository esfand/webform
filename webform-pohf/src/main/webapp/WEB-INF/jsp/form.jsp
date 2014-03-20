<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Webforms demo - POHF</title>
</head>
<body>
	<h1>Please enter your personal information:</h1>
	<ul>
		<c:forEach items="${messages}" var="message">
			<li><c:out value="${message}"></c:out>
		</c:forEach>
	</ul>
	<form method="POST">
		<div>
			<label>First name:</label> <input name="firstName" type="text" value="${firstName}">
		</div>
		<div>
			<label>Age:</label> <input name="age" type="text" value="${age}">
		</div>
		<div>
			<input type="submit" value="Submit the form">
		</div>
	</form>
</body>
</html>
