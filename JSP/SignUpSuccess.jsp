<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up</title>
</head>
<body>
Successfully Sign Up<br>
User Name: <%=request.getAttribute("username") %><br>
Password: <%=request.getAttribute("pass") %><br><br><br><br><br>
<a href="loginPage">Login</a>
</body>
</html>