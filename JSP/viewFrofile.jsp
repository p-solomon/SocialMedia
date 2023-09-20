<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="data.connector.Dbase" %>
     <%@ page import="data.connector.Database" %>
      <%@ page import="data.login.Login1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Social Media App</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<!-- <a href="viewFrofile.jsp">View Profile</a>
<a href="viewAllUsers.jsp">View All Users</a> -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Social Media App</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="welcome.jsp">Home</a></li>
       <li class="active"><a href="viewFrofile.jsp">View Profile</a></li>
      <li><a href="viewAllUsers">View All Users</a></li>
        <li><a href="friendRequests">Friend Requests</a></li>
        <li><a href="myFriendsList">My Friends</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
     <li> <span style="color:white;ma"><%=session.getAttribute("name")%></span></li>
      <li><a href="logout"><span></span> Logout</a></li>
    </ul>
  </div>
</nav>
  
<div class="container">
  <marquee>Social Media App</marquee>


<%
Login1 user=(Login1)request.getAttribute("user");
%>
<table class="table">
  <tr><td>Name</td><td><%=user.getFname()+" "+user.getSname() %></td></tr>
  <tr><td>E-Mail</td><td><%=user.getEmail() %></td></tr>
  <tr><td>Mobile</td><td><%=user.getMob() %></td></tr>
  <tr><td>Username</td><td><%=user.getUsername() %></td></tr>
  
</table>
</div>
</body>
</html>