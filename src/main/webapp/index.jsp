<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="view.beans.customer.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>homepage</title>
<link rel="stylesheet" href="css/styles.css" type="text/css">
</head>
<body>
<% 
	if (request.getParameter("reset") != null) {
	%> 
	<jsp:setProperty property="emailUser" value="" name="customerBean"/>
	<%
	}
	if (customerBean == null || customerBean.getEmailUser()=="") {
%>
<h1 class="Title">Camps Manager</h1>
<div class="ButtonContainer">
	<a class="Button" href="/Proyecto-Programacion-Web/mvc/controller/login/login.jsp">Log in</a>
	<a class="Button" href="/Proyecto-Programacion-Web/mvc/controller/login/registerController.jsp">Sign up</a>
</div>
<% } else { %>
	<p>Welcome <jsp:getProperty property="emailUser" name="customerBean"/>!! </p>
	<p><a href="/Proyecto-Programacion-Web/mvc/controller/login/changeData.jsp">Change data</a></p>
	<p><a href="/Proyecto-Programacion-Web/mvc/controller/logout/logout.jsp">Log out</a></p>
	
<% } %>
</body>
</html>