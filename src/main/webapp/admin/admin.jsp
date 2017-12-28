
<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>Admin Home Page</title>
</head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<body>
	<div class="container">

		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="text" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h3> Welcome ${pageContext.request.userPrincipal.name}</h3> 
				<p> <a onclick="document.forms['logoutForm'].submit()">Logout</a></p>
		</c:if>
	</div>
</body>
</html>